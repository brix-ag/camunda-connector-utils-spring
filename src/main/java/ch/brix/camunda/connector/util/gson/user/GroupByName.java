package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.StringLike;
import ch.brix.camunda.connector.util.keycloak.KeycloakSupplier;
import ch.brix.spring.ApplicationContextHolder;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.representations.idm.GroupRepresentation;

import java.util.List;

/**
 * Keycloak group represented by its name.
 * There are member functions on the group to get its users and groups.
 * If this class is used in a collection it would be better to create a separate type that requests all groups at once, but there doesn't seem to be such a request.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class GroupByName implements StringLike {

    @NotBlank
    @NonNull
    @EqualsAndHashCode.Include
    private final String name;

    public GroupResource getGroup() {
        List<GroupRepresentation> groups = ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().groups().groups(name, true, 0, 1, true);
        if (groups == null || groups.isEmpty())
            return null;
        return ApplicationContextHolder.getBean(KeycloakSupplier.class).getRealmResource().groups().group(groups.get(0).getId());
    }

    @Override
    public String toString() {
        return name;
    }

}
