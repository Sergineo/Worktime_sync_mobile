package io.hornet.worktimesync.authorization.presentation.fragment

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import io.hornet.worktimesync.R
import io.hornet.worktimesync.authorization.domain.screen_model.TextFieldScreenEvents
import io.hornet.worktimesync.authorization.presentation.widgets.AuthorizationTextFieldWidget
import io.hornet.worktimesync.authorization.presentation.widgets.AuthorizationTextWidgetSpacer
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel

@Composable
fun ColumnScope.RegistrationModeFragment(
    viewModel: AuthorizationScreenViewModel
) {
    val screenState = viewModel.authorizationScreenState.collectAsState().value
    val regState = screenState.registrationFragment

    AuthorizationTextWidgetSpacer()

    AuthorizationTextFieldWidget(
        event = {
            viewModel.onInputEvent(
                TextFieldScreenEvents.RegistrationModeFragment.EmailChanged(
                    it
                )
            )
        },
        message = regState.email.message.toString(),
        isError = regState.email.isError,
        stringResource = R.string.enter_email,
        errorStringResource = regState.email.errorDescription
    )
    AuthorizationTextFieldWidget(
        event = {
            viewModel.onInputEvent(
                TextFieldScreenEvents.RegistrationModeFragment.NewPasswordChanged(
                    it
                )
            )
        },
        message = regState.password.message.toString(),
        isError = regState.password.isError,
        stringResource = R.string.enter_password,
        errorStringResource = regState.password.errorDescription
    )
    AuthorizationTextFieldWidget(
        event = {
            viewModel.onInputEvent(
                TextFieldScreenEvents.RegistrationModeFragment.RepeatPasswordChanged(
                    it
                )
            )
        },
        message = regState.repeatPassword.message.toString(),
        isError = regState.repeatPassword.isError,
        stringResource = R.string.repeat_password,
        errorStringResource = regState.repeatPassword.errorDescription
    )
}