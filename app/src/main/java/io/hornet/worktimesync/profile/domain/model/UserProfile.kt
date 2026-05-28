package io.hornet.worktimesync.profile.domain.model

data class UserProfile(
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val position: String,
    val workDays: List<Int>,
    val timeZone: String,
    val workFormat: String,
    val startAt: String,
    val endAt: String,
    val updatedAt: String
)
