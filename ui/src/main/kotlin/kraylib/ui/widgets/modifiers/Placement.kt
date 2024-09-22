package io.github.callmephil.parsecs.ui.modifiers

sealed class Placement {
    data object TopLeft: Placement()
    data object Top : Placement()
    data object TopRight : Placement()
    data object Right : Placement()
    data object BottomRight : Placement()
    data object Bottom : Placement()
    data object BottomLeft : Placement()
    data object Left : Placement()
    data object Center : Placement()
}
