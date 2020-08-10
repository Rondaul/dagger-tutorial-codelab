package com.example.android.dagger.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.UserManager
import androidx.lifecycle.Observer
import androidx.test.espresso.idling.CountingIdlingResource
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
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.splashComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            splashViewModel.checkAppState()
        }, 3000)
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

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}

sealed class AppState
object LoginState : AppState()
object RegistrationState : AppState()
object MainPageState : AppState()