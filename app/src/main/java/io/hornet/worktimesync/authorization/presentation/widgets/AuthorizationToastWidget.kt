package io.hornet.worktimesync.authorization.presentation.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel

@Composable
fun AuthorizationToastWidget(
    context: Context,
    authorizationScreenViewModel: AuthorizationScreenViewModel
){
    LaunchedEffect(key1 = true) {
        authorizationScreenViewModel.authorizationErrorViewModel.collect { error ->
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}