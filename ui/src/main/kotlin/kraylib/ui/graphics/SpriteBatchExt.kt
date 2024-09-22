package io.github.callmephil.parsecs.graphics

import com.badlogic.gdx.graphics.g2d.SpriteBatch

fun SpriteBatch.using(block: SpriteBatch.() -> Unit) {
    this.begin()
    block()
    this.end()
}
