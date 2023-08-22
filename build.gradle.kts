plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "me.ichwan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
}

tasks.test {
    useJUnitPlatform{
        excludeTags("integration-test")
    }
}

tasks.register("integration-test", Test::class) {
//    useJUnitPlatform {
//        includeTags("integration-test")
//    }
    useJUnitPlatform {}
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}