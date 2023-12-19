package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.camunda.connector.util.keycloak.KeycloakSupplier;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.NotFoundException;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

/**
 * Keycloak user represented by his id.
 * If this class is used in a collection it would be better to create a separate type that requests all users at once, but there doesn't seem to be such a request.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class UserById implements StringLike {

    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private final String userId;

    @Override
    public String toString() {
        return userId;
    }

    public UserResource getUserResource() {
        return ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().users().get(userId);
    }

    public UserRepresentation getUser() {
        UserResource userResource = getUserResource();
        return userResource == null ? null : userResource.toRepresentation();
    }

}
