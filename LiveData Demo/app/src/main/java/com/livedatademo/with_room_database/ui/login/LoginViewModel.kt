package com.livedatademo.with_room_database.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.livedatademo.R
import com.livedatademo.with_room_database.data.LoginDataSource
import com.livedatademo.with_room_database.data.LoginRepository
import com.livedatademo.with_room_database.data.Result


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext = application.applicationContext
    private val loginRepository = LoginRepository(LoginDataSource(appContext))
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.userName))
        } else if (result is Result.ErrorMsg) {
            _loginResult.value = LoginResult(error = result.msg)
        } else if (result is Result.Error) {
            _loginResult.value =
                LoginResult(error = appContext.getString(R.string.login_failed))
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}