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
    //private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                getCoursesUseCase().collect { courses ->
                    _uiState.value = _uiState.value.copy(
                        courses = courses,
                        originalCourses = courses,
                        isLoading = false,
                        error = null,
                        isSorted = false
                    )
                }
            } catch (e: Exception) {
                when (e) {
                    is java.net.UnknownHostException,
                    is java.io.IOException -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Нет подключения к интернету"
                        )
                    }
                    else -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Ошибка загрузки данных"
                        )
                    }
                }
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
        val currentState = _uiState.value
        if (currentState.isSorted) {
            _uiState.value = currentState.copy(
                courses = currentState.originalCourses,
                isSorted = false
            )
        } else {
            val sorted = currentState.originalCourses
                .sortedByDescending { it.publishDate }
            _uiState.value = currentState.copy(
                courses = sorted,
                isSorted = true
            )
        }
    }
}

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val originalCourses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSorted: Boolean = false
)
