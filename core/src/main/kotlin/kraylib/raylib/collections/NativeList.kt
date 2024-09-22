package kraylib.raylib.collections

import kraylib.FFM.arena
import kraylib.ffm.Raylib
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class NativeList<T : NativeMemory<T>>(
    val byteSize: Long,
    val arePointers: Boolean,
    private val items: List<T>
) : NativeMemory<NativeList<T>>(), List<T> by items {

    companion object {
        fun <T: NativeMemory<T>> nativeListOf(items: List<T>, layout: ValueLayout, isContiguous: Boolean = false, arePointers: Boolean = false, updateRefs: Boolean = true): NativeList<T> {
            if (isContiguous)
                return NativeList(layout.byteSize(), arePointers, items)

            val elementSize = if(arePointers) Raylib.C_POINTER.byteSize() else layout.byteSize()
            val bytesSize = elementSize * items.size

            val newSegment = arena.allocate(bytesSize)

            val newItems: ArrayList<T>? = if (updateRefs) null else ArrayList(items.size)

            items.forEachIndexed { index, item ->
                MemorySegment.copy(
                    items[index].memorySegment, layout, 0,
                    newSegment, layout, elementSize * index,
                    1
                )
                val segment = MemorySegment.ofAddress(newSegment.address() + elementSize * index).reinterpret(elementSize)

                if (updateRefs)
                    item.memorySegment = segment
                else
                    newItems!!.add(index, item.copy().apply { memorySegment = segment })
            }

            val nativeList = if (updateRefs) items else newItems!!
            return NativeList(elementSize, arePointers, nativeList)
        }
    }
}