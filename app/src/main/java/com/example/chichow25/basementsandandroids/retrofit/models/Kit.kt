package com.example.chichow25.basementsandandroids.retrofit.models

/**
 * Created by chichow25 on 3/19/18.
 */

data class Kit(
		val _id: String,
		val index: Int,
		val name: String,
		val equipment_category: String,
		val gear_category: String,
		val cost: Cost,
		val weight: Int,
		val desc: List<String>,
		val url: String
)