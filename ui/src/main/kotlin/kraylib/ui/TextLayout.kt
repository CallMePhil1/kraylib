package kraylib.ui

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Rectangle


object TextLayout {
    fun getBounds(text: String, bitmapFont: BitmapFont): Rectangle {
        val glyphs = text.map { bitmapFont.data.getGlyph(it) }

        val height = bitmapFont.lineHeight
        val width = glyphs.sumOf { it.xadvance }.toFloat()

        return Rectangle(0f, 0f, width, height)
    }
}
