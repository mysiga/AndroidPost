package com.mysiga.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Json 序列化工具类
 */
public class JsonElementUtils {
    public static String getAsString(JsonObject jsonObject, String memberName) {
        String jsonString = null;
        //防止JsonElement 为null时，抛出 UnsupportedOperationException 异常
        JsonElement jsonElement = jsonObject.get(memberName);
        if (jsonElement.isJsonPrimitive() && !jsonElement.isJsonArray()) {
            jsonString = jsonObject.get(memberName).getAsString();
        }
        return jsonString;
    }

    public static boolean getAsBoolean(JsonObject jsonObject, String memberName) {
        boolean jsonBoolean = false;
        //防止JsonElement 为null时，抛出 UnsupportedOperationException 异常
        JsonElement jsonElement = jsonObject.get(memberName);
        if (jsonElement.isJsonPrimitive() && !jsonElement.isJsonArray()) {
            jsonBoolean = jsonObject.get(memberName).getAsBoolean();
        }
        return jsonBoolean;
    }

    public static int getAsInt(JsonObject jsonObject, String memberName) {
        int jsonInt = 0;
        //防止JsonElement 为null时，抛出 UnsupportedOperationException 异常
        JsonElement jsonElement = jsonObject.get(memberName);
        if (jsonElement.isJsonPrimitive() && !jsonElement.isJsonArray()) {
            jsonInt = jsonObject.get(memberName).getAsInt();
        }
        return jsonInt;
    }
}
