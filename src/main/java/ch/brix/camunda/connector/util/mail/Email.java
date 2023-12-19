package ch.brix.camunda.connector.util.mail;

import lombok.*;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class Email {
    private String template;
    private String subject;
    private Locale locale;
    private String from;
    private Set<String> to;
    private Set<String> cc;
    private Set<String> bcc;
    private Map<String, Object> variables;
}
