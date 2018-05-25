package com.bna.game.model

import com.beust.klaxon.Json
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class PropertyAwareObject {
    @Json(ignored = true) //list of listeners
    val listeners by lazy { mutableListOf<PropertyAwareListener>() }
    @Json(ignored = true) //change this to enable/disable instant notification of updates
    var instantUpdate = true

    fun addPropertyChangeListener(l: PropertyAwareListener) = listeners.add(l)

    inline fun addPropertyChangeListener(crossinline l: (PropertyAwareObject) -> Unit): PropertyAwareListener {
        val listener = object : PropertyAwareListener {
            override fun onPropertyChange(propertyAwareObject: PropertyAwareObject) {
                l(propertyAwareObject)
            }

        }
        addPropertyChangeListener(listener)
        return listener
    }

    protected open fun changeHandler(prop: KProperty<*>, old: Any, new: Any) {
        if (instantUpdate) listeners.forEach { it.onPropertyChange(this) }
    }

    fun removePropertyChangeListener(l: PropertyAwareListener) = listeners.remove(l)

    protected inline fun <T : Any> observableProperty(initialValue: T, crossinline handler: (KProperty<*>, T, T) -> Unit = ::changeHandler) = Delegates.observable(initialValue, handler)
}

inline fun <T : PropertyAwareObject> T.applyCumulativeChanges(change: T.() -> Unit) {
    instantUpdate = false //disable instance updates
    apply(change)
    listeners.forEach { it.onPropertyChange(this) } //emit all changes together
    instantUpdate = true //re-enable instant updates
}

interface PropertyAwareListener {
    fun onPropertyChange(propertyAwareObject: PropertyAwareObject)
}

data class PropertyChangeInfo(val parent: Any, val varName: String, val old: Any, val new: Any)