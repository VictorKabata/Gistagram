package com.vickikbt.gistagram.di

import com.vickikbt.gistagram.ui.activity.MainViewModel
import com.vickikbt.gistagram.ui.screens.auth.AuthViewModel
import com.vickikbt.gistagram.ui.screens.home.HomeViewModel
import com.vickikbt.gistagram.ui.screens.profile.ProfileViewModel
import com.vickikbt.gistagram.ui.screens.settings.SettingsViewModel
import com.vickikbt.gistagram.ui.screens.status.StatusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MainViewModel(authRepository = get(), get()) }
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { HomeViewModel(feedsRepository = get()) }
    viewModel { ProfileViewModel(profileRepository = get()) }
    viewModel { SettingsViewModel(settingsRepository = get()) }

    viewModel { StatusViewModel(profileRepository = get()) }
}
