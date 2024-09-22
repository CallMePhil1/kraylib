package io.github.callmephil.parsecs.graphics.gl

import com.badlogic.gdx.math.Rectangle
import io.github.callmephil.parsecs.graphics.intersection
import java.util.*

class ScissorStack {
    var bounds = Rectangle(
        -Float.MAX_VALUE / 2, -Float.MAX_VALUE / 2,
        Float.MAX_VALUE, Float.MAX_VALUE
    )
        private set

    private val stack = Stack<Rectangle>()

    fun pop() {
        bounds = stack.pop()
    }

    fun push(bounds: Rectangle) {
        val newBounds = this.bounds.intersection(bounds)
        stack.push(this.bounds)
        this.bounds = newBounds
    }
}
