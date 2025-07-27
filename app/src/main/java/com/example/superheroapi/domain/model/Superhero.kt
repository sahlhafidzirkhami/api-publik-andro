package com.example.superheroapi.domain.model

data class Superhero(
    val id: String,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int,
    val gender: String,
    val race: String,
    val height: String,
    val weight: String,
    val publisher: String
)