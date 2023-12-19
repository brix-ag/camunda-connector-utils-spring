package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class UserByEmailSet extends HashSet<UserByEmail> implements DelimitedCollection<UserByEmail> {
    @Override
    public UserByEmail toGenericType(String s) {
        return new UserByEmail(s);
    }
}
