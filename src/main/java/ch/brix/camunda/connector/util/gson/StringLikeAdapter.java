package ch.brix.camunda.connector.util.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@RequiredArgsConstructor
@Getter
public class StringLikeAdapter implements JsonSerializer<StringLike>, JsonDeserializer<StringLike> {

    @Override
    public StringLike deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonNull() || jsonElement.getAsString().isBlank())
            return null;
        try {
            Constructor<?> constructor = TypeToken.get(type).getRawType().getDeclaredConstructor(String.class);
            return (StringLike) constructor.newInstance(jsonElement.getAsString().trim());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonElement serialize(StringLike stringLike, Type type, JsonSerializationContext jsonSerializationContext) {
        return stringLike == null ? JsonNull.INSTANCE : new JsonPrimitive(stringLike.toString());
    }

}
