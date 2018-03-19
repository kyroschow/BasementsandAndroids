package com.example.chichow25.basementsandandroids.gamedata.retrofit

/**
 * Created by chichow25 on 3/15/18.
 */

data class RaceResponse(
        val _id: String,
        val index: Int,
        val name: String,
        val speed: Int,
        val ability_bonuses: List<Int>,
        val alignment: String,
        val age: String,
        val size: String,
        val size_description: String,
        val starting_proficiencies: List<StartingProficiency>,
        val languages: List<Language>,
        val language_desc: String,
        val traits: List<Trait>,
        val subraces: List<Subrace>,
        val url: String
)

data class Trait(
		val url: String,
		val name: String
)

data class Language(
		val name: String,
		val url: String
)

data class StartingProficiency(
		val name: String,
		val url: String
)

data class Subrace(
		val name: String,
		val url: String
)