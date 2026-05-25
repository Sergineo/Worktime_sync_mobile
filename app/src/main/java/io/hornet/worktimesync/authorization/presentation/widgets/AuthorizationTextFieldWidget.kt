package io.hornet.worktimesync.authorization.presentation.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.authorization.domain.screen_model.TextField
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import io.hornet.worktimesync.theme.presentaition.text.TypographyShema
import androidx.compose.ui.graphics.Color as ComposeColor


@Composable
fun ColumnScope.AuthorizationTextFieldWidget(
    event: (textFiled: TextField) -> Unit,
    isError: Boolean,
    message: String,
    stringResource: Int,
    errorStringResource: Int?
) {
    OutlinedTextField(
        value = message,
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
            event(TextField(it, isError = false))
        },
        label = {
            val labelText = if (isError) {
                stringResource(id = errorStringResource!!)
            } else {
                stringResource(id = stringResource)
            }

            Text(
                text = labelText,
                fontFamily = TypographyShema,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        },
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 12.dp, start = 40.dp, end = 40.dp)
            .align(Alignment.CenterHorizontally),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ColorShema.current.onSurface,
            unfocusedTextColor = ColorShema.current.onSurface,

            // КРИТИЧЕСКИ ВАЖНО: Явный цвет текста при ошибке, чтобы он не пропадал и не сливался
            errorTextColor = ColorShema.current.onSurface,

            // Цвета для Лейбла/Хинта
            focusedLabelColor = ColorShema.current.primary,
            unfocusedLabelColor = ColorShema.current.onSurfaceVariant,
            errorLabelColor = ComposeColor.Red,

            // Цвета контейнера (фона) поля — берем строго из вашей темы
            focusedContainerColor = ColorShema.current.surface,
            unfocusedContainerColor = ColorShema.current.surface,
            errorContainerColor = ColorShema.current.surface,

            // Цвета рамки
            focusedBorderColor = ColorShema.current.primary,
            unfocusedBorderColor = ColorShema.current.outline,
            errorBorderColor = ComposeColor.Red,

            // Курсор
            cursorColor = ColorShema.current.primary,
            errorCursorColor = ComposeColor.Red
        )
    )
}