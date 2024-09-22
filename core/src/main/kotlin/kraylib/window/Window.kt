package kraylib.window

import kraylib.FFM
import kraylib.ffm.Raylib
import kraylib.raylib.core.ConfigFlags
import kraylib.raylib.core.getWindowHandle
import kraylib.raylib.core.initWindow
import kraylib.raylib.core.setConfigFlags
import kraylib.raylib.structs.Vector2
import java.lang.foreign.MemorySegment

class Window(
    width: Int, height: Int, title: String,
    enableMSAA: Boolean = false, isHighDpi: Boolean = false,
    isTransparent: Boolean = false
) {
    var height: Int
        get() = Raylib.GetScreenHeight()
        set(value) = Raylib.SetWindowSize(width, value)

    var size: Vector2
        get() = Vector2().apply {
            x = width.toFloat()
            y = height.toFloat()
        }
        set(value) = Raylib.SetWindowSize(value.x.toInt(), value.y.toInt())

    var title: String = title
        set(value) {
            Raylib.SetWindowTitle(FFM.arena.allocateFrom(value, Charsets.UTF_8))
            field = value
        }

    var width: Int
        get() = Raylib.GetScreenWidth()
        set(value) = Raylib.SetWindowSize(value, height)

    var windowHandle: MemorySegment = MemorySegment.NULL
        private set

    init {
        var initFlags = 0

        if(enableMSAA) initFlags = initFlags or ConfigFlags.FLAG_MSAA_4X_HINT
        if(isHighDpi) initFlags = initFlags or ConfigFlags.FLAG_WINDOW_HIGHDPI
        if(isTransparent) initFlags = initFlags or ConfigFlags.FLAG_WINDOW_TRANSPARENT

        setConfigFlags(initFlags)
        initWindow(width, height, title)
        windowHandle = getWindowHandle()
    }
}