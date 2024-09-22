package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Vector2 as Vector2FFM
import kraylib.ffm.Vector3 as Vector3FFI
import kraylib.ffm.Vector4 as Vector4FFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Vector2(override var memorySegment: MemorySegment) : NativeMemory<Vector2>() {

    constructor() : this(Vector2FFM.allocate(FFM.arena))

    constructor(x: Float, y: Float) : this() {
        this.x = x
        this.y = y
    }

    var x: Float
        get() = Vector2FFM.x(memorySegment)
        set(value) = Vector2FFM.x(memorySegment, value)

    var y: Float
        get() = Vector2FFM.y(memorySegment)
        set(value) = Vector2FFM.y(memorySegment, value)

    companion object {
        fun empty() = Vector2(MemorySegment.NULL)
        fun zero() = Vector2()

        val byteSize = Vector2FFM.sizeof()
    }
}

class Vector3(
    x: Float,
    y: Float,
    z: Float
) {
    internal var memorySegment: MemorySegment = Vector3FFI.allocate(FFM.arena)

    var x: Float
        get() = Vector3FFI.x(memorySegment)
        set(value) = Vector3FFI.x(memorySegment, value)

    var y: Float
        get() = Vector3FFI.y(memorySegment)
        set(value) = Vector3FFI.y(memorySegment, value)

    var z: Float
        get() = Vector3FFI.z(memorySegment)
        set(value) = Vector3FFI.z(memorySegment, value)

    init {
        this.x = x
        this.y = y
        this.z = z
    }

    companion object {
        val ZERO = Vector3(0f, 0f, 0f)
    }
}

class Vector4(
    x: Float,
    y: Float,
    z: Float,
    w: Float
) {
    internal var memorySegment: MemorySegment = Vector4FFM.allocate(FFM.arena)

    var x: Float
        get() = Vector4FFM.z(memorySegment)
        set(value) = Vector4FFM.z(memorySegment, value)

    var y: Float
        get() = Vector4FFM.z(memorySegment)
        set(value) = Vector4FFM.z(memorySegment, value)

    var z: Float
        get() = Vector4FFM.z(memorySegment)
        set(value) = Vector4FFM.z(memorySegment, value)

    var w: Float
        get() = Vector4FFM.w(memorySegment)
        set(value) = Vector4FFM.w(memorySegment, value)

    init {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }

    companion object {
        val ZERO = Vector4(0f, 0f, 0f, 0f)
    }
}