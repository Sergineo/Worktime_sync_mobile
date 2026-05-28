package io.hornet.worktimesync.data.remote.dto

data class MatrixCellDto(
    val day_of_week: Int,
    val hour: String,
    val available_count: Int,
    val total_count: Int
)

data class AvailabilityResponseDto(
    val date_range: String,
    val total_employees: Int,
    val cells: List<MatrixCellDto>
)

data class EmployeeShortDto(val id: Int, val first_name: String, val last_name: String)
data class CellDetailsDto(
    val date_text: String,
    val available_count: Int,
    val total_count: Int,
    val unavailable_employees: List<EmployeeShortDto>
)