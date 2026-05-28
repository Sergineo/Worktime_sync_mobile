package io.hornet.worktimesync.avaliable_map.domain.interactor

import io.hornet.worktimesync.avaliable_map.domain.model.AvailabilityMatrix
import io.hornet.worktimesync.data.remote.dto.CellDetailsDto

interface AvailabilityRepository {
    fun getMatrix(): AvailabilityMatrix
    fun getDetails(): CellDetailsDto
}