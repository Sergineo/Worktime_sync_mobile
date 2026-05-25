package io.hornet.worktimesync.authorization.domain.screen_model

data class AuthorizationFragment(
    val email: TextField = TextField(),
    val password: TextField = TextField()
)