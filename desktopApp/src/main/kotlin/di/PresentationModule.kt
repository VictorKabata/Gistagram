package di

import org.koin.dsl.module
import ui.screens.auth.AuthViewModel
import ui.screens.main.MainViewModel
import ui.screens.profile.ProfileViewModel

val presentationModule = module {

    single { MainViewModel(authRepository = get()) }

    single { AuthViewModel(authRepository = get()) }
    single { ProfileViewModel(profileRepository = get()) }
}
