package com.vickikbt.gistagram.di

import com.vickikbt.gistagram.ui.screens.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { ProfileViewModel(get()) }

}