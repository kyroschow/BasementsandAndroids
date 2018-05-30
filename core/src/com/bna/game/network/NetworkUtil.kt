package com.bna.game.network

import org.json.JSONObject

fun json(vararg map: Pair<String, Any>) = JSONObject().apply {
    map.forEach { put(it.first, it.second) }
}