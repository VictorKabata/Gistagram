package com.vickikbt.gistagram.di

import com.vickikbt.gistagram.ui.screens.auth.AuthViewModel
import com.vickikbt.gistagram.ui.screens.profile.ProfileViewModel
import com.vickikbt.gistagram.ui.screens.status.StatusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    // viewModel { MainViewModel(profileRepository = get()) }
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { ProfileViewModel(profileRepository = get()) }

    viewModel { StatusViewModel(profileRepository = get()) }
}
