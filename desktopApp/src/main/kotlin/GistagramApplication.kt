import androidx.compose.ui.window.application
import com.vickikbt.shared.di.initKoin
import di.presentationModule
import org.koin.core.Koin
import ui.screens.main.MainScreen

lateinit var koin: Koin

fun main() {
    val desktopModules = listOf(presentationModule)

    koin = initKoin(isDebug = false) { modules(desktopModules) }.koin

    return application {
        MainScreen(applicationScope = this)
    }
}
