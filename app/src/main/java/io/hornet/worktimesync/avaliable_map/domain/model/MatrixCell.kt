package io.hornet.worktimesync.avaliable_map.domain.model

data class MatrixCell(
    val dayIndex: Int,
    val hour: String,
    val availableCount: Int,
    val totalCount: Int
)
