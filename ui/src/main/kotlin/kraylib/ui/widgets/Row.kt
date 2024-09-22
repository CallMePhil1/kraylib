package kraylib.ui.widgets

import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext

class Row : Container() {
    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        super.draw(renderContext, inputContext, constraint)

        if (children.size == 0)
            return

        val xOffset = constraint.width / children.size

        children.forEachIndexed { index, child ->
            val childConstraint = Constraint(
                x = constraint.x + (xOffset * index),
                y = constraint.y,
                width = constraint.width / children.size,
                height = constraint.height
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

fun Container.row(block: Row.() -> Unit) {
    val row = Row()
    block(row)
    addChild(row)
}
