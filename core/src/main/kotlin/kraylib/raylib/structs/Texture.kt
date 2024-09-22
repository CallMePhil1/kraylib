package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Texture as TextureFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

typealias Texture2D = Texture

class Texture(
    id: Int,
    width: Int,
    height: Int,
    mipmaps: Int,
    format: Int
) : NativeMemory<Texture>() {

    override var memorySegment: MemorySegment = TextureFFM.allocate(FFM.arena)

    var id: Int
        get() = TextureFFM.id(memorySegment)
        set(value) = TextureFFM.id(memorySegment, value)

    var width: Int
        get() = TextureFFM.width(memorySegment)
        set(value) = TextureFFM.width(memorySegment, value)

    var height: Int
        get() = TextureFFM.height(memorySegment)
        set(value) = TextureFFM.height(memorySegment, value)

    var mipmaps: Int
        get() = TextureFFM.mipmaps(memorySegment)
        set(value) = TextureFFM.mipmaps(memorySegment, value)

    var format: Int
        get() = TextureFFM.format(memorySegment)
        set(value) = TextureFFM.format(memorySegment, value)

    init {
        this.id = id
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}