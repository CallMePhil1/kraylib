package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Color as ColorFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Color internal constructor(
    memorySegment: MemorySegment = ColorFFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    var r
        get() = ColorFFM.r(memorySegment)
        private set(value) = ColorFFM.r(memorySegment, value)

    var g
        get() = ColorFFM.g(memorySegment)
        private set(value) = ColorFFM.g(memorySegment, value)

    var b
        get() = ColorFFM.b(memorySegment)
        private set(value) = ColorFFM.b(memorySegment, value)

    var a
        get() = ColorFFM.a(memorySegment)
        private set(value) = ColorFFM.a(memorySegment, value)

    internal constructor(r: UByte, g: UByte, b: UByte, a: UByte) : this() {
        this.r = r.toByte()
        this.g = g.toByte()
        this.b = b.toByte()
        this.a = a.toByte()
    }

    companion object {
        val AQUAMARINE      = Color(127u, 255u, 255u, 255u)
        val AZURE           = Color(0u, 127u, 255u, 255u)
        val BLACK           = Color(0u, 0u, 0u, 255u)
        val BLUE            = Color(0u, 0u, 255u, 255u)
        val BLUSH_PINK      = Color(255u, 127u, 255u, 255u)
        val CYAN            = Color(0u, 255u, 255u, 255u)
        val DOLLY           = Color(255u, 255u, 127u, 255u)
        val ELECTRIC_VIOLET = Color(127u, 0u, 255u, 255u)
        val FUCHSIA         = Color(255u, 0u, 255u, 255u)
        val GRAY            = Color(127u, 127u, 127u, 255u)
        val GREEN           = Color(0u, 255u, 0u, 255u)
        val GREY            = GRAY
        val MALIBU          = Color(127u, 127u, 255u, 255u)
        val MING_GREEN      = Color(127u, 255u, 127u, 255u)
        val ORANGE          = Color(255u, 127u, 0u, 255u)
        val RADIUM          = Color(127u, 255u, 0u, 255u)
        val RED             = Color(255u, 0u, 0u, 255u)
        val ROSE            = Color(255u, 0u, 127u, 255u)
        val SPRING_GREEN    = Color(0u, 255u, 127u, 255u)
        val TEAL            = Color(0u, 127u, 127u, 255u)
        val TRANSPARENT     = Color(0u, 0u, 0u, 0u)
        val VIVID_TANGERINE = Color(255u, 127u, 127u, 255u)
        val WHITE           = Color(255u, 255u, 255u, 255u)
        val YELLOW          = Color(255u, 255u, 0u, 255u)

        fun rgb(hex: Int)  = rgb(0xFF and hex, 0x00FF and hex, 0x0000FF and hex)
        fun rgb(hex: UInt) = rgb(0xFFU and hex, 0x00FFU and hex, 0x0000FFU and hex)
        fun rgb(hex: Long) = rgb(0xFFL and hex, 0x00FFL and hex, 0x0000FFL and hex)
        fun rgb(r: Byte, g: Byte, b: Byte) = rgb(r.toUByte(), g.toUByte(), b.toUByte())
        fun rgb(r: UByte, g: UByte, b: UByte) = Color(r, g, b,0xFFu)
        fun rgb(r: Int, g: Int, b: Int) = rgb(r.toUByte(), g.toUByte(), b.toUByte())
        fun rgb(r: UInt, g: UInt, b: UInt) = rgb(r.toUByte(), g.toUByte(), b.toUByte())
        fun rgb(r: Long, g: Long, b: Long) = rgb(r.toUByte(), g.toUByte(), b.toUByte())
        fun rgb(r: Float, g: Float, b: Float) = rgb(
            (r.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
            (g.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
            (b.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
        )

        fun rgba(hex: Int)  = rgba(0xFF and hex, 0x00FF and hex, 0x0000FF and hex, -1 and hex)
        fun rgba(hex: UInt) = rgba(0xFFu and hex,0x00FFu and hex,0x0000FFu and hex,0x000000FFu and hex)
        fun rgba(hex: Long) = rgba(0xFFL and hex,0x00FFL and hex,0x0000FFL and hex,0x000000FFL and hex)
        fun rgba(r: Byte, g: Byte, b: Byte, a: Byte) = rgba(r.toUByte(), g.toUByte(), b.toUByte(), a.toUByte())
        fun rgba(r: UByte, g: UByte, b: UByte, a: UByte) = Color(r, g, b, a)
        fun rgba(r: Int, g: Int, b: Int, a: Int) = rgba(r.toUByte(), g.toUByte(), b.toUByte(), a.toUByte())
        fun rgba(r: UInt, g: UInt, b: UInt, a: UInt) = rgba(r.toUByte(), g.toUByte(), b.toUByte(), a.toUByte())
        fun rgba(r: Long, g: Long, b: Long, a: Long) = rgba(r.toUByte(), g.toUByte(), b.toUByte(), a.toUByte())
        fun rgba(r: Float, g: Float, b: Float, a: Float) = rgba(
            (r.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
            (g.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
            (b.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte(),
            (a.coerceIn(0.0F, 1.0F) * 255).toUInt().toUByte()
        )
    }
}

