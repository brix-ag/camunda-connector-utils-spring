package ch.brix.camunda.connector.util.gson.time;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

public class IsoOffsetDateSet extends HashSet<IsoOffsetDate> implements DelimitedCollection<IsoOffsetDate> {
    @Override
    public IsoOffsetDate toGenericType(String s) {
        return new IsoOffsetDate(s);
    }
}
