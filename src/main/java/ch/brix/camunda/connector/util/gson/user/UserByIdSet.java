package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class UserByIdSet extends HashSet<UserById> implements DelimitedCollection<UserById> {
    @Override
    public UserById toGenericType(String s) {
        return new UserById(s);
    }
}
