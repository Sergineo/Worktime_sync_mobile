package io.hornet.worktimesync.core.presentaition.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel

@Composable
fun MainToastWidget(
    context: Context = LocalContext.current,
    navViewModel: NavViewModel
) {
    LaunchedEffect(Unit) {
        navViewModel.mainErrorViewModel.collect {
            Toast.makeText(context, it?.description!!, Toast.LENGTH_SHORT).show()
        }
    }
}