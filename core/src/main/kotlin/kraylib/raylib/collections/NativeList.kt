package kraylib.raylib.collections

import kraylib.FFM.arena
import kraylib.raylib.NativeMemory
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.OfInt

abstract class AbstractNativeList<T : NativeMemory>(
    memorySegment: MemorySegment,
    private val ctor: (MemorySegment) -> T
) : NativeMemory(memorySegment, layout.byteSize()), NativeList<T> {

    override var byteSize: Long = 0
        get() = (size * elementSize) + layout.byteSize()

    protected var capacity
        get() = memorySegment.get(capacityLayout, CAPACITY_OFFSET)
        set(value) = memorySegment.set(capacityLayout, CAPACITY_OFFSET, value)

    internal val dataAddress
        get() = memorySegment.address() + DATA_ADDRESS_OFFSET

    private var elementSize: Int
        get() = memorySegment.get(elementSizeLayout, ELEMENT_SIZE_OFFSET)
        set(value) = memorySegment.set(capacityLayout, CAPACITY_OFFSET, value)

    final override var size
        get() = memorySegment.get(sizeLayout, SIZE_OFFSET)
        set(value) = memorySegment.set(sizeLayout, SIZE_OFFSET, value)

    protected fun checkIndexBounds(index: Int) {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("NativeList Index: $index, Size: $size")
    }

    override fun contains(element: T) = indexOf(element) > -1

    override fun containsAll(elements: Collection<T>) = elements.all { indexOf(it) > -1 }

    override fun get(index: Int): T {
        checkIndexBounds(index)
        return ctor(memorySegment.asSlice(index * byteSize))
    }

    override fun indexOf(element: T): Int {
        val proxy = ctor(MemorySegment.NULL)

        for (i in 0 until size) {
            proxy.memorySegment = memorySegment.asSlice(i * byteSize)
            if (proxy == element)
                return i
        }
        return -1
    }

    override fun isEmpty() = size == 0

    override fun lastIndexOf(element: T): Int {
        val proxy = ctor(MemorySegment.NULL)

        for (i in size - 1 downTo size) {
            proxy.memorySegment = memorySegment.asSlice(i * byteSize)
            if (proxy == element)
                return i
        }
        return -1
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw NotImplementedError()
    }

    companion object {
        internal val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("capacity"),
            ValueLayout.JAVA_INT.withName("size"),
            ValueLayout.JAVA_INT.withName("elementSize")
        ).withName("AbstractNativeList")

        internal val capacityLayout = layout.select(groupElement("capacity")) as OfInt
        internal const val CAPACITY_OFFSET = 0L

        internal val sizeLayout = layout.select(groupElement("size")) as OfInt
        internal const val SIZE_OFFSET = 4L

        internal val elementSizeLayout = layout.select(groupElement("elementSize")) as OfInt
        internal const val ELEMENT_SIZE_OFFSET = 8L

        internal const val DATA_ADDRESS_OFFSET = 12L
    }
}

