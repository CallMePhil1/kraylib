package kraylib.ui.graphics

import kraylib.raylib.shapes.drawRectangle
import kraylib.raylib.structs.Color

fun border(
    x: Int, y: Int,
    width: Int, height: Int,
    borderThickness: Int, color: Color
) {
    // Bottom
    drawRectangle(x, y, width, borderThickness, color)

    // Top
    drawRectangle(x, y + height - borderThickness, width, borderThickness, color)

    // Left
    drawRectangle(x, y, borderThickness, height, color)

    // Right
    drawRectangle(x + width - borderThickness, y, borderThickness, height, color)
}

//fun ShapeRenderer.using(type: ShapeRenderer.ShapeType, block: ShapeRenderer.() -> Unit) {
//    this.begin(type)
//    block()
//    this.end()
//}
