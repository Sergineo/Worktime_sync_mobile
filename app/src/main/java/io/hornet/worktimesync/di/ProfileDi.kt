package io.hornet.worktimesync.di

import com.google.firebase.appdistribution.gradle.ApiService
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.api.JwtApi
import io.hornet.worktimesync.data.remote.api.ProfileApi
import io.hornet.worktimesync.data.remote.config.ApiClient
import io.hornet.worktimesync.data.remote.data_source.ProfileRemoteDataSource
import io.hornet.worktimesync.data.repository.ProfileRepositoryImpl
import io.hornet.worktimesync.profile.domain.interactor.ProfileInteractor
import io.hornet.worktimesync.profile.domain.interactor.ProfileRepository
import io.hornet.worktimesync.profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val profileDiModule = module {
    single<ProfileApi>{
        get<ApiClient>().createApiClientSession().create(ProfileApi::class.java)
    }
    single {
        ProfileRemoteDataSource(get<ProfileApi>())
    }
    single<ProfileRepository> {
        ProfileRepositoryImpl(
            get<ProfileRemoteDataSource>(),
            get<JwtTokenLocalDataSource>()
        )
    }

    single {
        ProfileInteractor(get<ProfileRepository>())
    }
    viewModel {
        ProfileViewModel(get())
    }
}