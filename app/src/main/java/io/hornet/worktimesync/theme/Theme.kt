package io.hornet.worktimesync.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.hornet.worktimesync.theme.domain.ColorSystem
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import androidx.compose.ui.graphics.Color as ComposeColor


@Composable
fun Theme(
    content: @Composable () -> Unit
) {
    val lightColorSchema = ColorSystem(
        primary = ComposeColor(0xFF121A23),          // Темно-синий для главных кнопок (Stake HUMP)
        secondary = ComposeColor(0xFFE8E8E8),        // Серый для неактивных вкладок (UNSTAKE)
        onPrimary = ComposeColor(0xFFFFFFFF),        // Белый текст на темно-синих кнопках
        onSecondary = ComposeColor(0xFF121A23),      // Темно-синий/черный текст на серых вкладках
        background = ComposeColor(0xFFEBF0F3),       // Светло-серый фирменный фон приложения
        onBackground = ComposeColor(0xFF000000),     // Черный текст для заголовков
        surface = ComposeColor(0xFFFFFFFF),          // Чистый белый для подложек карточек и полей ввода
        onSurface = ComposeColor(0xFF000000),        // Черный текст внутри карточек
        onSurfaceVariant = ComposeColor(0xFF121A23).copy(alpha = 0.6f), // Приглушенный темно-синий для "Your Balance"
        outline = ComposeColor(0xFFE8E8E8)           // Серый для границ полей
    )

    val darkColorSchema = ColorSystem(
        primary = ComposeColor(0xFF6BFF95),          // Ярко-зеленый акцент для кнопок в темной теме
        secondary = ComposeColor(0xFF121A23),        // Темно-синий для неактивных элементов
        onPrimary = ComposeColor(0xFF000000),        // Черный текст для идеального контраста на зеленом фоне
        onSecondary = ComposeColor(0xFFE8E8E8),      // Серый текст на темно-синих вкладках
        background = ComposeColor(0xFF000000),       // Чистый черный фон приложения
        onBackground = ComposeColor(0xFFFBF3F3),     // Светло-серый текст для заголовков
        surface = ComposeColor(0xFF121A23),          // Темно-синий для карточек и полей ввода
        onSurface = ComposeColor(0xFFFFFFFF),        // Белый текст внутри карточек
        onSurfaceVariant = ComposeColor(0xFF6BFF95), // Зеленый для важных цифр (ROI, доходность) или серый E8E8E8 для обычных подписей
        outline = ComposeColor(0xFFE8E8E8).copy(alpha = 0.2f) // Полупрозрачная граница для темной темы
    )
    CompositionLocalProvider(
        if (isSystemInDarkTheme()) ColorShema provides darkColorSchema
        else ColorShema provides lightColorSchema,
        content = content
    )
}