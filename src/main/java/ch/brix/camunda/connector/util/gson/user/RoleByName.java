package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.camunda.connector.util.keycloak.KeycloakSupplier;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.NotFoundException;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RoleResource;

/**
 * Keycloak role represented by its name. Note that the name is treated like an id by the keycloak client (not the same for users and groups).
 * There are member functions on the role to get groups and users.
 * If this class is used in a collection it would be better to create a separate type that requests all roles at once, but there doesn't seem to be such a request.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class RoleByName implements StringLike {

    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private final String roleName;

    public RoleResource getRole() {
        return ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().roles().get(roleName);
    }

    @Override
    public String toString() {
        return roleName;
    }

}
