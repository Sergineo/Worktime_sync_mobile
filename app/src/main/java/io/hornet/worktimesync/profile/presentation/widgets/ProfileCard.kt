package io.hornet.worktimesync.profile.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = ColorShema.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.14.dp))
            .background(colors.surface.copy(alpha = 0.7f))
//            .border(
//                width = 1.dp, color = colors.outline,
//                shape = RoundedCornerShape(18.14.dp)
//            )
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        content()
    }
}