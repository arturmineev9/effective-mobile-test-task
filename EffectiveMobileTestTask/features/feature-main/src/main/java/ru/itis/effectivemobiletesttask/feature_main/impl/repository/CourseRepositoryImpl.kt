package ru.itis.effectivemobiletesttask.feature_main.impl.repository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.repository.FavoriteRepository
import ru.itis.effectivemobiletesttask.feature_main.api.repository.CourseRepository
import ru.itis.effectivemobiletesttask.feature_main.impl.mapper.CourseMapper
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource.CourseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepositoryImpl @Inject constructor(
    private val remoteDataSource: CourseRemoteDataSource,
    private val favoriteRepository: FavoriteRepository
) : CourseRepository {

    override fun getAllCourses(): Flow<List<Course>> {
        val remoteFlow = flow {
            val dtos = remoteDataSource.fetchCourses()
            emit(CourseMapper.mapList(dtos))
        }

        val favoriteFlow = favoriteRepository.getAllFavorites()
            .map { it.map { entity -> entity.id } }.distinctUntilChanged()

        return remoteFlow.combine(favoriteFlow) { courses, favoriteIds ->
            courses.map { course ->
                course.copy(hasLike = favoriteIds.contains(course.id))
            }
        }
    }
}
