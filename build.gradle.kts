plugins { id("com.diffplug.spotless") version "6.25.0" }

repositories { mavenCentral() }

spotless {
  format("misc") {
    target("**/*.md", "**/*.xml", "**/*.html", "**/*.css", ".gitignore")
    targetExclude("**/build/**/*", "**/.idea/**")
    trimTrailingWhitespace()
    endWithNewline()
    indentWithSpaces(2)
  }

  java {
    target("**/*.java")
    googleJavaFormat()
  }

  freshmark { target("*.md") }

  format("html") {
    target("**/*.html")
    prettier().config(mapOf("tabWidth" to 2))
  }

  kotlinGradle {
    target("**/*.gradle.kts")
    targetExclude("**/build/**/*.gradle.kts")
    ktfmt().googleStyle()
  }
}
