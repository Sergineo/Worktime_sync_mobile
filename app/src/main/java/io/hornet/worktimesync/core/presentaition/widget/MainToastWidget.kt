package io.hornet.worktimesync.core.presentaition.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel

@Composable
fun MainToastWidget(
    context: Context = LocalContext.current,
    navViewModel: NavViewModel
) {
    LaunchedEffect(key1 = true) {
        navViewModel.mainErrorViewModel.collect { error ->
            val errorMessage = error?.description
            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}