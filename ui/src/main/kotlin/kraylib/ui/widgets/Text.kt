package kraylib.ui.widgets

import com.badlogic.gdx.graphics.Color
import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext
import kraylib.ui.TextLayout
import io.github.callmephil.parsecs.graphics.gl.Opengl
import io.github.callmephil.parsecs.graphics.using

enum class TextAlignment {
    START,
    END,
    CENTER
}

class Text : Widget() {
    var textAlignment = TextAlignment.START
    var textColor: Color = Color.WHITE
    var text: String = ""

    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        super.draw(renderContext, inputContext, constraint)

        val bounds = TextLayout.getBounds(text, renderContext.bitmapFont)

        val textY = constraint.y + (constraint.height * 0.7f) //constraint.y + (constraint.height / 2f)

        val textX = when(textAlignment) {
            TextAlignment.END -> constraint.x + constraint.width - bounds.width.toInt()
            TextAlignment.CENTER -> constraint.x + constraint.width / 2 - bounds.width / 2
            else -> constraint.x
        }.toFloat()

        if (text.isNotEmpty()) {
            Opengl.scissorTest(renderContext.scissorStack.bounds) {
                renderContext.spriteBatch.using {
                    renderContext.bitmapFont.color = textColor
                    renderContext.bitmapFont.draw(
                        this,
                        text,
                        textX,
                        textY
                    )
                }
            }
        }
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        val bounds = TextLayout.getBounds(text, renderContext.bitmapFont)
        val width = (bounds.width + padding.left + padding.right + (borderThickness * 2)).toInt()
        val height = (bounds.height + padding.top + padding.bottom + (borderThickness * 2)).toInt()

        return Size(width, height)
    }
}

fun Container.text(block: Text.() -> Unit) {
    val text = Text()
    block(text)
    this.addChild(text)
}
