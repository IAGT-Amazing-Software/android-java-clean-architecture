package com.innopro.android.sample.presentation.utils;


import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class PreferencesUtils {
    SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesUtils(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    /**
     * Storage the pair key-object in preference
     */
    public void setPreferences(String key,
                               Object object) {

        if (key != null) {

            SharedPreferences.Editor ed = mSharedPreferences.edit();
            if (object instanceof String) {
                ed.putString(key, (String) object);
            } else if (object instanceof Boolean) {
                ed.putBoolean(key, (Boolean) object);
            } else if (object instanceof Integer) {
                ed.putInt(key, (Integer) object);
            } else if (object instanceof Long) {
                ed.putLong(key, (Long) object);
            } else if (object instanceof Float) {
                ed.putFloat(key, (Float) object);
            } else {
                String json = null;
                if (object != null) {
                    json = gsonBuilder().create().toJson(object);
                }
                ed.putString(key, json);
            }
            ed.apply();
        }
    }

    // Get the stored object associate with the key string
    @SuppressWarnings("rawtypes")
    public <T> T getPreferences(String key,
                                Class<T> clazz) {

        if (key != null && clazz != null) {

            try {
                if (clazz.equals(String.class)) {
                    return clazz.cast(mSharedPreferences.getString(key, null));
                } else if (clazz.equals(Boolean.class)) {
                    return clazz.cast(mSharedPreferences.getBoolean(key, false));
                } else if (clazz.equals(Integer.class)) {
                    return clazz.cast(mSharedPreferences.getInt(key, 0));
                } else if (clazz.equals(Long.class)) {
                    return clazz.cast(mSharedPreferences.getLong(key, 0));
                } else if (clazz.equals(Float.class)) {
                    return clazz.cast(mSharedPreferences.getFloat(key, 0));
                } else {
                    String json = mSharedPreferences.getString(key, null);
                    return clazz.cast(
                            gsonBuilder().create().fromJson(json, clazz));
                }
            } catch (Exception e) {
                Log.e("getPreferences exceptio", e.getMessage());
            }
        }
        return null;
    }

    /**
     * Remove stored object that it is associated with key param
     */
    public void removePreference(String key) {

        setPreferences(key, null);
    }

    public GsonBuilder gsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
                    throws JsonParseException {

                if (json.getAsString() != null && "".compareTo(json.getAsString()) != 0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(Long.parseLong(json.getAsString()));
                    return calendar.getTime();
                } else {
                    return null;
                }
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
                return new JsonPrimitive(arg0.getTime());
            }
        });

        return builder;
    }
}
