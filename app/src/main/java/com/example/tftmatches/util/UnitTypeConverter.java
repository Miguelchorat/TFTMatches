package com.example.tftmatches.util;

import androidx.room.TypeConverter;

import com.example.tftmatches.model.Unit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UnitTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Unit> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Unit>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Unit> someObjects) {
        return gson.toJson(someObjects);
    }
}
