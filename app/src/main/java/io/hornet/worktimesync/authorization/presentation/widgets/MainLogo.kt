package io.hornet.worktimesync.authorization.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.hornet.worktimesync.R

@Composable
fun ColumnScope.MainLogo(
    modifier: Modifier
){
    Image(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .then(modifier),
        painter = painterResource(R.drawable.main_header_logo),
        contentDescription = null
    )
}