package io.hornet.worktimesync.data.repository

import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.data_source.ProfileRemoteDataSource
import io.hornet.worktimesync.profile.domain.interactor.ProfileRepository
import io.hornet.worktimesync.profile.domain.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val jwtTokenLocalDataSource: JwtTokenLocalDataSource
): ProfileRepository {
    override suspend fun getProfileData(): Result<UserProfile> = withContext(Dispatchers.IO) {
        var result = jwtTokenLocalDataSource.getLocalJwtToken()

        var maxAttempts = 40
        while ((result == null || result.access_token == null) && maxAttempts > 0) {
            delay(5000)
            result = jwtTokenLocalDataSource.getLocalJwtToken()
            maxAttempts--
        }

        if (result != null && result.access_token != null) {
            profileRemoteDataSource.getFullProfileDataRaw(result.access_token)
                .map { (userDto, employeeDto, scheduleDto) ->
                    UserProfile(
                        email = userDto.email,
                        firstName = employeeDto.first_name,
                        lastName = employeeDto.last_name,
                        patronymic = employeeDto.patronymic ?: "",
                        position = employeeDto.position ?: "Сотрудник",
                        workDays = scheduleDto.work_days,
                        timeZone = scheduleDto.time_zone,
                        workFormat = scheduleDto.work_format,
                        startAt = scheduleDto.start_at,
                        endAt = scheduleDto.end_at,
                        updatedAt = scheduleDto.updated_at
                    )
                }
        } else {
            Result.failure(Exception("Превышено время ожидания авторизации (Токен отсутствует)"))
        }
    }
}