package io.hornet.worktimesync.di

import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationInteractor
import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationRepository
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.AuthorizationRouterImpl
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.RouterCore
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.remote.data_source.JwtTokenRemoteDataSource
import io.hornet.worktimesync.data.repository.AuthorizationRepositoryImpl
import org.koin.dsl.module

val authorizationDi = module {
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get<JwtTokenRemoteDataSource>(), get<JwtTokenLocalDataSource>()) }
    single { AuthorizationInteractor(get<AuthorizationRepository>()) }
    single{AuthorizationScreenViewModel(get<AuthorizationInteractor>(), get<AuthorizationRouter>())}
    single<AuthorizationRouter>{ AuthorizationRouterImpl(get<RouterCore>()) }
    single { RouterCore() }
}