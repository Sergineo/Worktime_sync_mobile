package io.hornet.worktimesync.authorization.domain.screen_model

data class RegistrationFragment(
    val username: TextField = TextField(),
    val email: TextField = TextField(),
    val password: TextField = TextField(),
    val repeatPassword: TextField = TextField(),
    val companyName: TextField = TextField(),
    val selectedRole: Int = 0,
    val roles: List<Int> = listOf(

    )
)