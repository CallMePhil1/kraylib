package io.github.callmephil.parsecs.ui.modifiers

sealed class Alignment {
    data object Start : Alignment()
    data object End : Alignment()
    data object Center : Alignment()
}
