package com.bna.game

import com.badlogic.gdx.Gdx

inline fun <reified T> T.gdxLog(message: String?, exception: Throwable? = null, tag: String? = T::class.java.simpleName) = Gdx.app.log(tag, message, exception)