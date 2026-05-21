package com.example.task2module6.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task2module6.domain.model.NobelPrize
import com.example.task2module6.domain.usecase.GetPrizesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NobelUiState {
    object Loading : NobelUiState()
    data class Success(val prizes: List<NobelPrize>) : NobelUiState()
    data class Error(val message: String) : NobelUiState()
}

val CATEGORIES = listOf("", "physics", "chemistry", "literature", "peace", "medicine", "economics")
val CATEGORY_LABELS = listOf("Все", "Физика", "Химия", "Литература", "Мир", "Медицина", "Экономика")

@HiltViewModel
class NobelViewModel @Inject constructor(
    private val getPrizesUseCase: GetPrizesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NobelUiState>(NobelUiState.Loading)
    val uiState: StateFlow<NobelUiState> = _uiState

    private val _selectedYear = MutableStateFlow<Int?>(null)
    val selectedYear: StateFlow<Int?> = _selectedYear

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory

    val years = (2024 downTo 1901).toList()

    init {
        loadPrizes()
    }

    fun setYear(year: Int?) {
        _selectedYear.value = year
        loadPrizes()
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
        loadPrizes()
    }

    fun loadPrizes() {
        viewModelScope.launch {
            _uiState.value = NobelUiState.Loading
            try {
                val prizes = getPrizesUseCase(
                    limit = 50,
                    year = _selectedYear.value,
                    category = _selectedCategory.value.ifEmpty { null }
                )
                _uiState.value = NobelUiState.Success(prizes)
            } catch (e: Exception) {
                _uiState.value = NobelUiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}
