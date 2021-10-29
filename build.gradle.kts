val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktorJsoupVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
}

group = "com.turntabl"
version = "0.0.1"
application {
    mainClass.set("com.turntabl.ApplicationKt")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("com.tfowl.ktor:ktor-jsoup:$ktorJsoupVersion")
    implementation("com.apptastic:rssreader:2.5.0")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")


}