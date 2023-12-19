package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class GroupByNameList extends ArrayList<GroupByName> implements DelimitedCollection<GroupByName> {
    @Override
    public GroupByName toGenericType(String s) {
        return new GroupByName(s);
    }
}
