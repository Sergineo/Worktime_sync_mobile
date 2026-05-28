package io.hornet.worktimesync.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.core.presentaition.modifier.shimmerPlaceholder
import io.hornet.worktimesync.profile.domain.screen_model.ProfileUiState
import io.hornet.worktimesync.profile.presentation.view_model.ProfileViewModel
import io.hornet.worktimesync.profile.presentation.widgets.ProfileCard
import io.hornet.worktimesync.profile.presentation.widgets.ProfileRow
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val colors = ColorShema.current
    val scrollState = rememberScrollState()

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        when (val state = uiState) {
            is ProfileUiState.Loading -> {

                ProfileCard(title = "Личные данные") {
                    repeat(5) { ProfileRow(label = "Загрузка...", value = "", isLoading = true) }
                }

                ProfileCard(title = "Рабочее расписание") {
                    repeat(5) { ProfileRow(label = "Загрузка...", value = "", isLoading = true) }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Обновлено:", fontSize = 13.sp, color = colors.onSurfaceVariant)
                        Spacer(
                            modifier = Modifier
                                .height(14.dp)
                                .width(70.dp)
                                .shimmerPlaceholder(visible = true)
                        )
                    }
                }

                ProfileCard(title = "Исключения") {
                    ProfileRow(label = "Загрузка...", value = "", isLoading = true)
                }
            }

            is ProfileUiState.Success -> {
                val profile = state.profile

                ProfileCard(title = "Личные данные") {
                    ProfileRow(label = "ФИО", value = profile.fullName) // ФИО с отчеством из Интерактора
                    ProfileRow(label = "В компании с", value = "20.06.2019")
                    ProfileRow(label = "Должность", value = profile.position) // ИЗМЕНЕНО: Живое значение из БД
                    ProfileRow(label = "Отдел", value = "Разработка ПО")
                    ProfileRow(label = "Gmail", value = profile.email)
                }

                ProfileCard(title = "Рабочее расписание") {
                    ProfileRow(label = "Рабочие дни", value = profile.workDaysRu)
                    ProfileRow(
                        label = "Время начала", value = profile.workTime.substringBefore(" -")
                    )
                    ProfileRow(
                        label = "Время окончания", value = profile.workTime.substringAfter("- ")
                    )
                    ProfileRow(label = "Часовой пояс", value = profile.timeZone)
                    ProfileRow(label = "Формат работы", value = profile.workFormatRu)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Обновлено:", fontSize = 13.sp, color = colors.onSurfaceVariant
                        )
                        Text(
                            text = profile.updatedAtRu,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.primary
                        )
                    }
                }

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

            is ProfileUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}