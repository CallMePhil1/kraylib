package kraylib.ui

import io.github.callmephil.parsecs.graphics.gl.Opengl
import io.github.callmephil.parsecs.graphics.gl.ScissorStack
import io.github.callmephil.parsecs.graphics.gl.StencilContext

data class RenderContext(
    val scissorStack: ScissorStack,
    val stencilContext: StencilContext
) {
    fun drawStencil(mask: Int, block: () -> Unit) {
        Opengl.stencilBegin(mask)
        stencilContext.startStencil()
        block()
        stencilContext.endStencil()
//        Opengl.stencilEnd()
    }
}
