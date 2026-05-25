package io.hornet.worktimesync.authorization.presentation.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.R
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import io.hornet.worktimesync.theme.presentaition.text.TypographyShema

@Composable
fun ColumnScope.NextButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 30.dp, end = 30.dp, top = 80.dp, bottom = 20.dp)
            .align(Alignment.CenterHorizontally),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorShema.current.primary,
            disabledContainerColor = ColorShema.current.secondary,
            contentColor = ColorShema.current.onPrimary,
            disabledContentColor = ColorShema.current.onSecondary
        )
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = stringResource(R.string.next),
            fontFamily = TypographyShema,
            fontWeight = FontWeight.Medium,
            color = if (enabled) ColorShema.current.onPrimary
            else ColorShema.current.onSecondary,
            fontSize = 20.sp
        )
    }
}
