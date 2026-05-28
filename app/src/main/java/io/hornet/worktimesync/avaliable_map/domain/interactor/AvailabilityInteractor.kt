package io.hornet.worktimesync.avaliable_map.domain.interactor

import io.hornet.worktimesync.avaliable_map.domain.model.AvailabilityMatrix
import io.hornet.worktimesync.avaliable_map.domain.model.CellIntensity
import io.hornet.worktimesync.avaliable_map.domain.model.UiMatrixCell
import io.hornet.worktimesync.data.remote.dto.CellDetailsDto

class AvailabilityInteractor(
    private val repository: AvailabilityRepository
) {
    fun execute(): Pair<AvailabilityMatrix, List<UiMatrixCell>> {
        val matrix = repository.getMatrix()
        val uiCells = matrix.cells.map { cell ->
            val intensity = when {
                cell.availableCount == 19 -> CellIntensity.RED
                cell.hour in listOf(
                    "9:00",
                    "10:00",
                    "11:00",
                    "13:00"
                ) && cell.dayIndex in 0..2 -> CellIntensity.RED
                cell.dayIndex >= 5 -> CellIntensity.YELLOW
                else -> CellIntensity.GREEN
            }
            UiMatrixCell(cell, intensity)
        }
        return Pair(matrix, uiCells)
    }

    fun getCellDetails(): CellDetailsDto = repository.getDetails()
}