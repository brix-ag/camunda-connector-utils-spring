package ch.brix.camunda.connector.util.gson.time;

import ch.brix.camunda.connector.util.gson.StringLike;
import jakarta.validation.constraints.AssertTrue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IsoOffsetDate implements StringLike {
    @EqualsAndHashCode.Include
    @NonNull
    private final String dateString;

    @AssertTrue
    public boolean isValid() {
        try {
            return getAsDate() != null;
        } catch (Exception ignore) {
            return false;
        }
    }

    public TemporalAccessor getAsTemporalAccessor() {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateString);
    }

    public Instant getAsInstant() {
        return Instant.from(getAsTemporalAccessor());
    }

    public Date getAsDate() {
        return Date.from(getAsInstant());
    }

    @Override
    public String toString() {
        return dateString;
    }
}
