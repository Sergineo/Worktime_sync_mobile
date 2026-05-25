package io.hornet.worktimesync.core.presentaition.screen_ui

import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SelectionIconScreen(
    navigateToSecondScreen: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ComposeColor.Transparent)
            .clickable{
                navigateToSecondScreen()
            }
    ){
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Экран профиля",
            color = ComposeColor.White,
            fontSize = 30.sp
        )
    }
}