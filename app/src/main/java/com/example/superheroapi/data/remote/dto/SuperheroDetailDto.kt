package com.example.superheroapi.data.remote.dto

import com.google.gson.annotations.SerializedName

// File: SuperheroDetailDto.kt
data class SuperheroDetailDto(
    val id: String,
    val name: String,
    val powerstats: PowerstatsDto,
    val biography: BiographyDto,
    val appearance: AppearanceDto,
    val work: WorkDto,
    val connections: ConnectionsDto,
    val image: ImageDto
)

// File: PowerstatsDto.kt
data class PowerstatsDto(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

// File: BiographyDto.kt
data class BiographyDto(
    @SerializedName("full-name") val fullName: String,
    val aliases: List<String>,
    @SerializedName("place-of-birth") val placeOfBirth: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    val publisher: String,
    val alignment: String
)

// File: AppearanceDto.kt
data class AppearanceDto(
    val gender: String,
    val race: String?,
    val height: List<String>,
    val weight: List<String>,
    @SerializedName("eye-color") val eyeColor: String,
    @SerializedName("hair-color") val hairColor: String
)

// File: WorkDto.kt
data class WorkDto(
    val occupation: String,
    val base: String
)

// File: ConnectionsDto.kt
data class ConnectionsDto(
    @SerializedName("group-affiliation") val groupAffiliation: String,
    val relatives: String
)

// File: ImageDto.kt
data class ImageDto(
    val url: String
)