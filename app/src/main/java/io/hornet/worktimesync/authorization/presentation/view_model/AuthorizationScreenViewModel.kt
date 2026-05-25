package io.hornet.worktimesync.authorization.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hornet.worktimesync.R
import io.hornet.worktimesync.authorization.domain.exceptions.ValidationError
import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationInteractor
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationScreen
import io.hornet.worktimesync.authorization.domain.screen_model.NextButtonScreenEvents
import io.hornet.worktimesync.authorization.domain.screen_model.TextFieldScreenEvents
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorizationScreenViewModel(
    private val authorizationInteractor: AuthorizationInteractor,
    private val authorizationRouter: AuthorizationRouter
) : ViewModel() {
    val _authorizationScreenState = MutableStateFlow(AuthorizationScreen())
    val authorizationScreenState: StateFlow<AuthorizationScreen> =
        _authorizationScreenState.asStateFlow()

    val _authorizationErrorViewModel = MutableStateFlow<ValidationError?>(null)
    val authorizationErrorViewModel: SharedFlow<ValidationError?> =
        _authorizationErrorViewModel.asSharedFlow()

    fun setSelectedAuthorizationMode(index: Int) = _authorizationScreenState.update {
        it.copy(selectedMode = index)
    }

    fun onInputEvent(event: TextFieldScreenEvents) {
        _authorizationScreenState.update { currentState ->
            when (event) {
                is TextFieldScreenEvents.TextFieldModeFragment.EmailChanged -> {
                    currentState.copy(
                        authorizationFragment = currentState.authorizationFragment.copy(
                            email = authorizationInteractor.validateEmailTextField(
                                event.textField
                            )
                        )
                    )
                }

                is TextFieldScreenEvents.TextFieldModeFragment.PasswordChanged -> currentState.copy(
                    authorizationFragment = currentState.authorizationFragment.copy(
                        password = authorizationInteractor.validateEmptyTextFiled(
                            event.textField
                        )
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.EmailChanged -> currentState.copy(
                    registrationFragment = currentState.registrationFragment.copy(
                        email = authorizationInteractor.validateEmailTextField(
                            event.textField
                        )
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.NewPasswordChanged -> currentState.copy(
                    registrationFragment = currentState.registrationFragment.copy(
                        password = authorizationInteractor.validateEmptyTextFiled(
                            event.textField
                        )
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.RepeatPasswordChanged ->
                    currentState.copy(
                        registrationFragment = currentState.registrationFragment.copy(
                            repeatPassword = authorizationInteractor.validateRepeatPassword(
                                event.textField,
                                authorizationScreenState.value.registrationFragment.password.message,
                                authorizationScreenState.value.registrationFragment.repeatPassword.message
                            )
                        )
                    )

            }
        }
    }

    fun onClickedNextButton(events: NextButtonScreenEvents) {
        viewModelScope.launch {
            when (events) {
                is NextButtonScreenEvents.RegistrationClicked -> {
                    authorizationInteractor.registration(
                        _authorizationScreenState.value.registrationFragment
                    ).fold(
                        onSuccess = {
                            authorizationRouter.goToProfileScreen()
                        },
                        onFailure = {
                            _authorizationErrorViewModel.emit(
                                ValidationError.ErrorAuthorization(
                                    R.string.uncorrent_login_or_password
                                )
                            )
                        }
                    )
                }

                is NextButtonScreenEvents.AuthorizationClicked -> {
                    authorizationInteractor.login(
                        _authorizationScreenState.value.authorizationFragment
                    ).fold(
                        onSuccess = {
                            authorizationInteractor.saveLocalToken(it!!)
                            authorizationRouter.goToProfileScreen()
                        },
                        onFailure = {
                            _authorizationErrorViewModel.emit(
                                ValidationError.ErrorRegistration(
                                    R.string.unknown
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}