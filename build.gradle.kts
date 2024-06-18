plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    application
}

group = "org.paperrockscissors"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.paperrockscissors.PaperRockScissorsKt")
}

repositories {
    mavenCentral()

    maven {
        url = uri("org.jetbrains.kotlinx")
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines:0.19.2")
}

tasks.test {
    useJUnitPlatform()
}

// val sourceSets = project.extensions.getByName("sourceSets") as SourceSetContainer
// val mainClasspath = sourceSets.getByName("main").runtimeClasspath
//
// tasks.named("run") {
//     doFirst {
//         javaexec {
//             main = "org.paperrockscissors.PaperRockScissorsKt"
//             classpath = mainClasspath
//             standardInput = System.`in`
//         }
//     }
// }

kotlin {
    jvmToolchain(17)
}