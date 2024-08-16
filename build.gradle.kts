val junitVersion = "5.10.0"
val selenideVersion = "7.4.2"
val allureVersion = "2.29.0"
val aspectJVersion = "1.9.22.1"
val assertJVersion = "3.26.3"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

plugins {
    id("java")
}

group = "ru.moonvel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:$junitVersion"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:$selenideVersion")
    implementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    implementation("io.qameta.allure:allure-selenide")
    implementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-assertj")
    implementation("org.assertj:assertj-core:$assertJVersion")
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}