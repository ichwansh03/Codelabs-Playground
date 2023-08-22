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
    testImplementation("org.mockito:mockito-junit-jupiter:5.4.0")
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
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}