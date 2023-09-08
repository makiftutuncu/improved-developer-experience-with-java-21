package dev.akif.improveddeveloperexperiencewithjava21.stringtemplates;

import static dev.akif.improveddeveloperexperiencewithjava21.stringtemplates.WelcomeEmailProcessor.WE;
import static java.lang.StringTemplate.RAW;

public class StringTemplates {
    public String greet(String event) {
        // java.lang.StringTemplate.STR is imported automatically.
        return STR."Hello and welcome to \{event}!";
    }

    public String defaultInterpolation(
            String name,
            int age,
            boolean havingFun,
            Object object
    ) {
        return STR."""
                Hello, my name is \{name}.
                I am \{age} years old.
                I am \{havingFun ? "having fun" : "not having fun"}.
                I was given \{object}.""";
    }

    public String rawInterpolation(
            String name,
            int age,
            boolean havingFun,
            Object object
    ) {
        StringTemplate rawTemplate =
                RAW."""
                Hello, my name is \{name}.
                I am \{age} years old.
                I am \{havingFun ? "having fun" : "not having fun"}.
                I was given \{object}.""";

        System.out.println(STR."Fragments: \{rawTemplate.fragments()}");
        System.out.println(STR."Values: \{rawTemplate.values()}");

        return rawTemplate.interpolate();
    }

    public String customProcessingInterpolation(
            String name,
            int age,
            boolean havingFun,
            Object object
    ) throws Throwable {
        StringTemplate.Processor<String, Throwable> shoutingProcessor =
                template -> {
                    StringBuilder sb = new StringBuilder();
                    var values = template.values().iterator();
                    for (var fragment : template.fragments()) {
                        sb.append(fragment);
                        if (values.hasNext()) {
                            sb.append(values.next().toString().toUpperCase());
                        }
                    }
                    return sb.toString();
                };

        return RAW."""
                Hello, my name is \{name}.
                I am \{age} years old.
                I am \{havingFun ? "having fun" : "not having fun"}.
                I was given \{object}.""".process(shoutingProcessor);
    }

    public EmailTemplate prepareWelcomeEmail(long id, String user, String date) throws IllegalArgumentException {
        return WE."""
                userName: \{user}
                registrationDate: \{date}
                userId: \{id}""";
    }
}
