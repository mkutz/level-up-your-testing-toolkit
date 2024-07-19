plugins {
  java
  jacoco
  id("info.solidsoft.pitest") version "1.15.0"
}

repositories { mavenCentral() }

dependencies {
  val junitVersion = "5.9.3"
  testImplementation(platform("org.junit:junit-bom:$junitVersion"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  val assertjVersion = "3.26.0"
  testImplementation("org.assertj:assertj-core:$assertjVersion")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

tasks.withType<Test> { useJUnitPlatform() }

tasks.jacocoTestReport {
  reports {
    xml.required = true
    html.required = true
  }
}

pitest {
  pitestVersion = "1.16.1"
  targetTests = setOf("io.github.mkutz.qac.mutationtesting.*")
  targetClasses = setOf("io.github.mkutz.qac.mutationtesting.*")
  threads = 4
  outputFormats = setOf("XML", "HTML")
  timestampedReports = false
  junit5PluginVersion = "1.0.0"
  verbose = false
  testStrengthThreshold = 80
}

tasks.check { dependsOn(tasks.pitest) }
