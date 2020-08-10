package com.example.android.dagger.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.dagger.LiveDataTestUtil
import com.example.android.dagger.user.UserManager
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SplashViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userManager: UserManager
    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        userManager = mock(UserManager::class.java)
        splashViewModel = SplashViewModel(userManager)
    }

    @Test
    fun `AppState emits LoginState`() {
        `when`(userManager.isUserLoggedIn()).thenReturn(false)
        `when`(userManager.isUserRegistered()).thenReturn(true)

        splashViewModel.checkAppState()

        assertEquals(LiveDataTestUtil.getValue(splashViewModel.appState), LoginState)
    }

    @Test
    fun `AppState emits RegistrationState`() {
        `when`(userManager.isUserLoggedIn()).thenReturn(false)
        `when`(userManager.isUserRegistered()).thenReturn(false)

        splashViewModel.checkAppState()
        assertEquals(LiveDataTestUtil.getValue(splashViewModel.appState), RegistrationState)
    }

    @Test
    fun `AppState emits MainPageState`() {
        `when`(userManager.isUserLoggedIn()).thenReturn(true)
        `when`(userManager.isUserRegistered()).thenReturn(true)

        splashViewModel.checkAppState()
        assertEquals(LiveDataTestUtil.getValue(splashViewModel.appState), MainPageState)
    }
}