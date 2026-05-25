package io.hornet.worktimesync.authorization.domain.screen_model

sealed interface TextFieldScreenEvents {
    val textField: TextField

    sealed interface TextFieldModeFragment : TextFieldScreenEvents {
        data class EmailChanged(override val textField: TextField) : TextFieldModeFragment
        data class PasswordChanged(override val textField: TextField) : TextFieldModeFragment
    }

    sealed interface RegistrationModeFragment : TextFieldScreenEvents {
        data class EmailChanged(override val textField: TextField) : RegistrationModeFragment
        data class NewPasswordChanged(override val textField: TextField) : RegistrationModeFragment
        data class RepeatPasswordChanged(override val textField: TextField) :
            RegistrationModeFragment
    }
}