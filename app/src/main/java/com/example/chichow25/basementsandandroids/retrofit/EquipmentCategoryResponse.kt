package com.example.chichow25.basementsandandroids.retrofit

/**
 * Created by chichow25 on 3/19/18.
 */

data class EquipmentCategoryResponse(
		val count: Int,
		val results: List<Result>
)

data class Result(
		val name: String,
		val url: String
)