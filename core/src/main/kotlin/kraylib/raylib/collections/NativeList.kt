package kraylib.raylib.collections

import kraylib.FFM.arena
import kraylib.raylib.NativeMemory
import kraylib.raylib.ext.allocateFillZero
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.lang.foreign.ValueLayout.OfInt
import java.lang.foreign.ValueLayout.OfLong

typealias NativeListGetter<T> = (MemorySegment) -> T
typealias NativeListSetter<T> = (MemorySegment, T) -> Unit

private val nativeMemorySetter: NativeListSetter<NativeMemory> = { memorySegment, nativeMemory ->
    memorySegment.copyFrom(nativeMemory.memorySegment)
    nativeMemory.memorySegment = memorySegment
}

internal class MutableNativeListIterator<T>(private val list: NativeList<T>) : MutableListIterator<T> {
    private var cursor = -1

    override fun add(element: T) {
        throw NotImplementedError()
    }

    override fun hasNext() = nextIndex() < list.size
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

internal class NativeListIterator<T>(private val list: NativeList<T>) : ListIterator<T> {

    private var cursor = -1

    override fun hasNext() = nextIndex() < list.size
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

internal abstract class AbstractNativeList<T>(
    memorySegment: MemorySegment,
    protected val getter: NativeListGetter<T>
) : NativeMemory(memorySegment), NativeList<T> {

    protected var capacity
        get() = memorySegment.get(capacityLayout, CAPACITY_OFFSET)
        set(value) = memorySegment.set(capacityLayout, CAPACITY_OFFSET, value)

    override val data: MemorySegment get() = internalData

    var internalData: MemorySegment = MemorySegment.NULL
        protected set(value) {
            memorySegment.set(dataLayout, DATA_OFFSET, value)
            field = value
        }

    val dataSize: Long
        get() = size * elementSize

    var elementSize: Long
        get() = memorySegment.get(elementSizeLayout, ELEMENT_SIZE_OFFSET)
        protected set(value) = memorySegment.set(elementSizeLayout, ELEMENT_SIZE_OFFSET, value)

    final override var size
        get() = memorySegment.get(sizeLayout, SIZE_OFFSET)
        protected set(value) = memorySegment.set(sizeLayout, SIZE_OFFSET, value)

    protected fun addressAt(at: Int) = data.address() + (elementSize * at)

    protected fun checkIndexBounds(index: Int) {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException("NativeList Index: $index, Size: $size")
    }

    override fun contains(element: T) = indexOf(element) > -1

    override fun containsAll(elements: Collection<T>) = elements.all { indexOf(it) > -1 }

    override fun equals(other: Any?): Boolean {
        if (other !is NativeMemory)
            return false
        return other.memorySegment == this.memorySegment
    }

    override fun get(index: Int): T {
        checkIndexBounds(index)
        return getter(MemorySegment.ofAddress(addressAt(index)).reinterpret(elementSize))
    }

    override fun hashCode() = memorySegment.hashCode()

    override fun indexOf(element: T): Int {
        for (i in 0 until size) {
            val item = get(i)
            if (item == element)
                return i
        }
        return -1
    }

    override fun isEmpty() = size == 0

    override fun lastIndexOf(element: T): Int {
        for (i in size - 1 downTo size) {
            val item = get(i)
            if (item == element)
                return i
        }
        return -1
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw NotImplementedError()
    }

    override fun wrap(memorySegment: MemorySegment) {
        internalData = memorySegment
    }

    companion object {
        internal val layout: GroupLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("capacity"),
            ValueLayout.JAVA_INT.withName("size"),
            ValueLayout.JAVA_LONG.withName("elementSize"),
            ValueLayout.ADDRESS.withName("data")
        ).withName("AbstractNativeList")

        internal val capacityLayout = layout.select(groupElement("capacity")) as OfInt
        internal const val CAPACITY_OFFSET = 0L

        internal val sizeLayout = layout.select(groupElement("size")) as OfInt
        internal const val SIZE_OFFSET = 4L

        internal val elementSizeLayout = layout.select(groupElement("elementSize")) as OfLong
        internal const val ELEMENT_SIZE_OFFSET = 8L

        internal val dataLayout = layout.select(groupElement("data")) as AddressLayout
        internal const val DATA_OFFSET = 16L
    }
}

internal class MutableNativeListImpl<T : NativeMemory>(
    memorySegment: MemorySegment,
    getter: NativeListGetter<T>,
    private val setter: NativeListSetter<T>
) : AbstractNativeList<T>(memorySegment, getter), MutableNativeList<T> {

    constructor(
        memorySegment: MemorySegment,
        data: MemorySegment,
        elementCount: Int,
        elementSize: Long,
        reader: NativeListGetter<T>,
        setter: NativeListSetter<T>
    ) : this(memorySegment, reader, setter) {
        capacity = elementCount
        this.internalData = data
        this.elementSize = elementSize
        this.size = elementCount
    }

    fun addContiguousItems(items: List<T>) {
        if (items.isEmpty())
            return

        ensureCapacity(size + items.size)

        val totalBytes = items.size * elementSize
        val memoryOffset = size * elementSize

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

    override fun add(index: Int, element: T) = add(index, element, true)

    private fun add(index: Int, element: T, shiftRight: Boolean) {
        ensureCapacity(index)
        if (shiftRight and (index < size))
            shiftRight(index, 1)

        val newItemSegment = MemorySegment.ofAddress(addressAt(index)).reinterpret(elementSize)
        setter(newItemSegment, element)
        size++
    }

    override fun addAll(elements: Collection<T>) = addAll(size, elements)

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        ensureCapacity(size + elements.size)
        shiftRight(index, elements.size)

        elements.forEachIndexed { i, item ->
            add(index + i, item, false)
        }
        return true
    }

    override fun clear() {
        size = 0
    }

    private fun ensureCapacity(target: Int) {
        if (target < capacity)
            return

        val newCapacity = if (capacity == 0) target else target * 2
        val newMemorySegment = arena.allocate(elementSize * newCapacity)
        MemorySegment.copy(data, 0, newMemorySegment, 0, elementSize * size)

        capacity = newCapacity
        internalData = newMemorySegment
    }

    override fun iterator() = listIterator()

    override fun listIterator(): MutableListIterator<T> = MutableNativeListIterator(this)

    override fun listIterator(index: Int): MutableListIterator<T> = MutableNativeListIterator(this)

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
            MemorySegment.copy(data, it * byteSize, data, cursor * byteSize, byteSize)
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

        val totalBytes = (size - 1 - at) * elementSize
        val startOffset = at * elementSize
        val targetOffset = (at - amount) * elementSize

        MemorySegment.copy(data, startOffset, data, targetOffset, totalBytes)
    }

