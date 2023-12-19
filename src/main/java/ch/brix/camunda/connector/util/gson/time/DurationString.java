package ch.brix.camunda.connector.util.gson.time;


import ch.brix.camunda.connector.util.gson.StringLike;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

/**
 * Duration from a string such as PnDTnHnMn.nS
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class DurationString implements StringLike {

    @EqualsAndHashCode.Include
    @NotBlank
    @NonNull
    private final String durationString;

    @AssertTrue
    public boolean isValid() {
        try {
            return getDuration() != null;
        } catch (Exception ignore) {
            return false;
        }
    }

    @Override
    public String toString() {
        return durationString;
    }

    public Duration getDuration() {
        return Duration.parse(durationString);
    }

}
