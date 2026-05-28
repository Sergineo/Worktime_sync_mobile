package io.hornet.worktimesync

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.hornet.worktimesync.core.presentaition.navigation.BottomBar
import io.hornet.worktimesync.core.presentaition.navigation.NavRoot
import io.hornet.worktimesync.core.presentaition.navigation.TopBar
import io.hornet.worktimesync.theme.Theme
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import org.koin.core.component.KoinComponent

class Application() : AppCompatActivity(), KoinComponent {
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
            val navController: NavHostController = rememberNavController()
            Theme {
                Scaffold(
                    containerColor = ColorShema.current.secondary,
                    topBar = {
                        TopBar(navController)
                    },
                    bottomBar = {
                        BottomBar(
                            navController = navController
                        )
                    },
                ) { paddingValues ->
                    NavRoot(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}