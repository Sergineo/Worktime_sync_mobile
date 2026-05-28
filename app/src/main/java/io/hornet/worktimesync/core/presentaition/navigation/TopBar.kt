package io.hornet.worktimesync.core.presentaition.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.hornet.worktimesync.core.domain.screen_model.NavigationScreenEvent
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun TopBar(
    navController: NavHostController
) {
    val colors = ColorShema.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isAuthScreen = currentRoute == NavigationScreenEvent.AuthorizationScreenPoint::class.qualifiedName

    val barTitle = when (currentRoute) {
        NavigationScreenEvent.ProfileScreenPoint::class.qualifiedName -> "Личный кабинет"
        NavigationScreenEvent.MapScreenPoint::class.qualifiedName -> "Карта доступности"
        NavigationScreenEvent.ConflictScreenPoint::class.qualifiedName -> "Конфликты и риски"
        else -> "Личный кабинет"
    }

    AnimatedVisibility(
        visible = !isAuthScreen,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .statusBarsPadding()
                .background(colors.secondary)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = barTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = colors.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}