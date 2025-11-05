package ru.itis.effectivemobiletesttask.feature_main.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.GetCoursesUseCase
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.ToggleFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase().collect { courses ->
                _uiState.value = _uiState.value.copy(courses = courses)
            }
        }
    }

    fun onFavoriteClick(course: Course) {
        // Переключаем локально (UI-only пока)
        val updatedCourses = _uiState.value.courses.map {
            if (it.id == course.id) it.copy(hasLike = !it.hasLike) else it
        }
        _uiState.value = _uiState.value.copy(courses = updatedCourses)

        // TODO: сохранить в БД через toggleFavoriteUseCase
        /*viewModelScope.launch {
            toggleFavoriteUseCase(course.id)
        }*/
    }

    fun onSortClick() {
        val sorted = _uiState.value.courses.sortedByDescending { it.publishDate }
        _uiState.value = _uiState.value.copy(courses = sorted)
    }
}

data class MainUiState(
    val courses: List<Course> = emptyList()
)