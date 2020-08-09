package com.example.android.dagger.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import androidx.lifecycle.Observer
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.registration.RegistrationActivity_MembersInjector
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.checkAppState()
        setupObservers()
    }

    private fun setupObservers() {
        splashViewModel.appState.observe(this, Observer { appState ->
            when (appState) {
                is LoginState -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                is RegistrationState -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    finish()
                }
                is MainPageState -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        })
    }
}

sealed class AppState
object LoginState: AppState()
object RegistrationState: AppState()
object MainPageState: AppState()