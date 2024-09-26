package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Image as ImageFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

/** Image, pixel data stored in CPU memory (RAM) */
class Image(
    memorySegment: MemorySegment = ImageFFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    /** Image base width */
    var width: Int
        get() = ImageFFM.width(memorySegment)
        set(value) = ImageFFM.width(memorySegment, value)

    /** Image base height */
    var height: Int
        get() = ImageFFM.height(memorySegment)
        set(value) = ImageFFM.height(memorySegment, value)

    /** Mipmap levels, 1 by default */
    var mipmaps: Int
        get() = ImageFFM.mipmaps(memorySegment)
        set(value) = ImageFFM.mipmaps(memorySegment, value)

    /** Data format (PixelFormat type) */
    var format: Int
        get() = ImageFFM.format(memorySegment)
        set(value) = ImageFFM.format(memorySegment, value)

    /** Image raw data */
    var data: MemorySegment
        get() = ImageFFM.data(memorySegment)
        set(value) = ImageFFM.data(memorySegment, value)
}
