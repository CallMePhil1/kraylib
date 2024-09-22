package io.github.callmephil.parsecs.graphics.gl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL32
import com.badlogic.gdx.math.Rectangle

object Opengl {
    fun clearAll(color: Color = Color.BLACK) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a)
        Gdx.gl.glClear(GL32.GL_COLOR_BUFFER_BIT or GL32.GL_DEPTH_BUFFER_BIT or GL32.GL_STENCIL_BUFFER_BIT)
    }

    fun scissorTest(x: Int, y: Int, width: Int, height: Int, block: () -> Unit) {
        Gdx.gl.glEnable(GL32.GL_SCISSOR_TEST)
        Gdx.gl.glScissor(x, y, width, height)

        block()

        Gdx.gl.glDisable(GL32.GL_SCISSOR_TEST)
    }

    fun scissorTest(bounds: Rectangle, block: () -> Unit) = scissorTest(
        bounds.x.toInt(), bounds.y.toInt(),
        bounds.width.toInt(), bounds.height.toInt(),
        block
    )

    fun stencilBegin(mask: Int) {
        Gdx.gl.glEnable(GL32.GL_STENCIL_TEST)
        Gdx.gl.glStencilMask(0xFF)
        Gdx.gl.glStencilFunc(GL32.GL_ALWAYS, mask, 0xFF)
        Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
        Gdx.gl.glColorMask(false, false, false, false)
    }

    fun stencilEnd(mask: Int) {
        Gdx.gl.glColorMask(true, true, true, true)
        Gdx.gl.glDisable(GL32.GL_STENCIL_TEST)
    }

    fun updateStencil(mask: Int, block: () -> Unit) {
        Gdx.gl.glEnable(GL32.GL_STENCIL_TEST)
        Gdx.gl.glStencilMask(0xFF)
        Gdx.gl.glStencilFunc(GL32.GL_ALWAYS, mask, 0xFF)
        Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
        Gdx.gl.glColorMask(false, false, false, false)

        block()

        Gdx.gl.glDisable(GL32.GL_STENCIL_TEST)
    }

    fun withStencil(mask: Int, draw: () -> Unit) {
        Gdx.gl.glEnable(GL32.GL_STENCIL_TEST)
        Gdx.gl.glStencilMask(0xFF)
        Gdx.gl.glStencilFunc(GL32.GL_ALWAYS, mask, 0xFF)
        Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
        Gdx.gl.glColorMask(true, true, true, true)

        draw()

        Gdx.gl.glDisable(GL32.GL_STENCIL_TEST)
    }
}
