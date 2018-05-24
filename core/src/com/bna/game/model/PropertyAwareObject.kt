package com.bna.game.model

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class PropertyAwareObject {
    //list of listeners
    val listeners by lazy{ mutableListOf<PropertyAwareListener>() }
    //stores property changes until they are fired
    val cumulativePropertyInfos by lazy { mutableListOf<PropertyChangeInfo>() }
    //change this to enable/disable instant notification of updates
    var instantUpdate = true

    fun addPropertyChangeListener(l: PropertyAwareListener) = listeners.add(l)

    inline fun addPropertyChangeListener(crossinline l: (Array<out PropertyChangeInfo>) -> Unit): PropertyAwareListener {
        val listener = object: PropertyAwareListener {
            override fun onPropertyChange(vararg infos: PropertyChangeInfo) {
                l(infos)
            }

        }
        addPropertyChangeListener(listener)
        return listener
    }

    protected open fun changeHandler(prop: KProperty<*>, old: Any, new: Any) {
        if (instantUpdate) { //fires property changes instantly, property by property
            listeners.forEach {
                val info = PropertyChangeInfo(this, prop.name, old, new)
                it.onPropertyChange(info)
            }
        } else { //fires a list of property changes, >1 properties at the same time
            cumulativePropertyInfos.add(PropertyChangeInfo(this, prop.name, old, new))
        }
    }

    fun removePropertyChangeListener(l: PropertyAwareListener) = listeners.remove(l)

    protected inline fun <T: Any> observableProperty(initialValue: T, crossinline handler: (KProperty<*>, T, T) -> Unit = ::changeHandler)
            = Delegates.observable(initialValue, handler)
}

inline fun <T: PropertyAwareObject> T.applyCumulativeChanges(change: T.() -> Unit) {
    instantUpdate = false //disable instance updates
    apply(change)
    //fire cumulative property changes
    listeners.forEach{ it.onPropertyChange(*cumulativePropertyInfos.toTypedArray()) }
    cumulativePropertyInfos.clear()
    instantUpdate = true //re-enable instant updates
}

interface PropertyAwareListener {
    fun onPropertyChange(vararg infos: PropertyChangeInfo)
}

data class PropertyChangeInfo(val parent: Any, val varName: String, val old: Any, val new: Any)