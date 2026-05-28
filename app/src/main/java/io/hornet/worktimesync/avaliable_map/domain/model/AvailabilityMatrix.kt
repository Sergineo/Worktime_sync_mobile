package io.hornet.worktimesync.avaliable_map.domain.model

data class AvailabilityMatrix(
    val dateRange: String,
    val totalEmployees: Int,
    val cells: List<MatrixCell>
)
