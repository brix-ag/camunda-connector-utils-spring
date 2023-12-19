package ch.brix.camunda.connector.util.gson.lang;

import ch.brix.camunda.connector.util.gson.delimitedCollections.DelimitedCollection;

import java.util.HashSet;

/**
 * comma-separated set of message keys
 */
public class MessageKeySet extends HashSet<MessageKey> implements DelimitedCollection<MessageKey> {
    @Override
    public MessageKey toGenericType(String s) {
        return new MessageKey(s);
    }
}
