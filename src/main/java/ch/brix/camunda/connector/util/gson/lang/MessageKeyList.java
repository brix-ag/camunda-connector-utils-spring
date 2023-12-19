package ch.brix.camunda.connector.util.gson.lang;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.ArrayList;

/**
 * comma-separated list of message keys
 */
public class MessageKeyList extends ArrayList<MessageKey> implements DelimitedCollection<MessageKey> {
    @Override
    public MessageKey toGenericType(String s) {
        return new MessageKey(s);
    }
}
