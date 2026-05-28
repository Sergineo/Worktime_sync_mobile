package io.hornet.worktimesync.avaliable_map.presentation.view_model

import androidx.lifecycle.ViewModel
import io.hornet.worktimesync.avaliable_map.domain.interactor.AvailabilityInteractor
import io.hornet.worktimesync.avaliable_map.domain.model.AvailabilityMatrix
import io.hornet.worktimesync.avaliable_map.domain.model.UiMatrixCell
import io.hornet.worktimesync.data.remote.dto.CellDetailsDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AvailabilityViewModel(
    private val interactor: AvailabilityInteractor
): ViewModel() {
    private val _matrixState = MutableStateFlow<AvailabilityMatrix?>(null)
    val matrixState = _matrixState.asStateFlow()

    private val _cellsState = MutableStateFlow<List<UiMatrixCell>>(emptyList())
    val cellsState = _cellsState.asStateFlow()

    private val _selectedDetails = MutableStateFlow<CellDetailsDto?>(null)
    val selectedDetails = _selectedDetails.asStateFlow()

    init {
        val (matrix, uiCells) = interactor.execute()
        _matrixState.value = matrix
        _cellsState.value = uiCells
        _selectedDetails.value = interactor.getCellDetails()
    }
}