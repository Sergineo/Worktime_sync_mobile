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
fun ColumnScope.AuthorizationModeFragment(
    viewModel: AuthorizationScreenViewModel
) {
    val screenState = viewModel.authorizationScreenState.collectAsState().value
    val authState = screenState.authorizationFragment

    AuthorizationTextWidgetSpacer()
    AuthorizationTextFieldWidget(
        event = {
            viewModel.onInputEvent(
                TextFieldScreenEvents.TextFieldModeFragment.EmailChanged(
                    it
                )
            )
        },
        message = authState.email.message.toString(),
        isError = authState.email.isError,
        stringResource = R.string.enter_email,
        errorStringResource = authState.email.errorDescription
    )
    AuthorizationTextFieldWidget(
        event = {
            viewModel.onInputEvent(
                TextFieldScreenEvents.TextFieldModeFragment.PasswordChanged(
                    it
                )
            )
        },
        message = authState.password.message.toString(),
        isError = authState.password.isError,
        stringResource = R.string.enter_password,
        errorStringResource = authState.password.errorDescription
    )
}