    private fun shiftRight(at: Int, amount: Int) {
        ensureCapacity(size + amount)

        for (i in (size - 1) downTo at) {
            val memorySegmentSlice = internalData.asSlice(i * elementSize, elementSize)
            MemorySegment.copy(memorySegmentSlice, 0, internalData, (i + amount) * elementSize, elementSize)
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        throw NotImplementedError()
    }

    class MutableNativeListIterator<T : NativeMemory>(private val list: NativeList<T>) : MutableListIterator<T> {
        private var cursor = -1

        override fun add(element: T) {
            throw NotImplementedError()
        }

        override fun hasNext() = nextIndex() < list.size
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

internal class MutablePrimitiveListImpl<T>(
    memorySegment: MemorySegment,
    read: (MemorySegment) -> T,
    private val writer: (MemorySegment, T) -> Unit
) : AbstractNativeList<T>(memorySegment, read), MutableNativeList<T> {

    constructor(
        memorySegment: MemorySegment,
        data: MemorySegment,
        elementCount: Int,
        elementSize: Long,
        reader: (MemorySegment) -> T,
        writer: (MemorySegment, T) -> Unit
    ) : this(memorySegment, reader, writer) {
        capacity = elementCount
        this.internalData = data
        this.elementSize = elementSize
        this.size = elementCount
    }

    override fun add(element: T): Boolean {
        add(size, element)
        return true
    }

    override fun add(index: Int, element: T) {
        ensureCapacity(index)
        if (index < size)
            shiftRight(index, 1)

        val newItemSegment = MemorySegment.ofAddress(addressAt(index)).reinterpret(elementSize)
        writer(newItemSegment, element)
        size++
    }

    override fun addAll(elements: Collection<T>) = addAll(size, elements)

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        ensureCapacity(size + elements.size)
        shiftRight(index, elements.size)

        elements.forEachIndexed { i, item ->
            add(index + i, item)
        }
        return true
    }

    override fun clear() {
        size = 0
    }

    private fun ensureCapacity(target: Int) {
        if (target < capacity)
            return

        val newCapacity = if (capacity == 0) target else target * 2
        val newDataMemorySegment = arena.allocate(elementSize * newCapacity)
        MemorySegment.copy(data, 0, newDataMemorySegment, 0, elementSize * size)

        capacity = newCapacity
        internalData = newDataMemorySegment
    }

    override fun iterator() = listIterator()

    override fun listIterator(): MutableListIterator<T> = MutableNativeListIterator(this)

    override fun listIterator(index: Int): MutableListIterator<T> = MutableNativeListIterator(this)

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
            MemorySegment.copy(data, it * byteSize, data, cursor * byteSize, byteSize)
        }

        size = if (cursor > -1) cursor else 0
        return true
    }

