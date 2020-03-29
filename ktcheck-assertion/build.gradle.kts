import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.70"
}

val projectVersion: String by project

group = "org.mikeneck.ktcheck"
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
  mavenCentral()
}

dependencies {
  val kotlinVersion: String by project
  api(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
  api(project(":ktcheck-api"))

  implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))

  testRuntimeOnly(project(":ktcheck-engine"))
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}