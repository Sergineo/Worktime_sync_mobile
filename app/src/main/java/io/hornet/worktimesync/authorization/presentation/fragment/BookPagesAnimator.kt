package io.hornet.worktimesync.authorization.presentation.fragment

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BookPagesAnimator(
    modifier: Modifier,
    selectedIndex: Int,
    content: @Composable (valueParameter: Int) -> Unit
) {
    AnimatedContent(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .verticalScroll(rememberScrollState()),
        targetState = selectedIndex,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { width ->
                    width
                } togetherWith slideOutHorizontally { width ->
                    -width
                }
            } else {
                slideInHorizontally { width ->
                    -width
                } togetherWith slideOutHorizontally { width ->
                    width
                }
            }
        }
    ) { valueParameter ->
        Column(modifier = Modifier.fillMaxSize()) {
            content(valueParameter)
        }
    }
}