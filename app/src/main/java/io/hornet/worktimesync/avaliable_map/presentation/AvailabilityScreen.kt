package io.hornet.worktimesync.avaliable_map.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.hornet.worktimesync.avaliable_map.domain.model.CellIntensity
import io.hornet.worktimesync.avaliable_map.presentation.view_model.AvailabilityViewModel
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema

@Composable
fun AvailabilityScreen(viewModel: AvailabilityViewModel, modifier: Modifier = Modifier) {
    val colors = ColorShema.current
    val matrix by viewModel.matrixState.collectAsState()
    val uiCells by viewModel.cellsState.collectAsState()
    val selectedDetails by viewModel.selectedDetails.collectAsState()

    val daysHeader = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
    val hours = listOf("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "24.05-31.05",
            fontSize = 16.sp,
            color = colors.onSurfaceVariant
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = colors.surface), // Подложка карточки
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Дни недели в шапке таблицы
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Дата", fontSize = 12.sp, modifier = Modifier.width(40.dp), color = colors.onSurfaceVariant, textAlign = TextAlign.Center)
                    daysHeader.forEach { Text(text = it, fontSize = 12.sp, modifier = Modifier.width(42.dp), textAlign = TextAlign.Center, color = colors.onSurfaceVariant) }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Строки по часам
                hours.forEach { hour ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = hour, fontSize = 12.sp, modifier = Modifier.width(40.dp), color = colors.onSurface, textAlign = TextAlign.Center)

                        repeat(7) { dayIdx ->
                            val uiCell = uiCells.find { it.cell.dayIndex == dayIdx && it.cell.hour == hour }

                            val cellColor = when (uiCell?.intensity) {
                                CellIntensity.GREEN -> if (isSystemInDarkTheme()) Color(0xFF0F3D1B) else Color(0xFFD4EDDA)
                                CellIntensity.YELLOW -> if (isSystemInDarkTheme()) Color(0xFF3D350F) else Color(0xFFFFF3CD)
                                CellIntensity.RED -> if (isSystemInDarkTheme()) Color(0xFF421216) else Color(0xFFF8D7DA)
                                else -> colors.surface
                            }

                            val isTargetCell = (hour == "12:00" && dayIdx == 3)

                            Box(
                                modifier = Modifier
                                    .size(width = 42.dp, height = 28.dp)
                                    .background(cellColor)
                                    .border(
                                        width = 1.dp,
                                        color = if (isTargetCell) colors.primary else colors.outline
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = uiCell?.cell?.availableCount?.toString() ?: "22",
                                    fontSize = 11.sp,
                                    color = if (uiCell?.intensity == CellIntensity.RED) Color(0xFFE4606A) else colors.onSurface,
                                    fontWeight = if (isTargetCell) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }

        Text(
            text = "Всего сотрудников: ${matrix?.totalEmployees ?: 23}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface
        )

        // Нижний блок деталей ячейки ЧТ 12:00
        selectedDetails?.let { details ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Чт, 28 мая, 12:00",
                        fontSize = 16.sp,
                        color = colors.onSurfaceVariant,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Доступно ${details.available_count} из ${details.total_count} сотрудников",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Не доступно ${details.unavailable_employees.size}",
                            fontSize = 14.sp,
                            color = colors.onSurface
                        )
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = colors.onSurface
                        )
                    }

                    if (expanded) {
                        Column(modifier = Modifier.padding(top = 8.dp).fillMaxWidth()) {
                            details.unavailable_employees.forEach { employee ->
                                Text(
                                    text = "• ${employee.last_name} ${employee.first_name}",
                                    fontSize = 14.sp,
                                    color = colors.onSurfaceVariant,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}