package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class UserByUsernameList extends ArrayList<UserByUsername> implements DelimitedCollection<UserByUsername> {
    @Override
    public UserByUsername toGenericType(String s) {
        return new UserByUsername(s);
    }
}
