package io.github.callmephil.parsecs.ui.modifiers

sealed class Arrangement {
    data object Center : Arrangement()
    data object End : Arrangement()
    data object Equal : Arrangement()
    data object Start : Arrangement()
    data object SpaceAround : Arrangement()
    data object SpaceBetween : Arrangement()
    data object SpaceEvenly : Arrangement()
}
