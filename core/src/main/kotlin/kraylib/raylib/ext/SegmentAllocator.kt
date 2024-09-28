package kraylib.raylib.ext

import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

fun SegmentAllocator.allocateFillZero(layout: MemoryLayout): MemorySegment = allocateFillZero(layout.byteSize())
fun SegmentAllocator.allocateFillZero(size: Long): MemorySegment = this.allocate(size).fill(0)