package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.camunda.connector.util.keycloak.KeycloakSupplier;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.GroupResource;

/**
 * Keycloak group represented by its id.
 * There are member functions on the group to get its users and groups.
 * If this class is used in a collection it would be better to create a separate type that requests all groups at once, but there doesn't seem to be such a request.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class GroupById implements StringLike {

    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private final String groupId;

    public GroupResource getGroup() {
        return ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().groups().group(groupId);
    }

    @Override
    public String toString() {
        return groupId;
    }

}
