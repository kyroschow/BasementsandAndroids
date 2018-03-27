package com.example.chichow25.basementsandandroids.repo.gamedata

/**
 * Created by chichow25 on 3/21/18.
 */

data class EquipmentCategory(
		val index: Int,
		val name: String,
		val equipment: List<Equipment>,
		val url: String
)

data class Equipment(
		val name: String,
		val url: String
)