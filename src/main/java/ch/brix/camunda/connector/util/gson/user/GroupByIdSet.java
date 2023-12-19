package ch.brix.camunda.connector.util.gson.user;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class GroupByIdSet extends HashSet<GroupById> implements DelimitedCollection<GroupById> {
    @Override
    public GroupById toGenericType(String s) {
        return new GroupById(s);
    }
}
