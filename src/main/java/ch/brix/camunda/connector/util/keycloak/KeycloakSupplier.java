package ch.brix.camunda.connector.util.keycloak;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Service;

/**
 * Creates a keycloak client if the keycloak.url property is defined. See KeycloakConfig.
 */
@Service
@Getter
@Slf4j
public class KeycloakSupplier {

    private Keycloak keycloak;
    private String realm;
    private RealmResource realmResource;

    public KeycloakSupplier(KeycloakConfig config) {
        if (config.getUrl() == null || config.getUrl().isBlank()) return;
        String serverUrl = config.getUrl();
        if (!serverUrl.endsWith("/"))
            serverUrl += "/";
        serverUrl += "auth";
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(config.getClientId())
                .clientSecret(config.getClientSecret())
                .resteasyClient(ResteasyClientBuilder.newBuilder().build())
                .realm(config.getRealm())
                .build();
        realm = config.getRealm();
        reloadRealmResource();
    }

    public RealmResource reloadRealmResource() {
        keycloak.tokenManager().getAccessToken();
        realmResource = keycloak.realm(realm);
        return realmResource;
    }

}
