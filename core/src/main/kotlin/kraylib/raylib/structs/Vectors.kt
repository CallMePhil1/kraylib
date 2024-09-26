package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Vector2 as Vector2FFM
import kraylib.ffm.Vector3 as Vector3FFM
import kraylib.ffm.Vector4 as Vector4FFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Vector2(
    memorySegment: MemorySegment = Vector2FFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

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
        val byteSize = Vector2FFM.layout().byteSize()
        fun empty() = Vector2(MemorySegment.NULL)
        fun zero() = Vector2()
    }
}

class Vector3(
    memorySegment: MemorySegment = Vector3FFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    var x: Float
        get() = Vector3FFM.x(memorySegment)
        set(value) = Vector3FFM.x(memorySegment, value)

    var y: Float
        get() = Vector3FFM.y(memorySegment)
        set(value) = Vector3FFM.y(memorySegment, value)

    var z: Float
        get() = Vector3FFM.z(memorySegment)
        set(value) = Vector3FFM.z(memorySegment, value)

    constructor(
        x: Float,
        y: Float,
        z: Float
    ) : this() {
        this.x = x
        this.y = y
        this.z = z
    }

    companion object {
        fun empty() = Vector3(MemorySegment.NULL)
        fun zero() = Vector3()
    }
}

class Vector4(
    memorySegment: MemorySegment = Vector4FFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

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

    constructor(
        x: Float,
        y: Float,
        z: Float,
        w: Float
    ) : this() {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }

    companion object {
        fun empty() = Vector4(MemorySegment.NULL)
        fun zero() = Vector4()
    }
}