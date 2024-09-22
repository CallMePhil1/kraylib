import kraylib.raylib.structs.Camera2D
import kraylib.raylib.core.*
import kraylib.raylib.shapes.drawCircle
import kraylib.raylib.structs.Color
import kraylib.window.Window

fun main() {
    System.loadLibrary("raylib")

    val window = Window(800, 800, "Windowing", enableMSAA = true)

    val camera = Camera2D()
//    camera.offset = Vector2(400f, 400f)
    camera.offset.x = 400f
    camera.offset.y = 400f
    camera.target.x = 100f
    camera.target.y = 100f
    camera.zoom = 1f

    while (!windowShouldClose()) {
        beginDrawing()
        beginMode2D(camera)

        clearBackground(Color.GREEN)

        drawCircle(100, 100, 100f, Color.RED)

        endMode2D()
        endDrawing()
    }
}