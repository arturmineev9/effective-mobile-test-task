package ru.itis.effectivemobiletesttask.feature_main.api.usecase

import ru.itis.effectivemobiletesttask.core_common.model.Course
interface ToggleFavoriteUseCase {
    suspend operator fun invoke(course: Course)
}
