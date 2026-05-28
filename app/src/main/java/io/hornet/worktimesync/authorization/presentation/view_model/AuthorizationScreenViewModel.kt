package io.hornet.worktimesync.authorization.presentation.view_model

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.hornet.worktimesync.R
import io.hornet.worktimesync.authorization.domain.exceptions.AuthException
import io.hornet.worktimesync.authorization.domain.exceptions.ValidationError
import io.hornet.worktimesync.authorization.domain.interactor.AuthorizationInteractor
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationScreen
import io.hornet.worktimesync.authorization.domain.screen_model.NextButtonScreenEvents
import io.hornet.worktimesync.authorization.domain.screen_model.TextFieldScreenEvents
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.core.domain.exceptions.DomainCoreException
import io.hornet.worktimesync.core.domain.exceptions.JwtTokerError
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.compose.defaultExtras

class AuthorizationScreenViewModel(
    private val authorizationInteractor: AuthorizationInteractor,
    private val navViewModel: NavViewModel
) : ViewModel() {

    private val _authorizationScreenState = MutableStateFlow(AuthorizationScreen())
    val authorizationScreenState: StateFlow<AuthorizationScreen> =
        _authorizationScreenState.asStateFlow()

    private val _authorizationErrorViewModel = Channel<AuthException?>()
    val authorizationErrorViewModel  = _authorizationErrorViewModel.receiveAsFlow()

    fun setSelectedAuthorizationMode(index: Int) = _authorizationScreenState.update {
        it.copy(selectedMode = index)
    }

    fun onInputEvent(event: TextFieldScreenEvents) {
        _authorizationScreenState.update { currentState ->
            when (event) {
                is TextFieldScreenEvents.TextFieldModeFragment.EmailChanged -> {
                    currentState.copy(
                        authorizationFragment = currentState.authorizationFragment.copy(
                            email = authorizationInteractor.validateEmailTextField(event.textField)
                        )
                    )
                }

                is TextFieldScreenEvents.TextFieldModeFragment.PasswordChanged -> currentState.copy(
                    authorizationFragment = currentState.authorizationFragment.copy(
                        password = authorizationInteractor.validateEmptyTextFiled(event.textField)
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.EmailChanged -> currentState.copy(
                    registrationFragment = currentState.registrationFragment.copy(
                        email = authorizationInteractor.validateEmailTextField(event.textField)
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.NewPasswordChanged -> currentState.copy(
                    registrationFragment = currentState.registrationFragment.copy(
                        password = authorizationInteractor.validateEmptyTextFiled(event.textField)
                    )
                )

                is TextFieldScreenEvents.RegistrationModeFragment.RepeatPasswordChanged -> currentState.copy(
                    registrationFragment = currentState.registrationFragment.copy(
                        repeatPassword = authorizationInteractor.validateRepeatPassword(
                            event.textField,
                            currentState.registrationFragment.password.message?.toString(),
                            event.textField.message?.toString()
                        )
                    )
                )
            }
        }
    }


    fun onClickedNextButton(events: NextButtonScreenEvents, navigateCall: () -> Unit) {
        viewModelScope.launch {
            when (events) {
                is NextButtonScreenEvents.RegistrationClicked -> {
                    authorizationInteractor.registration(
                        email = events.email.message.toString(),
                        password = events.password.message.toString()
                    ).fold(
                        onSuccess = {
                            viewModelScope.launch(Dispatchers.Main.immediate) {
                                navViewModel.defineMessageToast(JwtTokerError.PleaceUnauthorized)
                                setSelectedAuthorizationMode(0)
                            }
                        },
                        onFailure = {
                            viewModelScope.launch(Dispatchers.Main.immediate) {
                                navViewModel.defineMessageToast(JwtTokerError.UncorrectData)
                            }
                        }
                    )
                }

                is NextButtonScreenEvents.AuthorizationClicked -> {
                    authorizationInteractor.login(
                        email = events.email.message.toString(),
                        password = events.password.message.toString()

                    ).fold(
                        onSuccess = { token ->
                            if (token != null) {
                                Log.d("AUTH_SUCCESS", "Токен успешно получен!")
                                authorizationInteractor.saveLocalToken(token)
                                viewModelScope.launch(Dispatchers.Main.immediate) {
                                    Log.d("AUTH_NAV", "Вызываем goToProfileScreen в Main потоке")
                                    navigateCall()
                                }
                            } else {
                                Log.e("AUTH_ERROR", "Тело ответа пустое")
                                viewModelScope.launch(Dispatchers.Main.immediate) {
                                    navViewModel.defineMessageToast(JwtTokerError.UncorrectData)
                                }
                            }
                        },
                        onFailure = { throwable ->
                            Log.e("AUTH_CRITICAL", "Ошибка: ${throwable.javaClass.simpleName}")
                            viewModelScope.launch(Dispatchers.Main.immediate) {
                                navViewModel.defineMessageToast(JwtTokerError.UncorrectData)
                            }
                        }
                    )
                }
            }
        }
    }
}