plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    // implementation ("org.springframework.boot:spring-boot-starter-oauth2-client:3.3.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    // implementation ("org.springframework.boot:spring-boot-starter-web:3.3.0")

    implementation ("org.springframework.boot:spring-boot-starter-security")
    // implementation ("org.springframework.boot:spring-boot-starter-security:3.3.0")

    implementation ("org.springframework.security:spring-security-oauth2-client")
    // implementation ("org.springframework.security:spring-security-oauth2-client:6.3.1")

    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