internal class MutableNativeListImpl<T : NativeMemory>(
    memorySegment: MemorySegment,
    ctor: (MemorySegment) -> T
) : AbstractNativeList<T>(memorySegment, ctor), MutableNativeList<T> {

    constructor(
        memorySegment: MemorySegment,
        elementCount: Int,
        ctor: (MemorySegment) -> T
    ) : this(memorySegment, ctor) {
        capacity = elementCount
        this.size = elementCount
    }

    fun addContiguousItems(items: List<T>) {
        if (items.isEmpty())
            return

        ensureCapacity(size + items.size)

        val totalBytes = items.size * byteSize
        val memoryOffset = size * byteSize

        MemorySegment.copy(items[0].memorySegment, 0, memorySegment, memoryOffset, totalBytes)

        for (i in items.indices) {
            items[i].readdress(addressAt(size + i))
        }

        size += items.size
    }

    override fun add(element: T): Boolean {
        add(size, element)
        return true
    }

    override fun add(index: Int, element: T) {
        ensureCapacity(size + 1)
        if (index < size)
            shiftRight(index, 1)

        val newItemSegment = MemorySegment.ofAddress(addressAt(index)).reinterpret(byteSize).copyFrom(element.memorySegment)
        element.memorySegment = newItemSegment
    }

    override fun addAll(elements: Collection<T>) = addAll(size, elements)

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        ensureCapacity(size + elements.size)
        shiftRight(index, elements.size)

        elements.forEachIndexed { i, item ->
            MemorySegment.copy(item.memorySegment, 0, memorySegment, (index + i) * byteSize, byteSize)
        }
        return true
    }

    private fun addressAt(at: Int) = memorySegment.address() + (byteSize * at)

    override fun clear() {
        size = 0
    }

    private fun ensureCapacity(target: Int) {
        if (target < capacity)
            return

        val newCapacity = if (capacity == 0) target else target * 2
        val newMemorySegment = arena.allocate(byteSize * newCapacity)
        MemorySegment.copy(memorySegment, 0, newMemorySegment, 0, byteSize * size)

        capacity = newCapacity
        memorySegment = newMemorySegment
    }

    override fun iterator() = listIterator()

    override fun listIterator(): MutableListIterator<T> = MutableNativeListIterator(this, size)

    override fun listIterator(index: Int): MutableListIterator<T> = MutableNativeListIterator(this, size)

    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        if (index < 0)
            return false

        removeAt(index)
        return true
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        elements.map { indexOf(it) }.filterNot { it == -1 }.sortedDescending().forEach(::removeAt)
        return true
    }

    override fun removeAt(index: Int): T {
        checkIndexBounds(index)
        val oldValue = get(index)
        shiftLeft(index + 1)
        size--
        return oldValue
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val indices = elements.map { indexOf(it) }.sorted()
        var cursor = -1

        indices.forEach {
            cursor++

            if (cursor == it)
                return@forEach
            MemorySegment.copy(memorySegment, it * byteSize, memorySegment, cursor * byteSize, byteSize)
        }

        size = if (cursor > -1) cursor else 0
        return true
    }

    override fun set(index: Int, element: T): T {
        checkIndexBounds(index)
        val currentItem = get(index)
        currentItem.memorySegment.copyFrom(element.memorySegment)
        return currentItem
    }

    private fun shiftLeft(at: Int, amount: Int = 1) {

        val totalBytes = amount * byteSize
        val startOffset = at * byteSize
        val targetOffset = (at - amount) * byteSize

        MemorySegment.copy(memorySegment, startOffset, memorySegment, targetOffset, totalBytes)
    }

    private fun shiftRight(at: Int, amount: Int) {
        ensureCapacity(size + amount)

        for (i in (size - 1) downTo at) {
            val memorySegmentSlice = memorySegment.asSlice((i + amount) * byteSize, byteSize)
            MemorySegment.copy(memorySegmentSlice, 0, memorySegment, (i + amount) * byteSize, byteSize)
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        throw NotImplementedError()
    }

    class MutableNativeListIterator<T : NativeMemory>(private val list: NativeList<T>, private val size: Int) : MutableListIterator<T> {
        private var cursor = -1

        override fun add(element: T) {
            throw NotImplementedError()
        }

        override fun hasNext() = nextIndex() < size
        override fun hasPrevious() = previousIndex() >= 0

        override fun next(): T {
            cursor++
            return list[cursor]
        }
        override fun nextIndex() = cursor + 1

        override fun previous(): T {
            cursor--
            if (cursor < 0)
                throw NoSuchElementException("NativeListIterator: Not element at index $cursor")

            return list[cursor]
        }
        override fun previousIndex() = cursor - 1

        override fun remove() {
            throw NotImplementedError()
        }

        override fun set(element: T) {
            throw NotImplementedError()
        }
    }
}

class NativeListImpl<T : NativeMemory>(
    memorySegment: MemorySegment,
    ctor: (MemorySegment) -> T
) : AbstractNativeList<T>(memorySegment, ctor) {

    constructor(
        memorySegment: MemorySegment,
        elementCount: Int,
        ctor: (MemorySegment) -> T
    ) : this(memorySegment, ctor) {
        capacity = elementCount
        this.size = elementCount
    }

    override fun iterator(): Iterator<T> = NativeListIterator(this, size)

    override fun listIterator(): ListIterator<T> = NativeListIterator(this, size)

    override fun listIterator(index: Int): ListIterator<T> = NativeListIterator(this, size)

    class NativeListIterator<T : NativeMemory>(private val list: NativeList<T>, private val size: Int) : ListIterator<T> {

        private var cursor = -1

        override fun hasNext() = nextIndex() < size
        override fun hasPrevious() = previousIndex() >= 0

        override fun next(): T {
            cursor++
            return list[cursor]
        }
        override fun nextIndex() = cursor + 1

        override fun previous(): T {
            cursor--
            if (cursor < 0)
                throw NoSuchElementException("NativeListIterator: Not element at index $cursor")

            return list[cursor]
        }
        override fun previousIndex() = cursor - 1
    }
}

interface NativeList<T : NativeMemory> : List<T>

