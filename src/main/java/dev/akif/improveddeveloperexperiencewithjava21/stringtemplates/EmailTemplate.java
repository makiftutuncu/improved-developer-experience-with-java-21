package dev.akif.improveddeveloperexperiencewithjava21.stringtemplates;

import java.util.Map;

public record EmailTemplate(String template, Map<String, Object> variables) {}
