package com.example.superheroapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapi.domain.model.Superhero
import com.example.superheroapi.domain.usecase.GetSuperheroDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    data object Loading : UiState()
    data class Success(val superhero: Superhero) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSuperheroDetailUseCase: GetSuperheroDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchSuperheroDetail(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val superhero = getSuperheroDetailUseCase(id)
                _uiState.value = UiState.Success(superhero)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}