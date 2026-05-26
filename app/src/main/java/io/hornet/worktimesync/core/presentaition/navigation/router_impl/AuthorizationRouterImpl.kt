package io.hornet.worktimesync.core.presentaition.navigation.router_impl

import androidx.navigation.NavHostController
import io.hornet.worktimesync.authorization.domain.screen_model.NextButtonScreenEvents
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent

class AuthorizationRouterImpl(
    private val authorizationScreenViewModel: AuthorizationScreenViewModel
) : AuthorizationRouter {
    override fun goToProfileScreen(
        events: NextButtonScreenEvents,
        call: () -> NavHostController
    ) {
        authorizationScreenViewModel.onClickedNextButton(
            events = events,
            navigateCall = { call().navigate(NavigationScreenEvent.ProfileScreenPoint) }
        )
    }
}