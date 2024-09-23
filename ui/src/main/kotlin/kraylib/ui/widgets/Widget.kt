package kraylib.ui.widgets

import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext
import kraylib.ui.graphics.border
import io.github.callmephil.parsecs.ui.modifiers.PaddingValues
import io.github.callmephil.parsecs.ui.modifiers.Placement
import kraylib.raylib.shapes.drawRectangle
import kraylib.raylib.structs.Color

abstract class Widget {
    var width = -1
    var height = -1
    var padding: PaddingValues = PaddingValues()
    var placement: Placement = Placement.TopLeft

    var backgroundColor: Color = Color.TRANSPARENT
    var onHoverBackgroundColor: Color = Color.TRANSPARENT

    var borderColor: Color = Color.TRANSPARENT
    var onHoverBorderColor: Color = Color.TRANSPARENT
    var borderThickness = 0

    var focused: Boolean = false

    protected var default: () -> Unit = {}
    protected var onHovered: () -> Unit = {}
    protected var onTouchDown: () -> Unit = {}
    protected var stencil: (RenderContext, Constraint) -> Unit = { _, _ -> }

    open fun default(block: () -> Unit) { default = block }
    open fun onHovered(block: () -> Unit) { onHovered = block }
    open fun stencil(block: (RenderContext, Constraint) -> Unit) { stencil = block }

    fun beginDraw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        renderContext.stencilContext.startStencil()
        renderContext.stencilContext.updateStencil {
            stencil.invoke(renderContext, constraint)
        }
    }

    open fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        renderContext.stencilContext.withStencil {
            val hovering = constraint.containsPoint(inputContext.x, inputContext.y)

            default()
            if (hovering) onHovered()

            drawBackground(renderContext, constraint, backgroundColor)

            drawBorder(renderContext, constraint, borderThickness, borderColor)
        }
    }

    protected open fun drawBackground(
        renderContext: RenderContext, constraint: Constraint,
        color: Color
    ) {
        if (color != Color.TRANSPARENT) {
            drawRectangle(
                constraint.x,
                constraint.y,
                constraint.width,
                constraint.height,
                color
            )
        }
    }

    protected open fun drawBorder(
        renderContext: RenderContext, constraint: Constraint,
        borderThickness: Int, color: Color
    ) {
        if (borderThickness > 0 && color != Color.TRANSPARENT) {
            border(
                constraint.x,
                constraint.y,
                constraint.width,
                constraint.height,
                borderThickness,
                color
            )
        }
    }

    fun endDraw(renderContext: RenderContext, constraint: Constraint) {
        renderContext.stencilContext.endStencil()
    }

    abstract fun size(renderContext: RenderContext, availableSize: Size): Size

    open fun processInput(x: Int, y: Int, inside: Boolean) {}

    open fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean = false

    open fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean = false

    open fun touchCancelled(p0: Int, p1: Int, p2: Int, p3: Int): Boolean = false

    open fun touchDragged(x: Int, y: Int, pointer: Int): Boolean = false

    open fun mouseMoved(x: Int, y: Int): Boolean = false
}
