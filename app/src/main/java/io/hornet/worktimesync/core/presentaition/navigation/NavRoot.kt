package io.hornet.worktimesync.core.presentaition.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.hornet.worktimesync.authorization.presentation.AuthorizationScreen
import io.hornet.worktimesync.authorization.presentation.navigation.router.AuthorizationRouter
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import io.hornet.worktimesync.core.presentaition.widget.MainToastWidget
import io.hornet.worktimesync.profile.presentation.ProfileScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun NavRoot(
    navController: NavHostController,
    paddingValues: PaddingValues,
    navViewModel: NavViewModel = koinViewModel(),
    authorizationScreenViewModel: AuthorizationScreenViewModel = koinViewModel()
) {
    val router: AuthorizationRouter = koinInject()
    val startDestination = navViewModel._mainViewModel.collectAsState().value

    MainToastWidget(navViewModel = navViewModel)

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = startDestination
    ) {
        composable<NavigationScreenEvent.AuthorizationScreenPoint>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            AuthorizationScreen(
                viewModel = authorizationScreenViewModel,
                navigateToProfileScreen = {
                    router.goToProfileScreen(
                        it,
                        call = { navController }
                    )
                }
            )
        }
        composable<NavigationScreenEvent.ProfileScreenPoint>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            ProfileScreen()
        }
    }
}