package io.hornet.worktimesync.di


import io.hornet.worktimesync.core.domain.interactor.NavigationInteractor
import io.hornet.worktimesync.core.domain.interactor.CoreTokenRepository
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.api.JwtApi
import io.hornet.worktimesync.data.remote.config.JwtApiClient
import io.hornet.worktimesync.data.remote.data_source.JwtTokenRemoteDataSource
import io.hornet.worktimesync.data.repository.CoreTokenRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreDiModule = module {
    single { JwtApiClient() }
    single<JwtApi> { get() }
    single {

        get<JwtApiClient>().createJwtApiClientSession().create(JwtApi::class.java)
    }
    single { JwtTokenRemoteDataSource(get()) }
    single { JwtTokenLocalDataSource(androidContext()) }
    single<CoreTokenRepository> {
        CoreTokenRepositoryImpl(
            get<JwtTokenRemoteDataSource>(),
            get<JwtTokenLocalDataSource>()
        )
    }
    single { NavigationInteractor(get<CoreTokenRepository>()) }
    viewModel { NavViewModel(get<NavigationInteractor>())}
}