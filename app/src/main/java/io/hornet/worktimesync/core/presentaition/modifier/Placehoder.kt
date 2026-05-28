package io.hornet.worktimesync.core.presentaition.modifier

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

fun Modifier.shimmerPlaceholder(
    visible: Boolean,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp)
): Modifier = composed {
    if (!visible) return@composed this

    val colors = ColorShema.current
    val shimmerColors = listOf(
        colors.onSurface.copy(alpha = 0.08f),
        colors.onSurface.copy(alpha = 0.15f),
        colors.onSurface.copy(alpha = 0.08f)
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    this.background(brush = brush, shape = shape)
}