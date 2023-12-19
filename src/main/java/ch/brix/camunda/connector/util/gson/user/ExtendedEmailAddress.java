package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Locale;

/**
 * Email address in extended format is either just an email address or
 * [ firstName ';' ] lastName '&lt;' emailAddress '&gt;' [ locale [ ';' sex ] ], e.g.
 * John;Doe&lt;john.doe@email.com&gt;en;m (sex m = male, f = female, rest = unspecified)
 */
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class ExtendedEmailAddress implements StringLike {

    @NonNull
    @NotBlank
    @EqualsAndHashCode.Include
    private final String extendedEmail;

    private final String email;
    private final String firstName;
    private final String lastName;
    private final Locale locale;
    private final Sex sex;

    public ExtendedEmailAddress(String extEmail) {
        extendedEmail = extEmail.trim();
        int start = extendedEmail.indexOf('<');
        if (start < 0) {
            email = extendedEmail;
            firstName = null;
            lastName = null;
            locale = null;
            sex = Sex.UNSPECIFIED;
        } else {
            int end = extendedEmail.indexOf('>');
            if (end < 0) {
                email = extendedEmail.substring(start + 1);
                locale = null;
                sex = Sex.UNSPECIFIED;
            } else {
                email = extendedEmail.substring(start + 1, end - 1);
                if (end + 1 < extendedEmail.length()) {
                    String[] rest = email.substring(end + 1).split(";");
                    if (!rest[0].isBlank())
                        locale = Locale.forLanguageTag(rest[0].trim());
                    else
                        locale = null;
                    if (rest.length > 1)
                        sex = Sex.parse(rest[1]);
                    else
                        sex = Sex.UNSPECIFIED;
                } else {
                    locale = null;
                    sex = null;
                }
            }
            if (start > 0) {
                String[] rest = extendedEmail.substring(0, start - 1).trim().split("\\s*;\\s*");
                if (rest.length == 1) {
                    firstName = null;
                    lastName = rest[0];
                } else {
                    firstName = rest[0];
                    lastName = rest[1];
                }
            } else {
                firstName = null;
                lastName = null;
            }
        }
    }

    public boolean isValid() {
        return EmailValidator.getInstance(true, true).isValid(email);
    }

    @Override
    public String toString() {
        return extendedEmail;
    }
}
