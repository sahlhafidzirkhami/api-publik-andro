package com.example.superheroapi.data.remote

import com.example.superheroapi.data.remote.dto.SuperheroDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): SuperheroDetailDto
}