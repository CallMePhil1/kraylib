package kraylib.raylib

import java.lang.foreign.MemorySegment

abstract class NativeMemory(
    internal var memorySegment: MemorySegment,
) {
    val address get() = memorySegment.address()

    val byteSize: Long get() = memorySegment.byteSize()

    override fun equals(other: Any?): Boolean {
        if (other is NativeMemory) {
            if (byteSize != other.byteSize)
                return false
            return memorySegment.mismatch(other.memorySegment) == -1L
        }
        return false
    }

    override fun hashCode(): Int = memorySegment.hashCode()

    fun readdress(to: Long, byteSize: Long = memorySegment.byteSize()) {
        memorySegment = MemorySegment.ofAddress(to).reinterpret(byteSize)
    }
}
