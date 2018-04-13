package com.example.chichow25.basementsandandroids.repo.gamedata

data class Monster(
        val _id: String,
        val index: Int,
        val name: String,
        val size: String,
        val type: String,
        val subtype: String,
        val alignment: String,
        val armor_class: Int,
        val hit_points: Int,
        val hit_dice: String,
        val speed: String,
        val strength: Int,
        val dexterity: Int,
        val constitution: Int,
        val intelligence: Int,
        val wisdom: Int,
        val constitution_save: Int,
        val intelligence_save: Int,
        val wisdom_save: Int,
        val history: Int,
        val perception: Int,
        val damage_vulnerabilities: String,
        val damage_resistances: String,
        val damage_immunities: String,
        val condition_immunities: String,
        val senses: String,
        val languages: String,
        val challenge_rating: Int,
        val special_abilities: List<SpecialAbility>,
        val actions: List<Action>,
        val legendary_actions: List<LegendaryAction>,
        val url: String
)

data class Action(
		val attack_bonus: Int,
		val desc: String,
		val name: String
)

data class SpecialAbility(
		val attack_bonus: Int,
		val desc: String,
		val name: String
)

data class LegendaryAction(
		val attack_bonus: Int,
		val desc: String,
		val name: String
)