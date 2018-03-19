package com.example.chichow25.basementsandandroids.retrofit.models

/**
 * Created by chichow25 on 3/19/18.
 */

data class MountsAndVehicles(
		val _id: String,
		val index: Int,
		val name: String,
		val equipment_category: String,
		val vehicle_category: String,
		val cost: Cost,
		val speed: Speed,
		val capacity: String,
		val url: String
)

data class Speed(
		val quantity: Int,
		val unit: String
)