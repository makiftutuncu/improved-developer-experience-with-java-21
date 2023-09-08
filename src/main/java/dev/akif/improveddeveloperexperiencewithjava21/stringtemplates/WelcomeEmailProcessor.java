package dev.akif.improveddeveloperexperiencewithjava21.stringtemplates;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class WelcomeEmailProcessor implements StringTemplate.Processor<EmailTemplate, IllegalArgumentException> {
    public static final WelcomeEmailProcessor WE = new WelcomeEmailProcessor();

    private WelcomeEmailProcessor() {}

    @Override
    public EmailTemplate process(StringTemplate template) throws IllegalArgumentException {
        List<String> f = template.fragments();
        List<Object> v = template.values();

        String userName = parse(f, v, "userName", Object::toString);
        LocalDate registrationDate = parse(f, v, "registrationDate", o -> LocalDate.parse(o.toString()));
        long userId = parse(f, v, "userId", o -> Long.parseLong(o.toString()));

        String unsubscribeLink = STR."https://example.com/unsubscribe/\{userId}";

        return new EmailTemplate(
                """
                Greetings "%user_name%"!
                
                We will spam you starting from "%registration_date%".
                
                You can unsubscribe using <a href="%unsubscribe_link%">this link</a>.
                """,
                Map.of(
                        "user_name", userName,
                        "registration_date", registrationDate,
                        "unsubscribe_link", unsubscribeLink
                )
        );
    }

    private <A> A parse(
            List<String> fragments,
            List<Object> values,
            String key,
            Function<Object, A> transform
    ) {
        try {
            for (int i = 0, size = fragments.size(); i < size; i++) {
                if (fragments.get(i).trim().startsWith(key)) {
                    Object value = values.get(i);
                    return transform.apply(value);
                }
            }
            throw new IllegalArgumentException(key + " is missing!");
        } catch (Exception e) {
            throw new IllegalArgumentException(key + " is invalid!", e);
        }
    }
}
