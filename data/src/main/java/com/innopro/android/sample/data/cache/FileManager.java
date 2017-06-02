package com.innopro.android.sample.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Helper class to do operations on regular files/directories.
 */
@Singleton
public class FileManager {

  private static final String SETTINGS_FILE_NAME = "com.iagt.android.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  @Inject
  public FileManager() {}


  /**
   * Write a value to a user preferences file.
   *
   * @param context {@link Context} to retrieve android user preferences.
   * @param preferenceFileName A file name reprensenting where data will be written to.
   * @param value A long representing the value to be inserted.
   */
  public void writeToPreferences(Context context, String preferenceFileName,
      long value) {

    SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME+preferenceFileName,
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putLong(SETTINGS_KEY_LAST_CACHE_UPDATE, value);
    editor.apply();
  }

  /**
   * Get a value from a user preferences file.
   *
   * @param context {@link Context} to retrieve android user preferences.
   * @param preferenceFileName A file name representing where data will be get from.
   * @return A long representing the value retrieved from the preferences file.
   */
  public long getFromPreferences(Context context, String preferenceFileName) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME+preferenceFileName,
        Context.MODE_PRIVATE);
    return sharedPreferences.getLong(SETTINGS_KEY_LAST_CACHE_UPDATE, 0);
  }
}
