package ch.brix.camunda.connector.util.gson.lang;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Collections;
import java.util.Locale;

/**
 * A message key, fall-back to message key itself if not found
 */
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MessageKey implements StringLike {

    @NotBlank
    @EqualsAndHashCode.Include
    @NonNull
    private final String messageKey;

    /**
     * @param locale locale
     * @return message or message key if not found or locale is null
     */
    public String getMessage(Locale locale) {
        return getMessage(locale, (String[]) null);
    }

    /**
     * @param locale locale
     * @param replacements nullable
     * @return message or message key if not found or locale is null, replacements are still performed if possible
     */
    public String getMessage(Locale locale, String... replacements) {
        return getMessage(locale == null ? null : Collections.singletonList(locale), replacements);
    }

    /**
     * @param preferences locales in order of preference
     * @return message or message key if no locale was found
     */
    public String getMessage(java.util.List<Locale> preferences) {
        return getMessage(preferences, (String[]) null);
    }

    /**
     * @param preferences locales in order of preference
     * @param replacements nullable
     * @return message or message key if no locale was found, replacements are still performed if possible
     */
    public String getMessage(java.util.List<Locale> preferences, String... replacements) {
        if (preferences != null && !preferences.isEmpty()) {
            for (Locale locale : preferences) {
                if (locale == null)
                    continue;
                String msg = ApplicationContextHolder.getApplicationContext().getMessage(messageKey, replacements, null, locale);
                if (msg != null)
                    return msg;
            }
        }
        String msg = messageKey;
        // manually do replacements if message key was not found
        if (replacements != null)
            for (int i = 0; i < replacements.length; i++)
                msg = msg.replace("{" + i + "}", replacements[i]);
        return msg;
    }

    @Override
    public String toString() {
        return messageKey;
    }

}
