package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class UserByUsernameSet extends HashSet<UserByUsername> implements DelimitedCollection<UserByUsername> {
    @Override
    public UserByUsername toGenericType(String s) {
        return new UserByUsername(s);
    }
}
