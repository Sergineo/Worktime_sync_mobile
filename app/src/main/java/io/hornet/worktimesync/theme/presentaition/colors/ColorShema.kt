package io.hornet.worktimesync.theme.presentaition.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color as ComposeColor
import io.hornet.worktimesync.theme.domain.ColorSystem

val ColorShema = staticCompositionLocalOf {
    ColorSystem(
        primary = ComposeColor.Unspecified,
        onPrimary = ComposeColor.Unspecified,
        secondary = ComposeColor.Unspecified,
        onSecondary = ComposeColor.Unspecified,
        background = ComposeColor.Unspecified,
        onBackground = ComposeColor.Unspecified,
        surface = ComposeColor.Unspecified,
        onSurface = ComposeColor.Unspecified,
        onSurfaceVariant = ComposeColor.Unspecified,
        outline = ComposeColor.Unspecified,
    )
}