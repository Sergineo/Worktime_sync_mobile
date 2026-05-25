package io.hornet.worktimesync.authorization.presentation.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel
import io.hornet.worktimesync.theme.presentaition.colors.ColorShema
import io.hornet.worktimesync.theme.presentaition.text.TypographyShema

@Composable
fun ColumnScope.SelectedAuthorizationModeButton(
    modifier: Modifier,
    viewModel: AuthorizationScreenViewModel,
    authorizationOptions: List<Int>,
    selectedIndex: Int
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .then(modifier)
    ) {
        authorizationOptions.forEachIndexed { index, authorizationOption ->
            SegmentedButton(
                icon = {},
                modifier = Modifier.weight(0.2f),
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = authorizationOptions.size,
                    baseShape = RoundedCornerShape(10.dp)
                ),
                onClick = {
                    viewModel.setSelectedAuthorizationMode(index = index)
                },
                selected = index == selectedIndex,
                label = {
                    Text(
                        text = stringResource(id = authorizationOption),
                        fontFamily = TypographyShema,
                        fontWeight = FontWeight.Medium
                    )
                },

                colors = SegmentedButtonColors(
                    activeContainerColor = ColorShema.current.primary,
                    activeContentColor = ColorShema.current.onPrimary,
                    activeBorderColor = ColorShema.current.primary,
                    inactiveContainerColor = ColorShema.current.secondary,
                    inactiveContentColor = ColorShema.current.onSecondary,
                    inactiveBorderColor = ColorShema.current.outline,
                    disabledActiveContainerColor = ColorShema.current.secondary.copy(alpha = 0.5f),
                    disabledActiveContentColor = ColorShema.current.onSecondary.copy(alpha = 0.5f),
                    disabledInactiveContainerColor = ColorShema.current.secondary.copy(alpha = 0.5f),
                    disabledInactiveContentColor = ColorShema.current.onSecondary.copy(alpha = 0.5f),
                    disabledActiveBorderColor = ColorShema.current.outline.copy(alpha = 0.5f),
                    disabledInactiveBorderColor = ColorShema.current.outline.copy(alpha = 0.5f)
                )
            )
        }
    }
}