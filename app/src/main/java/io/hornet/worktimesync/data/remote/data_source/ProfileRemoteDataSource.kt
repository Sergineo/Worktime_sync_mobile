package io.hornet.worktimesync.data.remote.data_source

import io.hornet.worktimesync.data.remote.api.ProfileApi
import io.hornet.worktimesync.data.remote.dto.EmployeeMeDto
import io.hornet.worktimesync.data.remote.dto.ScheduleMeDto
import io.hornet.worktimesync.data.remote.dto.UserMeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class ProfileRemoteDataSource(
    private val profileApi: ProfileApi
) {
    suspend fun getFullProfileDataRaw(token: String): Result<Triple<UserMeDto, EmployeeMeDto, ScheduleMeDto>> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                val bearerToken = if (token.startsWith("Bearer ")) token else "Bearer $token"

                // Параллельный вызов трех методов API
                supervisorScope {
                    val userDef = async { profileApi.getUserMe(bearerToken) }
                    val employeeDef = async { profileApi.getEmployeeMe(bearerToken) }
                    val scheduleDef = async { profileApi.getScheduleMe(bearerToken) }

                    val userRes = userDef.await()
                    val employeeRes = employeeDef.await()
                    val scheduleRes = scheduleDef.await()

                    if (userRes.isSuccessful && employeeRes.isSuccessful && scheduleRes.isSuccessful) {
                        Result.success(
                            Triple(
                                userRes.body()!!,
                                employeeRes.body()!!,
                                scheduleRes.body()!!
                            )
                        )
                    } else {
                        Result.failure(Exception("Network error fetching combined profile data"))
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}