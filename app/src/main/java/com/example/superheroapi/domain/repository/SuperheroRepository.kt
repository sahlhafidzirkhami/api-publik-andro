package com.example.superheroapi.domain.repository

import com.example.superheroapi.domain.model.Superhero

interface SuperheroRepository {
    suspend fun getSuperheroDetail(id: String): Superhero
}