package ru.itis.effectivemobiletesttask.feature_main.impl.repository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.feature_main.api.repository.CourseRepository
import ru.itis.effectivemobiletesttask.feature_main.impl.mapper.CourseMapper
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource.CourseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepositoryImpl @Inject constructor(
    private val remoteDataSource: CourseRemoteDataSource
) : CourseRepository {

    override fun getAllCourses(): Flow<List<Course>> = flow {
        val dtos = remoteDataSource.fetchCourses()
        val courses = CourseMapper.mapList(dtos)
        emit(courses)
    }
}
