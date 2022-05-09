package com.app.medisage.viewmodel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.app.medisage.utils.AppConstant
import com.app.medisage.AuthInterface
import com.app.medisage.data.db.entity.User
import com.app.medisage.data.repository.UserRepository
import com.app.medisage.utils.Coroutines


class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var et_email: String = ""
    var et_password: String = ""
    var authInterface: AuthInterface? = null
    var isAuthButtonEnabled = ObservableBoolean(false)
    suspend fun insertUser(user: User) = repository.insertUser(user)

    suspend fun updateUser(user: User) = repository.updateUser(user)

    suspend fun deleteUser(user: User) = repository.deleteUser(user)

    suspend fun deleteUserById(id: Int) = repository.deleteUserById(id)

    suspend fun clearUser() = repository.clearUser()

    fun getAllUsers() = repository.getAllUsers()
    fun onSubmitButtonClick(view: View) {
        val id =  null
        val email = et_email
        val password = et_password
        val user = User(id = id, email = email, password = password)
        Coroutines.main {
            //for insert note
               insertUser(user).also {
                   authInterface?.onMessage("successful")
                   authInterface?.onSuccess()
            }
        }
    }

    fun onEmailTextChanged(text: CharSequence) {
        if (!AppConstant.isValidEmail(text)) {
            authInterface?.onSetEmailError("Invalid Email id")
            isAuthButtonEnabled.set(false)
            return
        }
        authInterface?.onSetEmailError(null)
        if (AppConstant.isValidPassword(et_password))
            isAuthButtonEnabled.set(true)
    }

    fun onPasswordTextChanged(text: CharSequence) {
        if (!AppConstant.isValidPassword(text)) {
            authInterface?.onSetPasswordError("Password size limitation between 8 - 15 characters")
            isAuthButtonEnabled.set(false)
            return
        }
        authInterface?.onSetPasswordError(null)
        if (AppConstant.isValidEmail(et_email))
            isAuthButtonEnabled.set(true)
    }
}