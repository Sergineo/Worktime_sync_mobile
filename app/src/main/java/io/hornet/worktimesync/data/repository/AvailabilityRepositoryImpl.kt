package io.hornet.worktimesync.data.repository

import io.hornet.worktimesync.avaliable_map.domain.interactor.AvailabilityRepository
import io.hornet.worktimesync.avaliable_map.domain.model.AvailabilityMatrix
import io.hornet.worktimesync.avaliable_map.domain.model.MatrixCell
import io.hornet.worktimesync.data.remote.data_source.AvailabilityRemoteDataSource
import io.hornet.worktimesync.data.remote.dto.CellDetailsDto

class AvailabilityRepositoryImpl(
    private val dataSource: AvailabilityRemoteDataSource
): AvailabilityRepository {
    override fun getMatrix(): AvailabilityMatrix {
        val raw = dataSource.getAvailabilityMatrixMock()
        return AvailabilityMatrix(
            dateRange = raw.date_range,
            totalEmployees = raw.total_employees,
            cells = raw.cells.map {
                MatrixCell(
                    it.day_of_week,
                    it.hour,
                    it.available_count,
                    it.total_count
                )
            }
        )
    }

    override fun getDetails(): CellDetailsDto = dataSource.getCellDetailsMock()
}