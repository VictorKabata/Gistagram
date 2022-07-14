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
    implementation(project(":core:library"))

    implementation(compose.desktop.currentOs)

    implementation(MultiplatformDependencies.kotlinxJvm)

    // HTTP Server
    implementation(MultiplatformDependencies.ktorServerCore)
    implementation(MultiplatformDependencies.ktorServerNetty)
    implementation("androidx.compose.ui:ui-text:1.1.1")
}

compose.desktop {
    application {
        mainClass = "DesktopApplicationKt"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = ".GistagramApplicationKt"
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
            )
            packageName = "Gistagram"
            packageVersion = "1.0.0"
        }
    }
}
