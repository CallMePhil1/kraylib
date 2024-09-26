package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.RenderTexture
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class RenderTexture(
    memorySegment: MemorySegment = RenderTexture.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    var id: Int
        get() = RenderTexture.id(memorySegment)
        set(value) = RenderTexture.id(memorySegment, value)

    var texture: Texture = Texture(RenderTexture.texture(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    var depth: Texture = Texture(RenderTexture.texture(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    constructor(
        id: Int,
        texture: Texture,
        depth: Texture
    ) : this() {
        this.id = id
        this.texture = texture
        this.depth = depth
    }
}