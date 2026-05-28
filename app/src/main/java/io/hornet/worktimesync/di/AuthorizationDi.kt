package io.hornet.worktimesync.di

import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationInteractor
import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationRepository
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.AuthorizationRouterImpl
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.data_source.JwtTokenRemoteDataSource
import io.hornet.worktimesync.data.repository.AuthorizationRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authorizationDi = module {
    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl(get<JwtTokenRemoteDataSource>(), get<JwtTokenLocalDataSource>())
    }
    single { AuthorizationInteractor(get<AuthorizationRepository>()) }
    single<AuthorizationRouter> { AuthorizationRouterImpl(get<AuthorizationScreenViewModel>()) }
    viewModel { AuthorizationScreenViewModel(get<AuthorizationInteractor>(), get<NavViewModel>()) }
}