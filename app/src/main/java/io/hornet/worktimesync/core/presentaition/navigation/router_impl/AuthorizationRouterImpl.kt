package io.hornet.worktimesync.core.presentaition.navigation.router_impl

import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent

class AuthorizationRouterImpl(
    private val routerCore: RouterCore
): AuthorizationRouter {
    override fun goToProfileScreen() {
        routerCore.navController?.navigate(NavigationScreenEvent.ProfileScreenPoint)
    }
}