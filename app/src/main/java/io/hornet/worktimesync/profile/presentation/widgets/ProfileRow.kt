package io.hornet.worktimesync.profile.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.core.presentaition.modifier.shimmerPlaceholder
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun ProfileRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val colors = ColorShema.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = colors.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.weight(1f)
        )

        if (isLoading) {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .width(120.dp)
                    .shimmerPlaceholder(visible = true)
            )
        } else {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colors.onSurface,
                modifier = Modifier.weight(1.3f),
                textAlign = TextAlign.End
            )
        }
    }
}