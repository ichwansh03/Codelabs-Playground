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
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform{
        excludeTags("integration-test")
    }
}

tasks.register("integration-test", Test::class) {
    useJUnitPlatform {
        includeTags("integration-test")
    }
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}