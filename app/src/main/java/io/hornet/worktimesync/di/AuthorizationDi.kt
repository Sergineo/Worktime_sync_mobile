package io.hornet.worktimesync.di

import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationInteractor
import io.hornet.worktimesync.authorization.domain.interactor.SaveTokenRepository
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.data.local.data_source.JwtTokenLocalDataSource
import io.hornet.worktimesync.data.repository.SaveTokenRepositoryImpl
import org.koin.dsl.module

val authorizationDi = module {
    single<SaveTokenRepository> { SaveTokenRepositoryImpl(get<JwtTokenLocalDataSource>()) }
    single { AuthorizationInteractor(get<SaveTokenRepository>()) }
    single{AuthorizationScreenViewModel(get<AuthorizationInteractor>())}
}