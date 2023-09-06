package dev.akif.improveddeveloperexperiencewithjava21.patterns;

public sealed interface Language permits Language.Java, Language.Kotlin, Language.Scala {
    String version();

    record Java(String version) implements Language {}

    record Kotlin(String version) implements Language {}

    record Scala(String version) implements Language {}
}
