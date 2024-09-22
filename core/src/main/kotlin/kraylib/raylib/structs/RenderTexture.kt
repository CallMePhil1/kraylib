package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.RenderTexture
import java.lang.foreign.MemorySegment

class RenderTexture(
    id: Int,
    texture: Texture,
    depth: Texture
) {
    internal var memorySegment: MemorySegment = RenderTexture.allocate(FFM.arena)

    var id: Int = id
        set(value) {
            RenderTexture.id(memorySegment, value)
            field = value
        }

    var texture: Texture = texture
        set(value) {
            RenderTexture.texture(memorySegment, value.memorySegment)
            field = value
        }

    var depth: Texture = depth
        set(value) {
            RenderTexture.depth(memorySegment, value.memorySegment)
            field = value
        }
}