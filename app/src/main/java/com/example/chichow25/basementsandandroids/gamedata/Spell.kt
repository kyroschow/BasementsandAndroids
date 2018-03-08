package com.example.chichow25.basementsandandroids.gamedata

/**
 * Created by chichow25 on 3/8/18.
 */

data class Spell(
        val _id: String,
        val index: Int,
        val name: String,
        val desc: List<String>,
        val higher_level: List<String>,
        val page: String,
        val range: String,
        val components: List<String>,
        val material: String,
        val ritual: String,
        val duration: String,
        val concentration: String,
        val casting_time: String,
        val level: Int,
        val school: School,
        val classes: List<Class>,
        val subclasses: List<Subclass>,
        val url: String
)

data class Class(
		val name: String,
		val url: String
)

data class School(
		val url: String,
		val name: String
)

data class Subclass(
		val url: String,
		val name: String
)