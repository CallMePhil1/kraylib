package kraylib.raylib.collections

import kraylib.FFM
import kraylib.FFM.arena
import kraylib.ffm.Raylib
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment

class NativeList<T : NativeMemory<T>>(
    override var memorySegment: MemorySegment,
    val byteSize: Long,
    val arePointers: Boolean,
    private val items: List<T>
) : NativeMemory<NativeList<T>>(memorySegment), List<T> by items

inline fun <reified R : NativeMemory<R>> nativeListOf(memorySegment: MemorySegment, layout: MemoryLayout, count: Int = -1, builder: (MemorySegment) -> R): NativeList<R> {
    val itemList = mutableListOf<R>()
    val elementSize = layout.byteSize()
    val elementCount = if (count > -1) count else 0

    for (i in 0 ..< elementCount) {
        val itemMemorySegment = MemorySegment.ofAddress(memorySegment.address() + elementSize * i).reinterpret(elementSize)
        val item = builder(itemMemorySegment)
        itemList.add(item)
    }

    return NativeList(memorySegment, elementSize, false, itemList)
}

fun <T: NativeMemory<T>> nativeListOf(items: List<T>, layout: MemoryLayout, isContiguous: Boolean = false, arePointers: Boolean = false, updateRefs: Boolean = true): NativeList<T> {
    if (isContiguous) {
        val listSegment = if (items.isEmpty()) arena.allocate(0) else items[0].memorySegment
        return NativeList(listSegment, layout.byteSize(), arePointers, items)
    }

    val elementSize = if(arePointers) Raylib.C_POINTER.byteSize() else layout.byteSize()
    val bytesSize = elementSize * items.size

    val listSegment = arena.allocate(bytesSize)

    val newItems: ArrayList<T>? = if (updateRefs) null else ArrayList(items.size)

    items.forEachIndexed { index, item ->
        MemorySegment.copy(
            items[index].memorySegment,0, listSegment, elementSize * index, elementSize
        )

        val segment = MemorySegment.ofAddress(listSegment.address() + elementSize * index).reinterpret(elementSize)

        if (updateRefs)
            item.memorySegment = segment
        else
            newItems!!.add(index, item.copy().apply { memorySegment = segment })
    }

    val nativeList = if (updateRefs) items else newItems!!
    return NativeList(listSegment, elementSize, arePointers, nativeList)
}