interface MutableNativeList<T : NativeMemory> : MutableList<T>, NativeList<T>

/**
 * Turn a mutable list into a MutableNativeList
 *
 * If list is empty it will return a null [MutableNativeList].
 * If list is already in contiguous memory then returns a [MutableNativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> mutableNativeListOf(list: MutableList<T>, elementSize: Long, ctor: (MemorySegment) -> T): MutableNativeList<T> {
    if (list.isEmpty())
        return MutableNativeListImpl(arena.allocate(AbstractNativeList.sizeLayout), ctor)

    val startAddress = list[0].address
    var isContiguous = true

    for (i in 1 ..< list.size) {
        if (list[i].address != startAddress + (i * elementSize)) {
            isContiguous = false
            break
        }
    }

    if (!isContiguous)
        list.makeContiguous()

    val dataSize = list.size * elementSize
    val newMemorySegment = arena.allocate(AbstractNativeList.layout.byteSize() + dataSize)

    MemorySegment.copy(list[0].memorySegment, 0, newMemorySegment, AbstractNativeList.layout.byteSize(), dataSize)
    return MutableNativeListImpl(newMemorySegment, list.size, ctor)
}

/**
 * Creates a [MutableNativeList] starting at the [memorySegment]. The data the this [memorySegment] should already be a [MutableNativeList]
 *
 * @param memorySegment Starting location for the native list
 * @param ctor Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
 * @return [MutableNativeList] that will wrap the elements in [memorySegment]
 */
fun <R : NativeMemory> mutableNativeListOf(
    memorySegment: MemorySegment,
    ctor: (MemorySegment) -> R
): MutableNativeList<R> = MutableNativeListImpl(memorySegment, ctor)

/**
 * Turn a mutable list into a MutableNativeList
 *
 * If list is empty it will return a null [NativeList].
 * If list is already in contiguous memory then returns a [NativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> nativeListOf(list: List<T>, elementSize: Long, ctor: (MemorySegment) -> T): NativeList<T> {
    if (list.isEmpty())
        return NativeListImpl(arena.allocate(AbstractNativeList.sizeLayout), ctor)

    val startAddress = list[0].address
    var isContiguous = true

    for (i in 1 ..< list.size) {
        if (list[i].address != startAddress + (i * elementSize)) {
            isContiguous = false
            break
        }
    }

    if (!isContiguous)
        list.makeContiguous()

    val dataSize = list.size * elementSize
    val newMemorySegment = arena.allocate(AbstractNativeList.layout.byteSize() + dataSize)

    MemorySegment.copy(list[0].memorySegment, 0, newMemorySegment, AbstractNativeList.layout.byteSize(), dataSize)
    return NativeListImpl(newMemorySegment, list.size, ctor)
}

/**
 * Creates a [NativeList] starting at the [memorySegment]. The data the this [memorySegment] should already be a [NativeList]
 *
 * @param memorySegment Starting location for the native list
 * @param ctor Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
 * @return [NativeList] that will wrap the elements in [memorySegment]
 */
fun <R : NativeMemory> nativeListOf(
    memorySegment: MemorySegment,
    ctor: (MemorySegment) -> R
): NativeList<R> = NativeListImpl(memorySegment, ctor)

fun <T : NativeMemory> List<T>.allocateContiguous(init: (MemorySegment) -> T): MutableList<T> {
    val totalBytes = this.sumOf { it.memorySegment.byteSize() }

    val newMemorySegment = arena.allocate(totalBytes)

    val startAddress = newMemorySegment.address()
    var offset = 0L
    val contiguousList = mutableListOf<T>()

    this.forEach {
        val newItemMemorySegment = MemorySegment
            .ofAddress(startAddress + offset)
            .reinterpret(it.memorySegment.byteSize())
            .copyFrom(it.memorySegment)
        offset += it.memorySegment.byteSize()
        contiguousList.add(init(newItemMemorySegment))
    }

    return contiguousList
}

/**
 * Converts this list to have all items in contiguous memory. This is an inplace operation.
 */
fun <T : NativeMemory> List<T>.makeContiguous() {
    val totalBytes = this.sumOf { it.memorySegment.byteSize() }
    val newMemorySegment = arena.allocate(totalBytes)

    val startAddress = newMemorySegment.address()
    var offset = 0L

    this.forEach {
        val newItemMemorySegment = MemorySegment
            .ofAddress(startAddress + offset)
            .reinterpret(it.memorySegment.byteSize())
            .copyFrom(it.memorySegment)
        offset += it.memorySegment.byteSize()
        it.memorySegment = newItemMemorySegment
    }
}