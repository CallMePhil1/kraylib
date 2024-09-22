package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Rectangle as RectangleFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Rectangle(
    x: Float,
    y: Float,
    width: Float,
    height: Float
) : NativeMemory<Rectangle>() {
    override var memorySegment: MemorySegment = RectangleFFM.allocate(FFM.arena)

    /** Rectangle top-left corner position x */
    var x: Float
        get() = RectangleFFM.x(memorySegment)
        set(value) = RectangleFFM.x(memorySegment, value)

    /** Rectangle top-left corner position y */
    var y: Float
        get() = RectangleFFM.y(memorySegment)
        set(value) = RectangleFFM.y(memorySegment, value)

    /** Rectangle width */
    var width: Float
        get() = RectangleFFM.width(memorySegment)
        set(value) = RectangleFFM.width(memorySegment, value)

    /** Rectangle height */
    var height: Float
        get() = RectangleFFM.height(memorySegment)
        set(value) = RectangleFFM.height(memorySegment, value)

    init {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }
}