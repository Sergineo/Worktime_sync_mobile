package io.hornet.worktimesync.authorization.domain.screen_model

interface NextButtonScreenEvents {
    data class AuthorizationClicked(
        val email: TextField = TextField(),
        val password: TextField = TextField()
    ) : NextButtonScreenEvents
    data class RegistrationClicked(
        val email: TextField = TextField(),
        val password: TextField = TextField()
    ) : NextButtonScreenEvents
}