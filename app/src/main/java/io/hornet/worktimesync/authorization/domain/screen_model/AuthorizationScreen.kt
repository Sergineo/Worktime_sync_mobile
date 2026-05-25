package io.hornet.worktimesync.authorization.domain.screen_model

import io.hornet.worktimesync.R

data class AuthorizationScreen(
    var selectedMode: Int = 0,
    val authorizationMode: List<Int> = listOf(
        R.string.authorization,
        R.string.registration
    ),
    val authorizationFragment: AuthorizationFragment = AuthorizationFragment(),
    val registrationFragment: RegistrationFragment = RegistrationFragment()
)