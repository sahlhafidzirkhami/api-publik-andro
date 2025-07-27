package com.example.superheroapi.domain.usecase

import com.example.superheroapi.domain.repository.SuperheroRepository
import javax.inject.Inject

class GetSuperheroDetailUseCase @Inject constructor(
    private val repository: SuperheroRepository
) {
    suspend operator fun invoke(id: String) = repository.getSuperheroDetail(id)
}