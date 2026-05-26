package io.hornet.worktimesync.authorization.domain.interactor

import android.util.Log
import io.hornet.worktimesync.R
import io.hornet.worktimesync.authorization.domain.User
import io.hornet.worktimesync.authorization.domain.screen_model.AuthorizationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.RegistrationFragment
import io.hornet.worktimesync.authorization.domain.screen_model.TextField
import io.hornet.worktimesync.core.domain.model.JwtToken

typealias ValidateTextFieldProcessor = (event: TextField) -> TextField
typealias ValidateRepeatPasswordProcessor = (
    event: TextField,
    password: String?,
    repeatPassword: String?
) -> TextField

class AuthorizationInteractor(
    private val authorizationRepository: AuthorizationRepository
){

    val validateEmptyTextFiled: ValidateTextFieldProcessor = { event ->
        if (!(event.message!!.isEmpty() || event.message.isBlank())) {
            Log.d("INPUT", event.message)
            TextField(message = event.message, isError = false)
        } else {
            Log.d("INPUT", event.message)
            TextField(
                message = event.message,
                isError = true,
                errorDescription = R.string.empty_text_field
            )
        }
    }

    val validateEmailTextField: ValidateTextFieldProcessor = { event ->
        if (event.message!!.isEmpty() || event.message.isBlank()) {
            Log.d("INPUT", event.message)
            TextField(
                message = event.message,
                isError = true,
                errorDescription = R.string.empty_text_field
            )
        }
        if (event.message.contains('@') && event.message.contains('.')) {
            Log.d("INPUT", event.message)
            TextField(message = event.message, isError = false)
        } else {
            Log.d("INPUT", event.message)
            TextField(
                message = event.message,
                isError = true,
                errorDescription = R.string.uncorrect_email_format
            )
        }
    }
    val validateRepeatPassword: ValidateRepeatPasswordProcessor =
        { event, password, repeatPassword ->
            if (repeatPassword!!.isEmpty() || repeatPassword.isBlank()) {
                TextField(
                    message = event.message,
                    isError = true,
                    errorDescription = R.string.empty_text_field
                )
            }
            if (password.equals(repeatPassword)) {
                TextField(message = event.message, isError = false)
            } else {
                TextField(
                    message = event.message,
                    isError = true,
                    errorDescription = R.string.uncorrect_repeat_password
                )
            }
        }

    suspend fun login(email: String, password: String): Result<JwtToken?>{
        return authorizationRepository.login(email, password)
    }
    suspend fun registration(registrationFragment: RegistrationFragment): Result<User?>{
        return authorizationRepository.registration(registrationFragment)
    }
    suspend fun saveLocalToken(jwtToken: JwtToken){
        authorizationRepository.saveLocalToken(jwtToken)
    }
}