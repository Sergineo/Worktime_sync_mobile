package io.hornet.worktimesync.authorization.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.hornet.worktimesync.authorization.domain.screen_model.NextButtonScreenEvents
import io.hornet.worktimesync.authorization.presentation.fragment.AuthorizationModeFragment
import io.hornet.worktimesync.authorization.presentation.fragment.BookPagesAnimator
import io.hornet.worktimesync.authorization.presentation.fragment.RegistrationModeFragment
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.authorization.presentation.widgets.AuthorizationToastWidget
import io.hornet.worktimesync.authorization.presentation.widgets.MainLogo
import io.hornet.worktimesync.authorization.presentation.widgets.NextButton
import io.hornet.worktimesync.authorization.presentation.widgets.SelectedAuthorizationModeButton
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationScreenViewModel,
    navigateToProfileScreen: (event: NextButtonScreenEvents) -> Unit
) {
    val authorizationOptions =
        viewModel.authorizationScreenState.collectAsState().value.authorizationMode

    val selectedOption by viewModel.authorizationScreenState.collectAsStateWithLifecycle()
    val selectedIndex = selectedOption.selectedMode

    val email =
        viewModel.authorizationScreenState.collectAsState().value.authorizationFragment.email
    val password =
        viewModel.authorizationScreenState.collectAsState().value.authorizationFragment.password
    val newEmail =
        viewModel.authorizationScreenState.collectAsState().value.registrationFragment.email
    val newPassword =
        viewModel.authorizationScreenState.collectAsState().value.registrationFragment.password
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ComposeColor.Transparent)
    ) {
        MainLogo(
            modifier = Modifier.weight(0.2f)
        )
        SelectedAuthorizationModeButton(
            modifier = Modifier.weight(0.15f),
            viewModel = viewModel,
            authorizationOptions = authorizationOptions,
            selectedIndex = selectedIndex
        )
        BookPagesAnimator(
            modifier = Modifier.weight(0.65f),
            selectedIndex = selectedIndex
        ) {
            when (it) {
                0 -> {
                    AuthorizationModeFragment(
                        viewModel = viewModel
                    )
                    NextButton(
                        onClick = {
                            navigateToProfileScreen(
                                NextButtonScreenEvents.AuthorizationClicked(email, password)
                            )
                        },
                    )
                }

                1 -> {
                    RegistrationModeFragment(
                        viewModel = viewModel
                    )
                    NextButton(
                        onClick = {
                            navigateToProfileScreen(
                                NextButtonScreenEvents.RegistrationClicked(newEmail, newPassword)
                            )
                        },
                    )
                }
            }
        }
    }
    AuthorizationToastWidget(
        context = LocalContext.current,
        authorizationScreenViewModel = viewModel
    )
}