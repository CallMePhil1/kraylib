package io.github.callmephil.parsecs.graphics.gl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL32
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

class StencilContext(
    internal var pixmap: Pixmap,
    textureRegion: TextureRegion
) {
    internal var depth: Int = -1
    internal var texture: TextureRegion = textureRegion

    constructor(width: Int, height: Int) : this(
        Pixmap(width, height, Pixmap.Format.Alpha),
        TextureRegion(Texture(width, height, Pixmap.Format.Alpha)).apply { flip(false, true) }
    )

    fun draw(x: Int, y: Int) {
        texture.texture.draw(pixmap, x, y)
    }

    fun endStencil() {
        depth -= 1
    }

    fun resize(width: Int, height: Int) {
        pixmap.dispose()
        texture.texture.dispose()

        pixmap = Pixmap(width, height, Pixmap.Format.Alpha)
        texture = TextureRegion(Texture(width, height, Pixmap.Format.Alpha))
    }

    fun startStencil() {
        depth += 1
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

    fun updateStencil(block: () -> Unit) = updateStencil(depth, block)

    fun withStencil(mask: Int, draw: () -> Unit) {
        Gdx.gl.glEnable(GL32.GL_STENCIL_TEST)
        Gdx.gl.glStencilMask(0xFF)
        Gdx.gl.glStencilFunc(GL32.GL_ALWAYS, mask, 0xFF)
        Gdx.gl.glStencilOp(GL32.GL_KEEP, GL32.GL_KEEP, GL32.GL_REPLACE)
        Gdx.gl.glColorMask(true, true, true, true)

        draw()

        Gdx.gl.glDisable(GL32.GL_STENCIL_TEST)
    }

    fun withStencil(block: () -> Unit) = withStencil(depth, block)
}
