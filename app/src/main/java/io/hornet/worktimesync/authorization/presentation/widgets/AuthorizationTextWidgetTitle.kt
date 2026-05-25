package io.hornet.worktimesync.authorization.presentation.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun AuthorizationTextWidgetTitle(
    stringResource: Int
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 30.dp, bottom = 10.dp),
        text = stringResource(id = stringResource),
        color = ColorShema.current.onBackground,
        textAlign = TextAlign.Center,
        fontSize = 14.sp
    )
}