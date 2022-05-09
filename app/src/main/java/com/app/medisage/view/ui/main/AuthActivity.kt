package com.app.medisage.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.medisage.AuthInterface
import com.app.medisage.viewmodel.AuthViewModel
import com.app.medisage.data.db.AppDataBase
import com.app.medisage.data.repository.UserRepository
import com.app.medisage.databinding.AuthActivityBinding
import com.app.medisage.UserViewModelFactory
import com.app.myapplication.utils.hide
import com.app.myapplication.utils.openActivity
import com.app.myapplication.utils.show
import com.app.myapplication.utils.toast

class AuthActivity : AppCompatActivity(), AuthInterface {
    private lateinit var binding: AuthActivityBinding
    private lateinit var factory: UserViewModelFactory
    private lateinit var appDataBase: AppDataBase
    private lateinit var repository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthActivityBinding.inflate(layoutInflater)
        appDataBase = AppDataBase(this)
        repository = UserRepository(appDataBase)
        factory = UserViewModelFactory(repository)
        val authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        binding.authViewModel = authViewModel
        setContentView(binding.root)
        authViewModel.authInterface = this
    }

    override fun onProgressStart() {
        binding.progressBar.show()
    }

    override fun onSuccess() {
        openActivity(MainActivity::class.java) {

        }
    }
   override fun onMessage(message: String?) {
       toast(message!!)
    }

    override fun onFailure(message: String) {
        toast(message)
        binding.progressBar.hide()
    }

    override fun onSetEmailError(message: String?) {
        binding.etEmail.error = message
    }
    override fun onSetPasswordError(message: String?) {
        binding.etPassword.error = message
    }
}