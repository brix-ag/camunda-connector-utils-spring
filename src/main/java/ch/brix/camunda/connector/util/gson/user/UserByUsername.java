package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.camunda.connector.util.keycloak.KeycloakSupplier;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

/**
 * Keycloak user represented by his email.
 * If this class is used in a collection it would be better to create a separate type that requests all users at once, but there doesn't seem to be such a request.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class UserByUsername implements StringLike {

    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private final String username;

    @Override
    public String toString() {
        return username;
    }

    public UserRepresentation getUser() {
        List<UserRepresentation> userRepresentations = ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().users().searchByUsername(username, true);
        return userRepresentations.isEmpty() ? null : userRepresentations.get(0);
    }

}
