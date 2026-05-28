package io.hornet.worktimesync.core.presentaition.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.hornet.worktimesync.R
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isAuthScreen =
        currentRoute ==
                NavigationScreenEvent.AuthorizationScreenPoint::class.qualifiedName

    val isProfileScreen =
        currentRoute ==
                NavigationScreenEvent.ProfileScreenPoint::class.qualifiedName
    val isMapScreen =
        currentRoute ==
                NavigationScreenEvent.MapScreenPoint::class.qualifiedName
    val isConflictScreen =
        currentRoute ==
                NavigationScreenEvent.ConflictScreenPoint::class.qualifiedName

    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
    val systemBarHeight = navigationBarsPadding.calculateBottomPadding()

    AnimatedVisibility(
        visible = !isAuthScreen,
        enter = slideInVertically { it },
        exit = slideOutVertically { -it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp + systemBarHeight)
                .background(ColorShema.current.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentSize(),
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        if (!isProfileScreen) {
                            navController.navigate(NavigationScreenEvent.ProfileScreenPoint)
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.users),
                            contentDescription = null,
                            tint = if (isProfileScreen) ColorShema.current.onSurface.copy(alpha = 0.4f)
                            else ColorShema.current.onBackground
                        )
                    }
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentSize(),
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        if (!isMapScreen) {
                            navController.navigate(NavigationScreenEvent.MapScreenPoint)
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.map),
                            contentDescription = null,
                            tint = if (isMapScreen) ColorShema.current.onSurface.copy(alpha = 0.4f)
                            else ColorShema.current.onBackground
                        )
                    }
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentSize(),
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        if (!isConflictScreen){
                            navController.navigate(NavigationScreenEvent.ConflictScreenPoint)
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.conflicts),
                            contentDescription = null,
                            tint = if (isConflictScreen) ColorShema.current.onSurface.copy(alpha = 0.4f)
                            else ColorShema.current.onBackground
                        )
                    }
                )
            }
        }
    }
}