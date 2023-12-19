package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class GroupByNameSet extends HashSet<GroupByName> implements DelimitedCollection<GroupByName> {
    @Override
    public GroupByName toGenericType(String s) {
        return new GroupByName(s);
    }
}
