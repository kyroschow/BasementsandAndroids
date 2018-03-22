package com.example.chichow25.basementsandandroids.gamedata.models

/**
 * Created by chichow25 on 3/21/18.
 */

data class Armor(
		val _id: String,
		val index: Int,
		val name: String,
		val equipment_category: String,
		val armor_category: String,
		val armor_class: ArmorClass,
		val str_minimum: Int,
		val stealth_disadvantage: Boolean,
		val weight: Int,
		val cost: Cost,
		val url: String
)

data class ArmorClass(
		val base: Int,
		val dex_bonus: Boolean,
		val max_bonus: Any
)