    override fun set(index: Int, element: T): T {
        checkIndexBounds(index)
        val currentItem = get(index)
        writer(MemorySegment.ofAddress(addressAt(index)).reinterpret(elementSize), element)
        return currentItem
    }

    private fun shiftLeft(at: Int, amount: Int = 1) {

        val totalBytes = (size - 1 - at) * elementSize
        val startOffset = at * elementSize
        val targetOffset = (at - amount) * elementSize

        MemorySegment.copy(data, startOffset, data, targetOffset, totalBytes)
    }

    private fun shiftRight(at: Int, amount: Int) {
        ensureCapacity(size + amount)

        for (i in (size - 1) downTo at) {
            val memorySegmentSlice = data.asSlice((i + amount) * elementSize, elementSize)
            MemorySegment.copy(memorySegmentSlice, 0, data, (i + amount) * elementSize, elementSize)
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        throw NotImplementedError()
    }
}

internal class NativeListImpl<T>(
    memorySegment: MemorySegment,
    ctor: (MemorySegment) -> T
) : AbstractNativeList<T>(memorySegment, ctor) {
    constructor(
        memorySegment: MemorySegment,
        data: MemorySegment,
        elementCount: Int,
        elementSize: Long,
        ctor: (MemorySegment) -> T
    ) : this(memorySegment, ctor) {
        capacity = elementCount
        this.internalData = data
        this.elementSize = elementSize
        this.size = elementCount
    }

    override fun iterator(): Iterator<T> = NativeListIterator(this)

    override fun listIterator(): ListIterator<T> = NativeListIterator(this)

    override fun listIterator(index: Int): ListIterator<T> = NativeListIterator(this)
}

interface NativeList<T> : List<T> {
    val data: MemorySegment

