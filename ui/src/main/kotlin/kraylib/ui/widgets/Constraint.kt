package kraylib.ui.widgets

import com.badlogic.gdx.math.Rectangle

data class Constraint(val x: Int, val y: Int, val width: Int, val height: Int) {
    fun containsPoint(x: Int, y: Int): Boolean {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height
    }
    fun toRectangle() = Rectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
    fun toSize() = Size(width, height)
}
