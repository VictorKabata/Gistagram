buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}")
        // classpath("de.jensklingenberg.cabret:cabret-gradle:${Versions.cabretLog}")
        // classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}")
    }
}
