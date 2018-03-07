package com.innopro.android.sample.presentation.utils;

import java.util.TreeMap;

public class SharedPreferencesManager {
    private PreferencesUtils preferencesUtils;

    private TreeMap<String, Object> session = new TreeMap<>();

    public SharedPreferencesManager(PreferencesUtils preferencesUtils) {
        this.preferencesUtils = preferencesUtils;
    }

    public <T> T getData(String key, Class<T> clazz) {
        T data = (T) session.get(key);
        if (data == null) {
            data = preferencesUtils.getPreferences(key, clazz);
        }
        return data;
    }

    public void setData(String key, Object data) {
        session.put(key, data);
    }

    public void setDataPersist(String key, Object data) {
        setData(key, data);
        preferencesUtils.setPreferences(key, data);
    }

    public void clearData(String key) {
        session.remove(key);
        preferencesUtils.removePreference(key);
    }

    public Long getLong(String key) {
        return getData(key, Long.class);
    }
    public Boolean getBoolean(String key) {
        return getData(key, Boolean.class);
    }
    public String getString(String key) {
        return getData(key, String.class);
    }
    public Integer getInteger(String key) {
        return getData(key, Integer.class);
    }

}
