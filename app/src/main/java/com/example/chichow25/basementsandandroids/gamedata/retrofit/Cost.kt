package com.example.chichow25.basementsandandroids.gamedata.retrofit

/**
 * Created by chichow25 on 3/15/18.
 */
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