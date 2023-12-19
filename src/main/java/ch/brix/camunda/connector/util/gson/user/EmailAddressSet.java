package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class EmailAddressSet extends HashSet<EmailAddress> implements DelimitedCollection<EmailAddress> {
    @Override
    public EmailAddress toGenericType(String s) {
        return new EmailAddress(s);
    }
}
