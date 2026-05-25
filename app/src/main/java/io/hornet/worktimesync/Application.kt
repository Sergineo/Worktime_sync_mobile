package io.hornet.worktimesync

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.hornet.worktimesync.core.presentaition.navigation.BottomBar
import io.hornet.worktimesync.core.presentaition.navigation.NavRoot
import io.hornet.worktimesync.core.presentaition.navigation.router_impl.RouterCore
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import io.hornet.worktimesync.core.presentaition.widget.MainToastWidget
import io.hornet.worktimesync.theme.Theme
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import org.koin.androidx.compose.koinViewModel

class Application : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.Companion.auto(
                lightScrim = Color.BLACK,
                darkScrim = Color.WHITE,
                detectDarkMode = {
                    resources.configuration.uiMode and
                            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
                },
            ),
            navigationBarStyle = SystemBarStyle.Companion.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
                detectDarkMode = {
                    resources.configuration.uiMode and
                            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
                },
            )
        )
        setContent {
            val navViewModel: NavViewModel = koinViewModel()
            val navController: NavHostController = rememberNavController()
            MainToastWidget(this, navViewModel)
            Theme {
                Scaffold(
                    containerColor = ColorShema.current.background,
                    bottomBar = {
                        BottomBar(
                            navController = navController
                        )
                    },
                ) { paddingValues ->
                    NavRoot(
                        navController = navController,
                        paddingValues = paddingValues,
                        navViewModel = navViewModel,
                        routerCore = RouterCore()
                    )
                }
            }
        }
    }
}