package io.hornet.worktimesync.profile.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.profile.presentation.widgets.ProfileCard
import io.hornet.worktimesync.profile.presentation.widgets.ProfileRow
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Preview
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val colors = ColorShema.current
    val scrollState = rememberScrollState()

    val centerColor by animateColorAsState(
        targetValue = if (colors.background == Color(0xFF000000)) Color(0xFF0C2417) else Color(0xFFE2FCE9),
        animationSpec = tween(durationMillis = 300)
    )
    val edgeColor by animateColorAsState(
        targetValue = colors.background,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Блок: Личные данные
        ProfileCard(title = "Личные данные") {
            ProfileRow(label = "ФИО", value = "Иванов Иван Иванович")
            ProfileRow(label = "В компании с", value = "20.06.2019")
            ProfileRow(label = "Должность", value = "Старший разработчик")
            ProfileRow(label = "Отдел", value = "Разработка ПО")
            ProfileRow(label = "Gmail", value = "loveyourmother@gmail.com")
        }

        // Блок: Рабочее расписание
        ProfileCard(title = "Рабочее расписание") {
            ProfileRow(label = "Рабочие дни", value = "ПН-ПТ")
            ProfileRow(label = "Время начала", value = "08:00")
            ProfileRow(label = "Время окончания", value = "17:00")
            ProfileRow(label = "Часовой пояс", value = "MSK+3")
            ProfileRow(label = "Формат работы", value = "Гибридный")

            Spacer(modifier = Modifier.height(10.dp))

            // Выделение даты обновления стиля
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Обновлено:",
                    fontSize = 13.sp,
                    color = colors.onSurfaceVariant // Приглушенный темный текст
                )
                Text(
                    text = "27.05.2026",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.primary // Темно-синий акцент (0xFF121A23)
                )
            }
        }

        // Блок: Исключения
        ProfileCard(title = "Исключения") {
            Text(
                text = "Список пуст",
                fontSize = 14.sp,
                color = colors.onSurfaceVariant.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}