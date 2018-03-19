package com.example.chichow25.basementsandandroids.retrofit.models

/**
 * Created by chichow25 on 3/19/18.
 */

data class Shield(
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