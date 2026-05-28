package io.hornet.worktimesync.di

import io.hornet.worktimesync.avaliable_map.domain.interactor.AvailabilityInteractor
import io.hornet.worktimesync.avaliable_map.domain.interactor.AvailabilityRepository
import io.hornet.worktimesync.avaliable_map.presentation.view_model.AvailabilityViewModel
import io.hornet.worktimesync.data.remote.data_source.AvailabilityRemoteDataSource
import io.hornet.worktimesync.data.repository.AvailabilityRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapDiModule = module{
    single { AvailabilityRemoteDataSource() }
    single <AvailabilityRepository>{
        AvailabilityRepositoryImpl(get())
    }
    single { AvailabilityInteractor(get()) }
    viewModel { AvailabilityViewModel(get()) }
}