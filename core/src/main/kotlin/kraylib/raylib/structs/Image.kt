package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Image as ImageFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Image(
    override val memorySegment: MemorySegment = ImageFFM.allocate(FFM.arena)
) : NativeMemory<Image>(memorySegment) {

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
}

// Image, pixel data stored in CPU memory (RAM)
typedef struct Image {
    void *data;             // Image raw data
} Image;