package dev.akif.improveddeveloperexperiencewithjava21.stringtemplates;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTemplatesTest {
    StringTemplates stringTemplates = new StringTemplates();

    @Test
    void greetTest() {
        String expected = "Hello and welcome to Coven of Wisdom!";
        String actual = stringTemplates.greet("Coven of Wisdom");
        assertEquals(expected, actual);
    }

    @Test
    void defaultInterpolationTest() {
        String expected =
                "Hello, my name is Akif."
                    + "\nI am 32 years old."
                    + "\nI am having fun."
                    + "\nI was given Pizza[type=Margherita, slices=8].";
        String actual = stringTemplates.defaultInterpolation(
                "Akif",
                32,
                true,
                new Pizza("Margherita", 8)
        );
        assertEquals(expected, actual);
    }

    @Test
    void rawInterpolationTest() {
        String expected =
                "Hello, my name is Akif."
                    + "\nI am 32 years old."
                    + "\nI am having fun."
                    + "\nI was given Pizza[type=Margherita, slices=8].";
        String actual = stringTemplates.rawInterpolation(
                "Akif",
                32,
                true,
                new Pizza("Margherita", 8)
        );
        assertEquals(expected, actual);
    }

    @Test
    void customProcessingInterpolationTest() throws Throwable {
        String expected =
                "Hello, my name is AKIF."
                    + "\nI am 32 years old."
                    + "\nI am HAVING FUN."
                    + "\nI was given PIZZA[TYPE=MARGHERITA, SLICES=8].";
        String actual = stringTemplates.customProcessingInterpolation(
                "Akif",
                32,
                true,
                new Pizza("Margherita", 8)
        );
        assertEquals(expected, actual);
    }

    @Test
    void prepareWelcomeEmailTest() {
        var expected = new EmailTemplate(
                """
                Greetings "%user_name%"!
                
                We will spam you starting from "%registration_date%".
                
                You can unsubscribe using <a href="%unsubscribe_link%">this link</a>.
                """,
                Map.of(
                        "user_name", "akif",
                        "registration_date", LocalDate.of(2023, 9, 14),
                        "unsubscribe_link", "https://example.com/unsubscribe/123"
                )
        );
        var actual = stringTemplates.prepareWelcomeEmail(123, "akif", "2023-09-14");
        assertEquals(expected, actual);
    }
}
