package kraylib.raylib.structs

import kraylib.FFM.arena
import kraylib.ffm.Texture2D
import kraylib.ffm.Texture as TextureFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

typealias Texture2D = Texture

class Texture(
    override var memorySegment: MemorySegment = TextureFFM.allocate(arena)
) : NativeMemory<Texture>(memorySegment) {

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

    constructor(
        id: Int,
        width: Int,
        height: Int,
        mipmaps: Int,
        format: Int
    ) : this(arena.allocate(Texture2D.layout())) {
        this.id = id
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}