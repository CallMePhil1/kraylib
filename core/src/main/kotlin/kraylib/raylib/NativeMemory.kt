package kraylib.raylib

import java.lang.foreign.MemorySegment

abstract class NativeMemory<T> {
    internal open lateinit var memorySegment: MemorySegment

    open fun copy(): T = this as T
}