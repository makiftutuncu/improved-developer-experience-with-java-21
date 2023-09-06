package dev.akif.improveddeveloperexperiencewithjava21.patterns;

public class Patterns {
    public boolean isJava21(Language language) {
        return switch (language) {
            case Language.Java(var version) when version.startsWith("21") -> true;
            default -> false;
        };
    }

    public String getLanguageString(Language language) {
        return switch (language) {
//            case Language.Java(var version) when version.startsWith("21") -> "You rock because you use Java " + version;
            case Language.Java(var version) -> "Java " + version;
//            case Language.Java java -> "Java " + java.version();
            case Language.Kotlin(var version) -> "Kotlin " + version;
            case Language.Scala(var version) -> "Scala " + version;
//            default -> "Unknown";
        };
    }

    public boolean canAttend(Developer developer, String meetup) {
        return meetup.equals("Coven of Wisdom")
                && developer instanceof Developer(_, Address(_, var country), Language.Java(_))
                && country.equals("Netherlands");
    }
}
