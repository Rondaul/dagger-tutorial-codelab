package com.example.android.dagger.di

import com.example.android.dagger.storage.FakeStorage
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestStorageModule::class, AppSubcomponents::class])
interface TestAppComponent : AppComponent