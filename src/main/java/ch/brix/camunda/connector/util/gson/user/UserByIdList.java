package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class UserByIdList extends ArrayList<UserById> implements DelimitedCollection<UserById> {
    @Override
    public UserById toGenericType(String s) {
        return new UserById(s);
    }
}
