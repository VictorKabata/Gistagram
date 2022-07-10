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
    // implementation(project(":core:library"))

    implementation(compose.desktop.currentOs)

    implementation(MultiplatformDependencies.kotlinxJvm)

    // HTTP Server
    implementation(MultiplatformDependencies.ktorServerCore)
    implementation(MultiplatformDependencies.ktorServerNetty)
}

compose.desktop {
    application {
        mainClass = "DesktopApplicationKt"
    }
}
