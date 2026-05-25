package io.hornet.worktimesync.core.presentaition.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.AuthorizationRouterImpl
import io.hornet.worktimesync.authorization.presentation.AuthorizationScreen
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.RouterCore
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavRoot(
    navController: NavHostController,
    paddingValues: PaddingValues,
    navViewModel: NavViewModel,
    routerCore: RouterCore,
    authorizationScreenViewModel: AuthorizationScreenViewModel = koinViewModel()
) {
    val startDestination = navViewModel.mainViewModel.collectAsState().value
    routerCore.attachNavController(navController)

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = startDestination::class
    ) {
        composable<NavigationScreenEvent.AuthorizationScreenPoint>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            AuthorizationScreen(
                viewModel = authorizationScreenViewModel,
            )
        }
        composable<NavigationScreenEvent.ProfileScreenPoint>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {

        }
    }
}