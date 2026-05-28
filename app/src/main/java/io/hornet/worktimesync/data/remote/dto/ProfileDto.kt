package io.hornet.worktimesync.data.remote.dto

import com.google.gson.annotations.SerializedName


data class UserMeDto(
    val email: String
)

data class EmployeeMeDto(
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("patronymic") val patronymic: String? = null,
    @SerializedName("position") val position: String? = null
)

data class ScheduleMeDto(
    val work_days: List<Int>,
    val time_zone: String,
    val work_format: String,
    val start_at: String,
    val end_at: String,
    val updated_at: String
)