plugins {
    kotlin("jvm") version "1.7.10"
    application
}

application {
    // defines the main class for the application
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}