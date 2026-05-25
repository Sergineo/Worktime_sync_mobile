package io.hornet.worktimesync.authorization.domain.screen_model

interface NextButtonScreenEvents {
    data object AuthorizationClicked : NextButtonScreenEvents
    data object RegistrationClicked : NextButtonScreenEvents
}