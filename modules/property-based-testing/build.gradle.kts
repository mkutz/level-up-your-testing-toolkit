plugins { java }

repositories { mavenCentral() }

dependencies {
  val junitVersion = "5.10.2"
  testImplementation(platform("org.junit:junit-bom:$junitVersion"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.junit.jupiter:junit-jupiter-params")
  val assertjVersion = "3.17.1"
  testImplementation("org.assertj:assertj-core:$assertjVersion")

  val jqwikVersion = "1.8.5"
  testImplementation("net.jqwik:jqwik:$jqwikVersion")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

tasks.withType<Test> { useJUnitPlatform() }
