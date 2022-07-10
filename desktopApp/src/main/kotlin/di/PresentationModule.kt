package di

import org.koin.dsl.module
import ui.screens.auth.AuthViewModel
import ui.screens.main.MainViewModel

val presentationModule = module {

    single { MainViewModel(authRepository = get()) }

    single { AuthViewModel(authRepository = get()) }
}
