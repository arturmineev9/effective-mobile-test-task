package ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource

import jakarta.inject.Inject
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.api.CourseApiService
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.model.CourseDto

interface CourseRemoteDataSource {
    suspend fun fetchCourses(): List<CourseDto>
}


class CourseRemoteDataSourceImpl @Inject constructor(
    private val apiService: CourseApiService
) : CourseRemoteDataSource {
    override suspend fun fetchCourses(): List<CourseDto> {
        val url = "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
        return apiService.getCourses(url).courses
    }
}
