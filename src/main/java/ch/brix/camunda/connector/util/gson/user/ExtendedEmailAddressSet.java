package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class ExtendedEmailAddressSet extends HashSet<ExtendedEmailAddress> implements DelimitedCollection<ExtendedEmailAddress> {
    @Override
    public ExtendedEmailAddress toGenericType(String s) {
        return new ExtendedEmailAddress(s);
    }
}
