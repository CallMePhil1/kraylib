package kraylib.ui.widgets

import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext

class Button : Container() {

    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {

        val width = if (width != -1) minOf(constraint.width, width) else constraint.width
        val height = if (height != -1) minOf(constraint.height, height) else constraint.height

        val childConstraint = Constraint(
            x = constraint.x,
            y = constraint.y,
            width = width,
            height = height
        )

        super.draw(renderContext, inputContext, childConstraint)

        val child = children.getOrNull(0) ?: return
        val childSize = child.size(renderContext, childConstraint.toSize())

        val drawConstraint = childConstraint.copy(
            x = constraint.x + constraint.width / 2 - childSize.width / 2,
            y = constraint.y + constraint.height / 2 - childSize.height / 2,
            width = childSize.width,
            height = childSize.height
        )

        child.beginDraw(renderContext, inputContext, drawConstraint)
        child.draw(renderContext, inputContext, drawConstraint)
        child.endDraw(renderContext, drawConstraint)
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        return availableSize
    }
}

fun Container.button(block: Button.() -> Unit) {
    val button = Button()
    block(button)
    addChild(button)
}
