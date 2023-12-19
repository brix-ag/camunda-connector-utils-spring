package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class ExtendedEmailAddressList extends ArrayList<ExtendedEmailAddress> implements DelimitedCollection<ExtendedEmailAddress> {
    @Override
    public ExtendedEmailAddress toGenericType(String s) {
        return new ExtendedEmailAddress(s);
    }
}
