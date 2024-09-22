package kraylib.ui.widgets

import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext

class Column : Container() {
    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        super.draw(renderContext, inputContext, constraint)

        val yOffset = constraint.height / children.size

        children.forEachIndexed { index, child ->
            val childConstraint = Constraint(
                x = constraint.x,
                y = constraint.y + yOffset * (children.size - index - 1),
                width = constraint.width,
                height = constraint.height / children.size
            )

            val childSize = child.size(renderContext, childConstraint.toSize())

            val drawConstraint = childConstraint.copy(
                y = childConstraint.y + (childConstraint.height - childSize.height) / 2,
                height = childSize.height
            )

            child.beginDraw(renderContext, inputContext, drawConstraint)
            child.draw(renderContext, inputContext, drawConstraint)
            child.endDraw(renderContext, drawConstraint)
        }
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        return availableSize
    }
}

fun Container.column(block: Column.() -> Unit) {
    val column = Column()
    block(column)
    addChild(column)
}
