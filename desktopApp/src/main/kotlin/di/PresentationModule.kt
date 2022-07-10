package di

import org.koin.dsl.module
import ui.screens.auth.AuthViewModel

val presentationModule = module {

    single { AuthViewModel(authRepository = get()) }

}
