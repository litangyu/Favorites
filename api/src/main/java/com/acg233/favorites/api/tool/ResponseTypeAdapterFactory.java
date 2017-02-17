package com.acg233.favorites.api.tool;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 用于返回数据离心和分发，由于我们采用了纯净的 API 返回数据结构，
 * 目前暂时无须使用此工具类。
 */
public class ResponseTypeAdapterFactory implements TypeAdapterFactory {

    private static final String TAG = ResponseTypeAdapterFactory.class.getSimpleName();

    private static final String ERROR = "error";
    private static final String DATA = "data";


    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> jsonElementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegateAdapter.write(out, value);
            }


            @Override @SuppressWarnings("PointlessBooleanExpression")
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = jsonElementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has(ERROR) && jsonObject.get(ERROR).getAsBoolean() == false) {
                        if (jsonObject.has(DATA) && isJson(jsonObject, DATA)) {
                            jsonElement = jsonObject.get(DATA);
                        }
                    }
                }
                return delegateAdapter.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }


    private static boolean isJson(JsonObject jsonObject, String memberName) {
        return jsonObject.get(memberName).isJsonArray() ||
            jsonObject.get(memberName).isJsonObject();
    }
}