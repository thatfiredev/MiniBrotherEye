package io.github.rosariopfernandes.minibrothereye.model

data class Character(
    val id: Int = 1,
    val name: String = "",
    val powerStats: PowerStats = PowerStats(),
    val appearance: Appearance = Appearance(),
    val biography: Biography = Biography(),
    val images: Images = Images()
)

data class PowerStats(
    val intelligence: Int = 0,
    val strength: Int = 0,
    val speed: Int = 0,
    val durability: Int = 0,
    val power: Int = 0,
    val combat: Int = 0
)

data class Appearance(
    val gender: String = "",
    val race: String? = null,
    val height: List<String> = listOf(),
    val weight: List<String> = listOf(),
    val eyeColor: String = "",
    val hairColor: String = ""
)

data class Biography(
    val fullName: String = "",
    val alterEgos: String = "",
    val aliases: List<String> = listOf(),
    val placeOfBirth: String = "",
    val firstAppearance: String = "",
    val alignment: String = ""
)

data class Images(
    val md: String = ""
)
