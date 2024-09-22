package kraylib.ui.widgets

import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext

class Box : Container() {

    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        super.draw(renderContext, inputContext, constraint)

        children.forEachIndexed { _, child ->
            val childSize = child.size(renderContext, constraint.toSize())

            val childConstraint = Constraint(
                x = constraint.x + constraint.width / 2 - childSize.width / 2,
                y = constraint.y + constraint.height / 2 - childSize.height / 2,
                width = childSize.width,
                height = childSize.height
            )

            child.beginDraw(renderContext, inputContext, childConstraint)
            child.draw(renderContext, inputContext, childConstraint)
            child.endDraw(renderContext, childConstraint)
        }
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        return availableSize
    }
}

fun Container.box(block: Box.() -> Unit) {
    val box = Box()
    block(box)
    addChild(box)
}
