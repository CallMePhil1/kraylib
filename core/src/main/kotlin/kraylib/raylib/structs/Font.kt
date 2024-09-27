package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Font as FontFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Font(
    memorySegment: MemorySegment = FontFFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    /** Base size (default chars height) */
    var baseSize: Int
        get() = FontFFM.baseSize(memorySegment)
        set(value) = FontFFM.baseSize(memorySegment, value)

    /** Number of glyph characters */
    var glyphCount: Int
        get() = FontFFM.glyphCount(memorySegment)
        set(value) = FontFFM.glyphCount(memorySegment, value)

    /** Padding around the glyph characters */
    var glyphPadding: Int
        get() = FontFFM.glyphPadding(memorySegment)
        set(value) = FontFFM.glyphPadding(memorySegment, value)

    /** Texture atlas containing the glyphs */
    var texture: Texture2D = Texture2D(FontFFM.texture(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    /** Rectangles in texture for the glyphs */
    val rectangles: MemorySegment = FontFFM.recs(memorySegment)

    /** Glyphs info data */
    val glyphs: MemorySegment = FontFFM.glyphs(memorySegment)
}
