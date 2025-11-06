package ru.itis.effectivemobiletesttask.feature_main.impl.remote.api

import retrofit2.http.GET
import retrofit2.http.Url
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.model.CourseResponse

interface CourseApiService {
    @GET
    suspend fun getCourses(@Url url: String): CourseResponse
}
