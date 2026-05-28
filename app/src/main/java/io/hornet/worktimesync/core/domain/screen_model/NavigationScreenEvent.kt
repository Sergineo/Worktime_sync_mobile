package io.hornet.worktimesync.core.domain.screen_model

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationScreenEvent {
    @Serializable
    data object AuthorizationScreenPoint : NavigationScreenEvent

    @Serializable
    data object ProfileScreenPoint : NavigationScreenEvent

    @Serializable
    data object MapScreenPoint : NavigationScreenEvent

    @Serializable
    data object ConflictScreenPoint : NavigationScreenEvent
}