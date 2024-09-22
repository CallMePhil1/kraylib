package io.github.callmephil.parsecs.graphics

import com.badlogic.gdx.math.Rectangle
import kraylib.ui.widgets.Size

fun Rectangle.intersection(other: Rectangle) : Rectangle {
    val minX = maxOf(x, other.x)
    val minY = maxOf(y, other.y)

    val maxX = minOf(this.x + width,other.x + other.width)
    val maxY = minOf(this.y + height,other.y + other.height)

    val width = maxX - minX
    val height = maxY - minY

    return Rectangle(minX, minY, maxOf(0f, width), maxOf(0f, height))
}

fun Rectangle.toSize() = Size(width.toInt(), height.toInt())
