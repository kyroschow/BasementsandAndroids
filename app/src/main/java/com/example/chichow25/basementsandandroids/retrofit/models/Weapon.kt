package com.example.chichow25.basementsandandroids.retrofit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by chichow25 on 3/19/18.
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
		val weight: Int,
		val properties: List<Property>,
		val url: String
)

data class Property(
		val name: String,
		val url: String
)

data class Range(
		val normal: Int,
		val long: Any
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