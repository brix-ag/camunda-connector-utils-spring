package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class RoleByNameList extends ArrayList<RoleByName> implements DelimitedCollection<RoleByName> {
    @Override
    public RoleByName toGenericType(String s) {
        return new RoleByName(s);
    }
}
