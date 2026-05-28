package io.hornet.worktimesync.profile.domain.model

data class FormattedProfile(
    val fullName: String,
    val email: String,
    val position: String,
    val workDaysRu: String,
    val workTime: String,
    val timeZone: String,
    val workFormatRu: String,
    val updatedAtRu: String
)