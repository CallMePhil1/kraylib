package kraylib.raylib

import java.lang.foreign.MemorySegment

abstract class NativeMemory(
    internal var memorySegment: MemorySegment
) {

    val address get() = memorySegment.address()

    override fun equals(other: Any?): Boolean {
        if (other is NativeMemory) {
            return address == other.address
        }
        return false
    }

    override fun hashCode(): Int = memorySegment.hashCode()

    fun readdress(to: Long, byteSize: Long = memorySegment.byteSize()) {
        memorySegment = MemorySegment.ofAddress(to).reinterpret(byteSize)
    }
}
