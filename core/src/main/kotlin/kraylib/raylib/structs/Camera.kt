package kraylib.raylib.structs

import kraylib.FFM
import kraylib.ffm.Camera2D as Camera2DFFM
import kraylib.ffm.Camera3D as Camera3DFFM
import kraylib.raylib.NativeMemory
import java.lang.foreign.MemorySegment

class Camera2D(
    memorySegment: MemorySegment = Camera2DFFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    var offset: Vector2 = Vector2(Camera2DFFM.offset(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    var target: Vector2 = Vector2(Camera2DFFM.target(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    var rotation: Float
        get() = Camera2DFFM.rotation(memorySegment)
        set(value) = Camera2DFFM.rotation(memorySegment, value)

    var zoom: Float
        get() = Camera2DFFM.zoom(memorySegment)
        set(value) = Camera2DFFM.zoom(memorySegment, value)
}

class Camera3D(
    memorySegment: MemorySegment = Camera3DFFM.allocate(FFM.arena)
) : NativeMemory(memorySegment) {

    /** Camera position */
    var position: Vector3 = Vector3(Camera3DFFM.position(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    /** Camera target it looks-at */
    var target: Vector3 = Vector3(Camera3DFFM.target(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    /** Camera up vector (rotation over its axis) */
    var up: Vector3 = Vector3(Camera3DFFM.up(memorySegment))
        set(value) {
            field.memorySegment.copyFrom(value.memorySegment)
        }

    /** Camera field-of-view aperture in Y (degrees) in perspective, used as near plane width in orthographic */
    var fovy: Float
        get() = Camera3DFFM.fovy(memorySegment)
        set(value) = Camera3DFFM.fovy(memorySegment, value)

    /** Camera projection: CAMERA_PERSPECTIVE or CAMERA_ORTHOGRAPHIC */
    var projection: Int
        get() = Camera3DFFM.projection(memorySegment)
        set(value) = Camera3DFFM.projection(memorySegment, value)

    constructor(
        position: Vector3,
        target: Vector3,
        up: Vector3,
        fovy: Float,
        projection: Int
    ) : this() {
        this.position = position
        this.target = target
        this.up = up
        this.fovy = fovy
        this.projection = projection
    }
}