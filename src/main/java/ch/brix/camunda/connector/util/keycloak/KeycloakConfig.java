package ch.brix.camunda.connector.util.keycloak;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Data
public class KeycloakConfig {
    private String url;
    private String realm;
    private String clientId;
    private String clientSecret;
}