    fun wrap(memorySegment: MemorySegment)
}

interface MutableNativeList<T> : MutableList<T>, NativeList<T>

/**
 * Turn a mutable list into a MutableNativeList
 *
 * If list is empty it will return a null [MutableNativeList].
 * If list is already in contiguous memory then returns a [MutableNativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> mutableNativeListOf(list: List<NativeMemory>, getter: NativeListGetter<T>): MutableNativeList<T> {
    val newListMemorySegment = arena.allocateFillZero(AbstractNativeList.layout)

    if (list.isEmpty())
        return MutableNativeListImpl(newListMemorySegment, getter, nativeMemorySetter)

    val elementSize = list[0].byteSize
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

    return MutableNativeListImpl(
        newListMemorySegment,
        list[0].memorySegment.reinterpret(elementSize * list.size),
        list.size,
        elementSize,
        getter,
        nativeMemorySetter
    )
}

/**
 * Turn a mutable list into a MutableNativeList
 *
 * If list is empty it will return a null [MutableNativeList].
 * Else a new allocation will be made, data from the list copied to that memory and then update list to point to the new memory.
 */
fun <T : Any> mutableNativeListOf(list: List<T>, elementSize: Long, getter: NativeListGetter<T>, setter: NativeListSetter<T>): MutableNativeList<T> {
    val nativeListMemorySegment = arena.allocateFillZero(AbstractNativeList.layout)

    if (list.isEmpty())
        return MutablePrimitiveListImpl(nativeListMemorySegment, getter, setter)

    val dataMemorySegment = arena.allocateFillZero(elementSize * list.size)

    for (i in list.indices)
        setter(dataMemorySegment.asSlice(elementSize * i, elementSize), list[i])

    return MutablePrimitiveListImpl(
        nativeListMemorySegment,
        dataMemorySegment,
        list.size,
        elementSize,
        getter,
        setter
    )
}

/**
 * Turn a list of items into a MutableNativeList
 *
 * If list is empty it will return a null [MutableNativeList].
 * If list is already in contiguous memory then returns a [MutableNativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> mutableNativeListOf(getter: NativeListGetter<T>, vararg items: T): MutableNativeList<T> {
    if (items.isEmpty())
        return MutableNativeListImpl(arena.allocateFillZero(AbstractNativeList.layout), getter, nativeMemorySetter)

    val elementSize = items[0].byteSize
    val startAddress = items[0].address
    var isContiguous = true

    for (i in 1 ..< items.size) {
        if (items[i].address != startAddress + (i * elementSize)) {
            isContiguous = false
            break
        }
    }

    if (!isContiguous)
        items.makeContiguous()

    val newMemorySegment = arena.allocateFillZero(AbstractNativeList.layout)
    return MutableNativeListImpl(
        newMemorySegment,
        items[0].memorySegment.reinterpret(elementSize * items.size),
        items.size,
        elementSize,
        getter,
        nativeMemorySetter
    )
}

/**
 * Creates a [MutableNativeList] starting at the [memorySegment]. The data in [memorySegment] should already be a [MutableNativeList]
 *
 * @param memorySegment Starting location for the native list
 * @param ctor Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
 * @return [MutableNativeList] that will wrap the elements in [memorySegment]
 */
fun <T : NativeMemory> mutableNativeListOf(
    memorySegment: MemorySegment,
    getter: NativeListGetter<T>
): MutableNativeList<T> = MutableNativeListImpl(memorySegment, getter, nativeMemorySetter)

/**
 * Creates a [MutableNativeList] starting at the [memorySegment]. The data in [memorySegment] should already be a [MutableNativeList]
 *
 * @param memorySegment Starting location for the native list
 * @param read Function to get the value from the [memorySegment]. The [memorySegment] address will be the beginning of where the data is stored.
 * @param update Function to update the value of the [memorySegment]. The [memorySegment] address will be the beginning of where the data is stored.
 * @return [MutableNativeList] that will wrap the elements in [memorySegment]
 */
fun <T : Any> mutableNativeListOf(
    memorySegment: MemorySegment,
    read: (MemorySegment) -> T,
    update: (MemorySegment, T) -> Unit
): MutableNativeList<T> = MutablePrimitiveListImpl(memorySegment, read, update)

/**
 * Turn a list into a NativeList
 *
 * If list is empty it will return a null [NativeList].
 * If list is already in contiguous memory then returns a [NativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> nativeListOf(list: List<T>, ctor: (MemorySegment) -> T): NativeList<T> {
    if (list.isEmpty())
        return NativeListImpl(arena.allocateFillZero(AbstractNativeList.layout), ctor)

    val elementSize = list[0].byteSize
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

    val newMemorySegment = arena.allocate(AbstractNativeList.layout)
    return NativeListImpl(
        newMemorySegment,
        list[0].memorySegment.reinterpret(elementSize * list.size),
        list.size,
        elementSize,
        ctor
    )
}

/**
 * Turn a list of non [NativeMemory] objects into a NativeList
 *
 * If list is empty it will return a null [NativeList].
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: Any> nativeListOf(list: List<T>, elementSize: Long, read: (MemorySegment) -> T, write: (MemorySegment) -> Unit): NativeList<T> {
    val nativeListMemorySegment = arena.allocateFillZero(AbstractNativeList.layout)

    if (list.isEmpty())
        return NativeListImpl(nativeListMemorySegment, read)

    val dataMemorySegment = arena.allocateFillZero(elementSize * list.size)

    for (i in list.indices) {
        write(dataMemorySegment.asSlice(elementSize * i, elementSize))
    }

    return NativeListImpl(
        nativeListMemorySegment,
        dataMemorySegment,
        list.size,
        elementSize,
        read
    )
}

/**
 * Turn a list of items into a NativeList
 *
 * If list is empty it will return a null [NativeList].
 * If list is already in contiguous memory then returns a [NativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> nativeListOf(ctor: (MemorySegment) -> T, vararg items: T) : NativeList<T> {
    if (items.isEmpty())
        return NativeListImpl(arena.allocateFillZero(AbstractNativeList.layout), ctor)

    val elementSize = items[0].byteSize
    val startAddress = items[0].address
    var isContiguous = true

    for (i in 1 ..< items.size) {
        if (items[i].address != startAddress + (i * elementSize)) {
            isContiguous = false
            break
        }
    }

    if (!isContiguous)
        items.makeContiguous()

    val newMemorySegment = arena.allocateFillZero(AbstractNativeList.layout)
    return NativeListImpl(
        newMemorySegment,
        items[0].memorySegment.reinterpret(elementSize * items.size),
        items.size,
        elementSize,
        ctor
    )
}

///**
// * Creates a [NativeList] starting at the [memorySegment]. The data the this [memorySegment] should already be a [NativeList]
// *
// * @param memorySegment Starting location for the native list
// * @param ctor Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
// * @return [NativeList] that will wrap the elements in [memorySegment]
// */
//fun <T : NativeMemory> nativeListOf(memorySegment: MemorySegment, getter: (MemorySegment) -> T
//): NativeList<T> = NativeListImpl(memorySegment, getter)

/**
 * Creates a [NativeList] starting at the [memorySegment]. The data in [memorySegment] should already be a [NativeList]
 *
 * @param memorySegment Starting location for the native list
 * @param getter Function to get the value from the [memorySegment]. The [memorySegment] address will be the beginning of where the data is stored.
 * @return [NativeList] that will wrap the elements in [memorySegment]
 */
fun <T : Any> nativeListOf(
    memorySegment: MemorySegment,
    getter: (MemorySegment) -> T,
): NativeList<T> = NativeListImpl(memorySegment, getter)

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
 *
 * A allocation is made to contain all items. Items are copied over individually to this new memory and the
 * JVM objects are update with the new memory segment
 */
fun <T : NativeMemory> List<T>.makeContiguous() {
    if (this.isEmpty())
        return

    val elementSize = this[0].byteSize
    val totalBytes = elementSize * this.size
    val newMemorySegment = arena.allocate(totalBytes)

    this.forEachIndexed { i, item ->
        val newItemMemorySegment = newMemorySegment
            .asSlice(elementSize * i, elementSize)
            .copyFrom(item.memorySegment)
        item.memorySegment = newItemMemorySegment
    }
}

/**
 * Converts this list to have all items in contiguous memory. This is an inplace operation.
 *
 * A allocation is made to contain all items. Items are copied over individually to this new memory and the
 * JVM objects are update with the new memory segment
 */
fun <T : NativeMemory> Array<T>.makeContiguous() {
    if (this.isEmpty())
        return

    val elementSize = this[0].byteSize
    val totalBytes = elementSize * this.size
    val newMemorySegment = arena.allocate(totalBytes)

    this.forEachIndexed { i, item ->
        val newItemMemorySegment = newMemorySegment
            .asSlice(elementSize * i, elementSize)
            .copyFrom(item.memorySegment)
        item.memorySegment = newItemMemorySegment
    }
}