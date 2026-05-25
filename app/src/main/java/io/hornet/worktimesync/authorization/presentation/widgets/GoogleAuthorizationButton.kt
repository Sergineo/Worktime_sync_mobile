package io.hornet.worktimesync.authorization.presentation.widgets

import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.common.SignInButton
import io.hornet.worktimesync.authorization.presentation.view_model.AuthorizationScreenViewModel

@Composable
fun GoogleAuthorizationButton(
    viewModel: AuthorizationScreenViewModel
) {
//    IconButton(
//        modifier = Modifier.size(70.dp),
//        onClick = {},
//        shape = RoundedCornerShape(10.dp),
//        colors = IconButtonDefaults.iconButtonColors(
//            containerColor = ColorShema.current.surface,
//            contentColor = ComposeColor.Transparent,
//            disabledContentColor = ComposeColor.Transparent,
//            disabledContainerColor = ColorShema.current.surface
//        ),
//        content = {
//            Row{
//                Icon(
//                    modifier = Modifier.fillMaxSize(),
//                    painter = painterResource(painter),
//                    contentDescription = null
//                )
//            }
//        }
//    )
    AndroidView(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        factory = { context ->
            SignInButton(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setSize(SignInButton.SIZE_WIDE)
            }
        },
        update = { view ->
            view.setOnClickListener { viewModel }
        }
    )
}