package io.hornet.worktimesync.core.presentaition.widget

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import io.hornet.worktimesync.core.presentaition.view_model.NavViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

@Composable
fun MainToastWidget(
    context: Context = LocalContext.current,
    navViewModel: NavViewModel
) {
    Log.e("INPUT", "Оно уже воняет!")
    LaunchedEffect(Unit) {
        navViewModel.mainErrorViewModel.collectLatest { error ->
            error.let {
                Toast.makeText(context, it.description, Toast.LENGTH_SHORT).show()
            }
        }
    }
}