package kraylib.ui.widgets

import io.github.callmephil.parsecs.ui.modifiers.Alignment
import io.github.callmephil.parsecs.ui.modifiers.Arrangement

abstract class Container : Widget() {
    var alignment: Alignment = Alignment.Start
    var arrangement: Arrangement = Arrangement.Equal
    protected val children: MutableList<Widget> = mutableListOf()

    open fun addChild(child: Widget) {
        children.add(child)
    }
}
