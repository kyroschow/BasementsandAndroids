package com.example.chichow25.basementsandandroids.gamedata.retrofit

import com.google.gson.annotations.SerializedName

/**
 * Created by chichow25 on 3/15/18.
 */

data class EquipmentResponse(
		val _id: String,
		val index: Int,
		val name: String,
		val type: String,
		@SerializedName("subtype:")
		val subtype: String,
		val weapon_range: String,
		val weapon_category: String,
		val cost: Cost,
		val damage: Damage,
		val weight: Int,
		val properties: List<String>,
		val url: String
)