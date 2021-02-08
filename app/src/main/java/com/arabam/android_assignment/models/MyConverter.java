package com.arabam.android_assignment.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyConverter {
    @TypeConverter
    public static String toString(ArrayList<Property> value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static ArrayList<Property> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Property>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

}