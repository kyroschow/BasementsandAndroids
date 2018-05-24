package com.bna.game.model

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class PropertyAwareObject {
    private val listeners by lazy{ mutableListOf<PropertyChangeListener>() }

    fun addPropertyChangeListener(l: PropertyChangeListener) = listeners.add(l)

    inline fun addPropertyChangeListener(crossinline l: (String, Any, Any) -> Unit): PropertyChangeListener {
        val listener = object: PropertyChangeListener {
            override fun onPropertyChange(varName: String, old: Any, new: Any) {
                l(varName, old, new)
            }
        }
        addPropertyChangeListener(listener)
        return listener
    }

    protected fun observer(prop: KProperty<*>, old: Any, new: Any) {
        listeners.forEach {
            it.onPropertyChange(prop.name, old, new)
        }
    }

    fun removePropertyChangeListener(l: PropertyChangeListener) = listeners.remove(l)

    protected inline fun <T: Any> observableProperty(initialValue: T, crossinline handler: (KProperty<*>, T, T) -> Unit = ::observer)
            = Delegates.observable(initialValue, handler)
}

interface PropertyChangeListener {
    fun onPropertyChange(varName: String, old: Any, new: Any)
}

data class PropertyChangeInfo(val varName: String, val old: Any, val new: Any)