package kraylib.raylib.camera

import kraylib.ffm.Raylib
import kraylib.raylib.structs.Camera3D
import kraylib.raylib.structs.Vector3

object CameraProjection {
    /** Perspective projection */
    const val CAMERA_PERSPECTIVE = 0
    /** Perspective projection */
    const val CAMERA_ORTHOGRAPHIC = 1
}

/**
 * @see CameraProjection
 */
fun updateCamera(camera: Camera3D, mode: Int) = Raylib.UpdateCamera(camera.memorySegment, mode)
fun updateCameraPro(camera: Camera3D, movement: Vector3, rotation: Vector3, zoom: Float) = Raylib.UpdateCameraPro(camera.memorySegment, movement.memorySegment, rotation.memorySegment, zoom)