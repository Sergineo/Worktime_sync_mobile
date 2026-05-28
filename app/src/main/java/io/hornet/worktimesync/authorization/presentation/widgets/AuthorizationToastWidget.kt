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
    LaunchedEffect(key1 = false) {
        authorizationScreenViewModel.authorizationErrorViewModel.collect { error ->
            if (error != null && error.description != null) {
                Toast.makeText(context, error.description, Toast.LENGTH_SHORT).show()
            }
        }
    }
}