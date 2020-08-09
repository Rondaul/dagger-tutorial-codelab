package com.example.android.dagger.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    private val _appState = MutableLiveData<AppState>()
    val appState: LiveData<AppState>
        get() = _appState

    fun checkAppState() {
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                _appState.value = RegistrationState
            } else {
                _appState.value = LoginState
            }
        } else {
            _appState.value = MainPageState
        }
    }

}