package io.hornet.worktimesync.authorization.presentation.navigation.router

import androidx.navigation.NavHostController
import io.hornet.worktimesync.authorization.domain.screen_model.NextButtonScreenEvents

interface AuthorizationRouter {
    fun goToProfileScreen(events: NextButtonScreenEvents, call: () -> NavHostController)
}