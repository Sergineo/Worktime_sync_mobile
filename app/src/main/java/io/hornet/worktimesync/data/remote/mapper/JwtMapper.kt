package io.hornet.worktimesync.data.remote.mapper

import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.data.remote.dto.RegistrationDto

fun RegistrationFragment.toDto() = RegistrationDto(
    email = this.email.message.toString(),
    password = this.password.message.toString()
)