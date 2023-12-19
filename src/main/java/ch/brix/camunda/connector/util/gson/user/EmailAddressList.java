package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class EmailAddressList extends ArrayList<EmailAddress> implements DelimitedCollection<EmailAddress> {
    @Override
    public EmailAddress toGenericType(String s) {
        return new EmailAddress(s);
    }
}
