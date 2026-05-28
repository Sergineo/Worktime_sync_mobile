package io.hornet.worktimesync.profile.domain.interactor

import io.hornet.worktimesync.profile.domain.model.FormattedProfile

class ProfileInteractor(private val repository: ProfileRepository) {

    suspend fun execute(): Result<FormattedProfile> {
        return repository.getProfileData().map { domain ->

            val middleNameText = if (!domain.patronymic.isNullOrBlank()) " ${domain.patronymic}" else ""
            val fullFio = "${domain.lastName} ${domain.firstName}$middleNameText"

            FormattedProfile(
                fullName = fullFio,
                email = domain.email,
                position = domain.position,
                workDaysRu = convertDaysToRu(domain.workDays),
                workTime = "${domain.startAt.take(5)} - ${domain.endAt.take(5)}",
                timeZone = domain.timeZone,
                workFormatRu = convertFormatToRu(domain.workFormat),
                updatedAtRu = convertDateToRu(domain.updatedAt)
            )
        }
    }

    private fun convertDaysToRu(days: List<Int>): String {
        if (days.isEmpty()) return "Не указано"
        if (days == listOf(0, 1, 2, 3, 4)) return "ПН-ПТ"

        val ruDaysNames = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
        return days.mapNotNull { ruDaysNames.getOrNull(it) }.joinToString("-")
    }

    private fun convertFormatToRu(format: String): String = when (format.lowercase()) {
        "office" -> "Офис"
        "hybrid" -> "Гибридный"
        "remote" -> "Удаленный"
        else -> format
    }

    private fun convertDateToRu(isoDate: String): String {
        return try {
            val datePart = isoDate.substringBefore("T")
            val elements = datePart.split("-")
            "${elements[2]}.${elements[1]}.${elements[0]}"
        } catch (e: Exception) {
            isoDate.take(10)
        }
    }
}