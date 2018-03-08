package com.example.chichow25.basementsandandroids.gamedata

import com.google.gson.annotations.SerializedName

/**
 * Created by chichow25 on 3/8/18.
 */

data class Equipment(
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

data class Cost(
		val quantity: Int,
		val unit: String
)

data class Damage(
		val dice_count: Int,
		val dice_value: Int,
		val damage_type: DamageType
)

data class DamageType(
		val url: String,
		val name: String
)