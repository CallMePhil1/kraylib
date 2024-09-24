package kraylib.raylib.collections

import kraylib.FFM.arena
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment

abstract class AbstractNativeList<T : NativeMemory>(
    memorySegment: MemorySegment,
    val byteSize: Long,
) : NativeMemory(memorySegment), NativeList<T> {

    abstract val list: List<T>

    internal var capacity: Int = list.size
    override var size: Int = list.size

    override fun contains(element: T) = list.contains(element)

    override fun containsAll(elements: Collection<T>) = list.containsAll(elements)

    override fun get(index: Int) = list[index]

    override fun indexOf(element: T) = list.indexOf(element)

    override fun isEmpty() = size == 0

    override fun lastIndexOf(element: T) = list.lastIndexOf(element)

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw NotImplementedError()
    }
}

class MutableNativeListImpl<T : NativeMemory>(
    memorySegment: MemorySegment,
    override val list: MutableList<T>,
    byteSize: Long,
) : AbstractNativeList<T>(memorySegment, byteSize), MutableNativeList<T> {

    fun addContiguousItems(items: List<T>) {
        if (items.isEmpty())
            return

        ensureCapacity(size + items.size)

        val totalBytes = items.size * byteSize
        val memoryOffset = size * byteSize

        MemorySegment.copy(items[0].memorySegment, 0, memorySegment, memoryOffset, totalBytes)
        list.addAll(items)

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
        if (index < size) {
            shiftRight(index, 1)
        }

        val newItemSegment = MemorySegment.ofAddress(addressAt(index)).reinterpret(byteSize).copyFrom(element.memorySegment)
        element.memorySegment = newItemSegment
        list.add(index, element)
    }

    override fun addAll(elements: Collection<T>) = addAll(size, elements)

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        ensureCapacity(size + elements.size)
        shiftRight(index, elements.size)

        elements.forEachIndexed { i, item ->
            val newMemorySegment = MemorySegment.ofAddress((index + i) * byteSize).reinterpret(byteSize).copyFrom(item.memorySegment)
            item.memorySegment = newMemorySegment
        }

        list.addAll(elements)

        return true
    }

    private fun addressAt(at: Int) = memorySegment.address() + (byteSize * at)

    override fun clear() {
        MemorySegment.ofAddress(address).reinterpret(size * byteSize).fill(0)
        size = 0
        list.clear()
    }

    private fun ensureCapacity(target: Int) {
        if (target < capacity)
            return

        val newCapacity = (target * 1.5f).toInt()
        val newMemorySegment = arena.allocate(byteSize * newCapacity)
        newMemorySegment.copyFrom(memorySegment)

        list.forEachIndexed { index, item ->
            val newItemMemorySegment = MemorySegment.ofAddress(newMemorySegment.address() + byteSize * index).reinterpret(byteSize)
            item.memorySegment = newItemMemorySegment
        }

        capacity = newCapacity
    }

    override fun iterator() = listIterator()

    override fun listIterator(): MutableListIterator<T> = MutableNativeListIterator(list.listIterator())

    override fun listIterator(index: Int): MutableListIterator<T> = MutableNativeListIterator(list.listIterator(index))

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
        shiftLeft(index + 1, 1)
        size -= 1
        return list.removeLast()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val indices = elements.map { indexOf(it) }.sorted()
        var cursor = 0

        indices.forEach {
            if (cursor == it)
                return@forEach
            list[cursor].memorySegment.copyFrom(list[it].memorySegment)
            cursor += 1
        }

        IntRange(0, elements.size).forEach { list.removeLast() }
        size -= elements.size

        return true
    }

    override fun set(index: Int, element: T): T {
        val currentItem = list[index]
        currentItem.memorySegment.copyFrom(element.memorySegment)
        return currentItem
    }

    private fun shiftLeft(at: Int, amount: Int) {

        val totalBytes = amount * byteSize
        val startOffset = at * byteSize
        val targetOffset = (at - amount) * byteSize

        MemorySegment.copy(memorySegment, startOffset, memorySegment, targetOffset, totalBytes)

//        for (i in IntProgression.fromClosedRange(at, size - 1, 1)) {
//            val itemNewMemorySegment = MemorySegment
//                .ofAddress((i - amount) * byteSize)
//                .reinterpret(byteSize)
//            list[i].memorySegment = itemNewMemorySegment
//        }
    }

    private fun shiftRight(at: Int, amount: Int) {
        ensureCapacity(size + amount)

        for (i in IntProgression.fromClosedRange(size - 1, at, -1)) {
            val itemNewMemorySegment = MemorySegment
                .ofAddress((i + amount) * byteSize)
                .reinterpret(byteSize)
                .copyFrom(list[i].memorySegment)
            list[i].memorySegment = itemNewMemorySegment
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        throw NotImplementedError()
    }

    class MutableNativeListIterator<T>(private val iterator: MutableListIterator<T>) : MutableListIterator<T> {
        override fun add(element: T) {
            throw NotImplementedError()
        }

        override fun hasNext() = iterator.hasNext()
        override fun hasPrevious() = iterator.hasPrevious()

        override fun next() = iterator.next()
        override fun nextIndex() = iterator.nextIndex()

        override fun previous() = iterator.previous()
        override fun previousIndex() = iterator.previousIndex()

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
    override val list: List<T>,
    byteSize: Long
) : AbstractNativeList<T>(memorySegment, byteSize) {

    override fun iterator(): Iterator<T> = list.iterator()

    override fun listIterator(): ListIterator<T> = list.listIterator()

    override fun listIterator(index: Int): ListIterator<T> = list.listIterator(index)
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
fun <T: NativeMemory> mutableNativeListOf(list: MutableList<T>, byteSize: Long): MutableNativeList<T> {
    if (list.isEmpty())
        return MutableNativeListImpl(MemorySegment.NULL, list, byteSize)

    val startAddress = list[0].address
    var isContiguous = true

    for (i in 1 ..< list.size) {
        if (list[i].address != startAddress + (i * byteSize)) {
            isContiguous = false
            break
        }
    }

    if (isContiguous)
        return MutableNativeListImpl(list[0].memorySegment, list, byteSize)

    list.makeContiguous()

    return MutableNativeListImpl(list[0].memorySegment, list, byteSize)
}

/**
 * Creates a [MutableNativeList] starting at the [memorySegment]. The elements should already be contiguous in memory
 *
 * @param memorySegment Starting location for the native list
 * @param layout The memory layout of the type of the list
 * @param count The count of how many elements are in that memory location
 * @param init Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
 * @return Mutable list that will contain elements in contiguous memory
 */
inline fun <R : NativeMemory> mutableNativeListOf(memorySegment: MemorySegment, layout: MemoryLayout, count: Int = -1, init: (MemorySegment) -> R): MutableNativeList<R> {
    val itemList = mutableListOf<R>()
    val elementSize = layout.byteSize()
    val elementCount = if (count > -1) count else 0

    for (i in 0 ..< elementCount) {
        val itemMemorySegment = MemorySegment.ofAddress(memorySegment.address() + elementSize * i).reinterpret(elementSize)
        val item = init(itemMemorySegment)
        itemList.add(item)
    }

    return MutableNativeListImpl(memorySegment, itemList, elementSize)
}

/**
 * Turn a mutable list into a MutableNativeList
 *
 * If list is empty it will return a null [NativeList].
 * If list is already in contiguous memory then returns a [NativeList] wrapped around the list.
 * Else a new allocation will be made, data from the list copied to that memory and then update the list inplace to point to the new memory.
 */
fun <T: NativeMemory> nativeListOf(list: List<T>, byteSize: Long): NativeList<T> {
    if (list.isEmpty())
        return NativeListImpl(MemorySegment.NULL, list, byteSize)

    val startAddress = list[0].address
    var isContiguous = true

    for (i in 1 ..< list.size) {
        if (list[i].address != startAddress + (i * byteSize)) {
            isContiguous = false
            break
        }
    }

    if (isContiguous)
        return NativeListImpl(list[0].memorySegment, list, byteSize)

    list.makeContiguous()

    return NativeListImpl(list[0].memorySegment, list, byteSize)
}

/**
 * Creates a [NativeList] starting at the [memorySegment]. The elements should already be contiguous in memory
 *
 * @param memorySegment Starting location for the native list
 * @param layout The memory layout of the type of the list
 * @param count The count of how many elements are in that memory location
 * @param init Construct a JVM object from the [MemorySegment] that is the start location of the object in memory
 * @return Mutable list that will contain elements in contiguous memory
 */
inline fun <R : NativeMemory> nativeListOf(memorySegment: MemorySegment, layout: MemoryLayout, count: Int = -1, init: (MemorySegment) -> R): NativeList<R> {
    val itemList = mutableListOf<R>()
    val elementSize = layout.byteSize()
    val elementCount = if (count > -1) count else 0

    for (i in 0 ..< elementCount) {
        val itemMemorySegment = MemorySegment.ofAddress(memorySegment.address() + elementSize * i).reinterpret(elementSize)
        val item = init(itemMemorySegment)
        itemList.add(item)
    }

    return NativeListImpl(memorySegment, itemList.toList(), elementSize)
}

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