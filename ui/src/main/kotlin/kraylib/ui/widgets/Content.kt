package kraylib.ui.widgets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Buttons
import io.github.callmephil.parsecs.InputContext
import kraylib.ui.RenderContext

class Content : Container() {
    override fun addChild(child: Widget) {
        if (children.size > 0)
            return
        children.add(child)
    }

    fun draw(renderContext: RenderContext, constraint: Constraint) {
        val inputContext = InputContext(
            x = Gdx.input.x,
            y = Gdx.graphics.height - Gdx.input.y,
            Gdx.input.isButtonPressed(Buttons.LEFT)
        )

        draw(renderContext, inputContext, constraint)
    }

    override fun draw(renderContext: RenderContext, inputContext: InputContext, constraint: Constraint) {
        super.draw(renderContext, inputContext, constraint)

        val child = children[0]
        child.beginDraw(renderContext, inputContext, constraint)
        child.draw(renderContext, inputContext, constraint)
        child.endDraw(renderContext, constraint)
    }

    override fun size(renderContext: RenderContext, availableSize: Size): Size {
        return availableSize
    }
}

fun content(block: Container.() -> Unit): Content {
    val content = Content()
    block(content)
    return content
}
