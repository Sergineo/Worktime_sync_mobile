package io.hornet.worktimesync.data.remote.api

import io.hornet.worktimesync.data.remote.dto.EmployeeMeDto
import io.hornet.worktimesync.data.remote.dto.ScheduleMeDto
import io.hornet.worktimesync.data.remote.dto.UserMeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {
    @GET("api/users/me")
    suspend fun getUserMe(@Header("Authorization") token: String): Response<UserMeDto>

    @GET("api/employees/me")
    suspend fun getEmployeeMe(@Header("Authorization") token: String): Response<EmployeeMeDto>

    @GET("api/schedules/me")
    suspend fun getScheduleMe(@Header("Authorization") token: String): Response<ScheduleMeDto>
}