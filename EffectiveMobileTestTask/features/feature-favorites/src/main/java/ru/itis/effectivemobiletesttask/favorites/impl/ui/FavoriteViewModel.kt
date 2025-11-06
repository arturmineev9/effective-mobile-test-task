package ru.itis.effectivemobiletesttask.favorites.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.favorites.api.usecase.GetFavoriteCoursesUseCase

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            getFavoriteCoursesUseCase().collect { courses ->
                _uiState.value = _uiState.value.copy(courses = courses)
            }
        }
    }

    fun onFavoriteClick(course: Course) {
        // TODO: убрать из избранного (опционально по ТЗ)
    }
}

data class FavoriteUiState(
    val courses: List<Course> = emptyList()
)