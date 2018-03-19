package com.example.chichow25.basementsandandroids.retrofit.responses

/**
 * Created by chichow25 on 3/19/18.
 */

data class OtherToolsResponse(
        val _id: String,
        val index: Int,
        val name: String,
        val equipment: List<Equipment>,
        val url: String
)