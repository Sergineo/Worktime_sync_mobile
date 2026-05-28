package io.hornet.worktimesync.data.remote.data_source

import io.hornet.worktimesync.data.remote.dto.AvailabilityResponseDto
import io.hornet.worktimesync.data.remote.dto.CellDetailsDto
import io.hornet.worktimesync.data.remote.dto.EmployeeShortDto
import io.hornet.worktimesync.data.remote.dto.MatrixCellDto

class AvailabilityRemoteDataSource {
    fun getAvailabilityMatrixMock(): AvailabilityResponseDto {
        val hoursList = listOf("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00")
        val cells = mutableListOf<MatrixCellDto>()

        for (hour in hoursList) {
            for (day in 0..6) {
                val count = when {
                    hour == "12:00" && day == 3 -> 19
                    hour in listOf("9:00", "10:00", "11:00", "13:00") && day in 0..2 -> 22 // Розовые ПН-СР
                    hour in listOf("8:00", "12:00", "14:00", "15:00", "16:00") && day in 0..2 -> 22 // Бежевые
                    else -> 22
                }
                cells.add(MatrixCellDto(day_of_week = day, hour = hour, available_count = count, total_count = 23))
            }
        }

        return AvailabilityResponseDto(
            date_range = "07.05-14.05",
            total_employees = 23,
            cells = cells
        )
    }

    fun getCellDetailsMock(): CellDetailsDto {
        return CellDetailsDto(
            date_text = "Чт, 4 мая, 12:00",
            available_count = 19,
            total_count = 23,
            unavailable_employees = listOf(
                EmployeeShortDto(1, "Петр", "Петров"),
                EmployeeShortDto(2, "Сидор", "Сидоров"),
                EmployeeShortDto(3, "Алексей", "Алексеев"),
                EmployeeShortDto(4, "Михаил", "Михайлов")
            )
        )
    }
}