package io.github.callmephil.parsecs.ui.modifiers

data class PaddingValues(
    val left: Float = 0f,
    val right: Float = 0f,
    val top: Float = 0f,
    val bottom: Float = 0f
) {
    companion object {
        val empty: PaddingValues = PaddingValues()
    }
}
