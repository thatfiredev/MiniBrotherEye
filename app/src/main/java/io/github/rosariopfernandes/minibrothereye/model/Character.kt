package io.github.rosariopfernandes.minibrothereye.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int = 1,
    val name: String = "",
    @Embedded val powerstats: PowerStats = PowerStats(),
    @Embedded val appearance: Appearance = Appearance(),
    @Embedded val biography: Biography = Biography(),
    @Embedded val work: Work = Work(),
    @Embedded val connections: Connections = Connections(),
    @Embedded val images: Images = Images()
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
    val height: List<String> = listOf(),
    val weight: List<String> = listOf()
)

data class Biography(
    val fullName: String = "-",
    val alterEgos: String = "No alter egos found.",
    val aliases: List<String> = listOf(),
    val placeOfBirth: String = "-",
    val firstAppearance: String = "-",
    val alignment: String = ""
)

data class Work(val occupation: String = "-", val base: String = "-")

data class Connections(val groupAffiliation: String = "-", val relatives: String = "-")

data class Images(
    @ColumnInfo(name = "image_url") val md: String = ""
)
