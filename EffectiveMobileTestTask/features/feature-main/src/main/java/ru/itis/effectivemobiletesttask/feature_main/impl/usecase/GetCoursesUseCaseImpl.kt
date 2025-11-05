package ru.itis.effectivemobiletesttask.feature_main.impl.usecase

import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_utils.IoDispatchers
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.GetCoursesUseCase

class GetCoursesUseCaseImpl @Inject constructor(
    @IoDispatchers private val coroutineDispatcher: CoroutineDispatcher,
) : GetCoursesUseCase {
    override suspend operator fun invoke(): Flow<List<Course>> {
        return withContext(coroutineDispatcher)
        {
            flowOf(
                listOf(
                    Course(
                        1,
                        "Java-разработчик",
                        "Описание...",
                        999,
                        4.9,
                        "2024-05-22",
                        false,
                        "2024-05-20"
                    ),
                    Course(2, "Kotlin", "Описание...", 1499, 4.8, "2024-06-01", true, "2024-04-10")
                )
            )
        }
    }
}