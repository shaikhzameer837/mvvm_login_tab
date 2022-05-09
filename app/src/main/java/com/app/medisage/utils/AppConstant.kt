package com.app.medisage.utils

import android.text.TextUtils
import android.util.Patterns

object AppConstant {
    fun isValidEmail(email: CharSequence): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(password: CharSequence): Boolean {
        return password.length in 8..15
    }
}