plugins {
    kotlin(Plugins.jvm)
    id(Plugins.compose) version Versions.composeDesktop
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(project(":shared"))
    //implementation(project(":shared"))
    // implementation(project(":core:library"))

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "DesktopApplicationKt"
    }
}
