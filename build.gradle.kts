import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    kotlin("jvm") version "1.7.22"
    id("org.jetbrains.dokka") version "1.6.10"
    id("maven-publish")
}

group = "io.github.pallocchi"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.springframework.data:spring-data-jdbc:3.0.4")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.4")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.0.4")
    testImplementation("org.liquibase:liquibase-core:4.20.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("com.h2database:h2:2.1.214")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

// Docs

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Kuery")
            includes.from("Module.md")
            samples.from("$rootDir/src/test/kotlin")
            perPackageOption {
                matchingRegex.set("kuery.repositories")
                suppress.set(true)
            }
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/pallocchi/kuery/tree/master/src/main/kotlin"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

// Publish

publishing {
    publications {
        create<MavenPublication>("kuery") {
            from(components["java"])
        }
    }
}