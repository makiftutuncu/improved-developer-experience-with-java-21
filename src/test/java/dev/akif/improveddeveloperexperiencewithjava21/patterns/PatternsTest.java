package dev.akif.improveddeveloperexperiencewithjava21.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatternsTest {
    Patterns patterns = new Patterns();

    @Test
    void isJava21Test() {
        assertFalse(patterns.isJava21(new Language.Scala("3.3")));
        assertFalse(patterns.isJava21(new Language.Java("17")));
        assertTrue(patterns.isJava21(new Language.Java("21")));
    }


    @Test
    void getLanguageStringTest() {
        assertEquals("Java 17", patterns.getLanguageString(new Language.Java("17")));
        assertEquals("Kotlin 1.5.30", patterns.getLanguageString(new Language.Kotlin("1.5.30")));
        assertEquals("Scala 3.3", patterns.getLanguageString(new Language.Scala("3.3")));
    }

    @Test
    void canAttendTest() {
        assertFalse(
                patterns.canAttend(
                        new Developer("Akif", new Address("Amsterdam", "Netherlands"), new Language.Kotlin("1.9")),
                        "Coven of Wisdom"
                )
        );

        assertTrue(
                patterns.canAttend(
                        new Developer("Ayse", new Address("Amsterdam", "Netherlands"), new Language.Java("21")),
                        "Coven of Wisdom"
                )
        );
    }
}
