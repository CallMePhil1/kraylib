package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Font
import kraylib.ffm.GlyphInfo as GlyphInfoFFM
import kraylib.raylib.NativeMemory
import kraylib.raylib.collections.NativeList
import kraylib.raylib.collections.nativeListOf
import java.lang.foreign.MemorySegment

class Font(
    override val memorySegment: MemorySegment = Font.allocate(FFM.arena)
) : NativeMemory<Font>(memorySegment) {

    /** Base size (default chars height) */
    var baseSize: Int
        get() = Font.baseSize(memorySegment)
        set(value) = Font.baseSize(memorySegment, value)

    /** Number of glyph characters */
    var glyphCount: Int
        get() = Font.glyphCount(memorySegment)
        set(value) = Font.glyphCount(memorySegment, value)

    /** Padding around the glyph characters */
    var glyphPadding: Int
        get() = Font.glyphPadding(memorySegment)
        set(value) = Font.glyphPadding(memorySegment, value)

    /** Texture atlas containing the glyphs */
    var texture: Texture2D = Texture2D(Font.texture(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    /** Rectangles in texture for the glyphs */
    val rectangles: NativeList<Rectangle> = nativeListOf(Font.recs(memorySegment), Font.layout(), -1) { Rectangle(it) }

    /** Glyphs info data */
    val glyphs: NativeList<GlyphInfo> = nativeListOf(Font.glyphs(memorySegment), GlyphInfoFFM.layout(), -1) { GlyphInfo(it) }
}
