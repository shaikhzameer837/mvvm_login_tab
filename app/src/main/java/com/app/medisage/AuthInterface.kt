package com.app.medisage

interface AuthInterface {
    fun onProgressStart()
    fun onSuccess()
    fun onFailure(message:String)
    fun onSetEmailError(message: String?)
    fun onMessage(message: String?)
    fun onSetPasswordError(message: String?)
}