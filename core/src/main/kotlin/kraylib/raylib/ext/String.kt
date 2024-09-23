package kraylib.raylib.ext

import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

fun String.allocate(allocator: SegmentAllocator): MemorySegment = allocator.allocateFrom(this)