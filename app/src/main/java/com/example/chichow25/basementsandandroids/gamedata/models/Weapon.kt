package com.example.chichow25.basementsandandroids.gamedata.models

import com.google.gson.annotations.SerializedName

/**
 * Created by chichow25 on 3/21/18.
 */

data class Weapon(
		val _id: String,
		val index: Int,
		val name: String,
		val equipment_category: String,
        @SerializedName("weapon_category:")
		val weapon_category: String,
		val weapon_range: String,
		val category_range: String,
		val cost: Cost,
		val damage: Damage,
		val range: Range,
		val weight: Double,
		val properties: List<Property>,
		val url: String
)

data class Range(
		val normal: Int
)

data class Property(
		val name: String,
		val url: String
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