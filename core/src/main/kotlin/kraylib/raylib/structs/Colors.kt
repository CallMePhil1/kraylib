package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Color as ColorFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Color internal constructor() : NativeMemory<Color>() {

    override var memorySegment: MemorySegment = ColorFFM.allocate(FFM.arena)

    var r = ColorFFM.r(memorySegment)
        private set
    var g = ColorFFM.g(memorySegment)
        private set
    var b = ColorFFM.b(memorySegment)
        private set
    var a = ColorFFM.a(memorySegment)
        private set

    constructor(r: Int, g: Int, b: Int, a: Int) : this() {
        ColorFFM.r(memorySegment, r.toByte())
        ColorFFM.g(memorySegment, g.toByte())
        ColorFFM.b(memorySegment, b.toByte())
        ColorFFM.a(memorySegment, a.toByte())
    }

    constructor(r: Float, g: Float, b: Float, a: Float) : this(
        (r.coerceIn(0f, 1f) * 255).toInt(),
        (g.coerceIn(0f, 1f) * 255).toInt(),
        (b.coerceIn(0f, 1f) * 255).toInt(),
        (a.coerceIn(0f, 1f) * 255).toInt()
    )

    companion object {
        val BLACK = Color(0, 0, 0, 255)
        val BLUE = Color(0, 0, 255, 255)
        val GREEN = Color(0, 255, 0, 255)
        val RED = Color(255, 0, 0, 255)
        val TRANSPARENT = Color(0, 0, 0, 0)
        val WHITE = Color(255, 255, 255, 255)
    }
}

