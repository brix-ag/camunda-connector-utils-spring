package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class UserByEmailList extends ArrayList<UserByEmail> implements DelimitedCollection<UserByEmail> {
    @Override
    public UserByEmail toGenericType(String s) {
        return new UserByEmail(s);
    }
}
