package io.github.callmephil.parsecs.graphics

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

fun ShapeRenderer.border(
    x: Float, y: Float,
    width: Float, height: Float,
    borderThickness: Float, color: Color
) {
    this.color = color
    // Bottom
//        line(x, y + borderOffset, x + width, y)
    rect(x, y, width, borderThickness)

    // Top
//        line(x, y + height, x + width, y + height)
    rect(x, y + height - borderThickness, width, borderThickness)

    // Left
//        line(x, y - borderOffset, x, y + height + borderOffset)
    rect(x, y, borderThickness, height)

    // Right
//        line(x + width, y - borderOffset, x + width, y + height + borderOffset)
    rect(x + width - borderThickness, y, borderThickness, height)
}

fun ShapeRenderer.using(type: ShapeRenderer.ShapeType, block: ShapeRenderer.() -> Unit) {
    this.begin(type)
    block()
    this.end()
}
