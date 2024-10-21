plugins { java }

repositories { mavenCentral() }

dependencies {
  val junitVersion = "5.11.3"
  testImplementation(platform("org.junit:junit-bom:$junitVersion"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  val assertjVersion = "3.26.3"
  testImplementation("org.assertj:assertj-core:$assertjVersion")

  val jqwikVersion = "1.9.1"
  testImplementation("net.jqwik:jqwik:$jqwikVersion")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

tasks.withType<Test> { useJUnitPlatform() }
