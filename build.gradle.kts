import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.10"
    id("maven-publish")
}

group = "io.github.pallocchi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.springframework.data:spring-data-jdbc:2.3.3")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.5")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc:2.6.5")
    testImplementation("org.liquibase:liquibase-core:4.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("com.h2database:h2:2.1.210")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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