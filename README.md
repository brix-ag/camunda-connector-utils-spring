# Camunda 8 Connector Util for Spring Boot

This is an extension of the [Camunda Connector Util](https://github.com/brix-ag/camunda-connector-utils) which is independent of spring. The following features are introduced:

- Email service (thymeleaf)
- Keycloak supplier
- Many useful classes for the deserialization of requests

## Email

### Configuration Example (yaml)

```yaml
spring:
  thymeleaf.cache: false
  mail:
    default-encoding: UTF-8
    host: <host>
    username: <username>
    password: <password>
    port: 587
    properties.mail.smtp:
      auth: true
      starttls.enable: true
    protocol: smtp
    test-connection: false
```

### Email Service

If the mail is configured correctly the `ch.brix.camunda.connector.util.mail.EmailService` can be autowired. It has one method `sendMail(Email email)` to send mails (thymeleaf), details below.

### Email Object

- template (String, required): the relative path to template without file extension
- subject (String, required): the subject
- locale (Locale): the locale to use for the template
- from (String, required): the sender email address
- to (Set&lt;String&gt;): the recipient email addresses
- cc (Set&lt;String&gt;): the cc email addresses
- bcc (Set&lt;String&gt;): the bcc email addresses
- variables (Map&lt;String, Object&gt;): the variables for the template

## Keycloak

### Configuration (yaml)

The default configuration takes the values from the operate environment variables (can be overwritten):

```yaml
keycloak:
  url: ${CAMUNDA_OPERATE_CLIENT_KEYCLOAK-URL}
  realm: ${CAMUNDA_OPERATE_CLIENT_KEYCLOAK-REALM}
  client-id: ${CAMUNDA_OPERATE_CLIENT_CLIENT-ID}
  client-secret: ${CAMUNDA_OPERATE_CLIENT_CLIENT-SECRET}
```

### Keycloak Supplier

The `KeycloakSupplier` connects to Keycloak and provides resources (`Keycloak` and `RealmResource`) to interact with Keycloak.

## Utility Classes for Deserialization

All utility classes except the enumerations also have a list and a set version to deserialize strings with comma-separated values.

The `StringLikeAdapter` has to be registered as hierarchy adapter for `StringLike` when building Gson for the deserialization to work.

### Language Related

#### MessageKey

Provides methods to get the message for a locale or preference list of locales (first found is returned) including optional replacements. Falls back on the message key itself and if possible the replacements are still performed.

### Time Related

#### IsoOffsetDate

Provides methods to get the ISO offset date string as `java.util.Date`, `TemporalAccessor` or `Instant`.

#### DurationString

Used to deserialize `java.time.Duration` strings.

### User Related

#### EmailAddress

Provides email address validation.

#### ExtendedEmailAddress

Normal email address or email address in extended syntax (`[firstName ';'] lastName '<' emailAddress '>' [locale [';' sex]]`, sex is `m` or `f` everything else is unspecified, no commas allowed if one of the collection types is used). This can be used if user data can't be loaded but we would still like to send a mail in the correct language with proper greeting.

#### UserById

Keycloak user by user id.

#### UserByUsername

Keycloak user by username.

#### UserByEmail

Keycloak user by email.

#### GroupById

Keycloak group by id.

#### GroupByName

Keycloak group by name.

#### RoleByName

Keycloak role by name.

#### Sex

Enum used by `ExtendedEmail`, values: UNSPECIFIED, MALE, FEMALE.

### Other

#### YesNo

Enum with YES and NO value (and labels "Yes" and "No" if used in choice property).
