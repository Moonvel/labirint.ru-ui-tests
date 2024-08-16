group = "ru.moonvel"
version = "1.0-SNAPSHOT"

plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}


val junitVersion = "5.10.3"
val assertJVersion = "3.26.3"
val allureVersion = "2.29.0"
val aspectJVersion = "1.9.22"
val selenideVersion = "7.4.2"
val lombokVersion = "1.18.32"

dependencies {
    // JUnit 5 - core test framework
    implementation(platform("org.junit:junit-bom:$junitVersion"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.junit.platform:junit-platform-launcher")
    // Assertions library
    implementation("org.assertj:assertj-core:$assertJVersion")
    // Allure support for JUnit 5
    implementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    implementation("io.qameta.allure:allure-selenide")
    implementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-assertj")
    implementation("org.aspectj:aspectjweaver:${aspectJVersion}")
    // UI testing core library
    implementation("com.codeborne:selenide:$selenideVersion")
    // Lombok - no boilerplate code
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    jvmArgs = listOf(
        "-Dfile.encoding=UTF-8"
    )

    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}
