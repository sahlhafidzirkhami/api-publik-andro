package com.example.superheroapi.domain.repository

import com.example.superheroapi.data.remote.ApiService
import com.example.superheroapi.data.remote.dto.SuperheroDetailDto
import com.example.superheroapi.domain.model.Superhero
import com.example.superheroapi.domain.repository.SuperheroRepository
import javax.inject.Inject

class SuperheroRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SuperheroRepository {
    override suspend fun getSuperheroDetail(id: String): Superhero {
        // Panggil API dan langsung map ke domain model
        return apiService.getSuperheroDetail(id).toDomain()
    }
}

// Extension function untuk mapping DTO ke Domain Model
private fun SuperheroDetailDto.toDomain(): Superhero {
    // Fungsi untuk mengubah string stat menjadi Int, dengan default 0 jika null/bukan angka
    fun String?.toIntOrZero() = this?.toIntOrNull() ?: 0

    return Superhero(
        id = this.id,
        name = this.name,
        fullName = this.biography.fullName,
        imageUrl = this.image.url,
        intelligence = this.powerstats.intelligence.toIntOrZero(),
        strength = this.powerstats.strength.toIntOrZero(),
        speed = this.powerstats.speed.toIntOrZero(),
        durability = this.powerstats.durability.toIntOrZero(),
        power = this.powerstats.power.toIntOrZero(),
        combat = this.powerstats.combat.toIntOrZero(),
        gender = this.appearance.gender,
        race = this.appearance.race ?: "N/A",
        height = this.appearance.height.lastOrNull() ?: "-",
        weight = this.appearance.weight.lastOrNull() ?: "-",
        publisher = this.biography.publisher
    )
}