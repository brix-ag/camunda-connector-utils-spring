package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmailAddress implements StringLike {

    @NonNull
    @NotBlank
    @EqualsAndHashCode.Include
    private final String email;

    public boolean isValid() {
        return EmailValidator.getInstance(true, true).isValid(email);
    }

    @Override
    public String toString() {
        return email;
    }

}
