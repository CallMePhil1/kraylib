package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.GlyphInfo as GlyphInfoFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class GlyphInfo(
    override val memorySegment: MemorySegment = GlyphInfoFFM.allocate(FFM.arena)
) : NativeMemory<GlyphInfo>(memorySegment) {
    
    /** Character value (Unicode) */
    var value: Int
        get() = GlyphInfoFFM.value(memorySegment)
        set(value) = GlyphInfoFFM.value(memorySegment, value)

    /** Character offset X when drawing */
    var offsetX: Int
        get() = GlyphInfoFFM.offsetX(memorySegment)
        set(value) = GlyphInfoFFM.offsetX(memorySegment, value)

    /** Character offset Y when drawing */
    var offsetY: Int
        get() = GlyphInfoFFM.offsetY(memorySegment)
        set(value) = GlyphInfoFFM.offsetY(memorySegment, value)

    /** Character advance position X */
    var advanceX: Int
        get() = GlyphInfoFFM.advanceX(memorySegment)
        set(value) = GlyphInfoFFM.advanceX(memorySegment, value)
    
    /** Character image data */
    var image: Image = Image(GlyphInfoFFM.image(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }
}
