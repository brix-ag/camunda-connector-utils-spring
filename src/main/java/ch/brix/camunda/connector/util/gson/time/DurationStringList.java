package ch.brix.camunda.connector.util.gson.time;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

public class DurationStringList extends ArrayList<DurationString> implements DelimitedCollection<DurationString> {
    @Override
    public DurationString toGenericType(String s) {
        return new DurationString(s);
    }
}
