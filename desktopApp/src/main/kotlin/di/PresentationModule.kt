package di

import org.koin.dsl.module
import ui.screens.auth.AuthViewModel
import ui.screens.home.HomeViewModel
import ui.screens.main.MainViewModel
import ui.screens.profile.ProfileViewModel

val presentationModule = module {

    single { MainViewModel(authRepository = get(), settingsRepository = get()) }

    single { AuthViewModel(authRepository = get()) }
    single { HomeViewModel(authRepository = get()) }
    single { ProfileViewModel(profileRepository = get(), settingsRepository = get()) }
}
