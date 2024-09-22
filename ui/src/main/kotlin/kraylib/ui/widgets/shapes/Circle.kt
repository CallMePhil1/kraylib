package io.github.callmephil.parsecs.ui.shapes

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import kraylib.ui.RenderContext
import io.github.callmephil.parsecs.graphics.gl.Opengl
import io.github.callmephil.parsecs.graphics.using
import io.github.callmephil.parsecs.ui.*
import kraylib.ui.widgets.Constraint
import kraylib.ui.widgets.Container
import kraylib.ui.widgets.Size
import kraylib.ui.widgets.Widget

class Circle : Widget() {

    var filled: Boolean = true
    var radius: Int = 0

    override fun drawBackground(renderContext: RenderContext, constraint: Constraint, color: Color) {
        if (color != Color.CLEAR) {
            Opengl.scissorTest(renderContext.scissorStack.bounds) {
                val type = if (filled) ShapeType.Filled else ShapeType.Line
                renderContext.shapeRenderer.using(type) {
                    this.color = color
                    circle(
                        constraint.x.toFloat() + constraint.width / 2,
                        constraint.y.toFloat() + constraint.height / 2,
                        radius.toFloat(),
                        64
                    )
                }
            }
        }
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        return Size(radius * 2 + 2, radius * 2 + 2)
    }
}

fun Container.circle(block: Circle.() -> Unit) {
    val circle = Circle()
    block(circle)
    addChild(circle)
}
