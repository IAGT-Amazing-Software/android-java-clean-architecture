package com.innopro.android.sample.data.cache;

import android.content.Context;

import com.innopro.android.sample.data.cache.serializer.UserLoggedJsonSerializer;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.data.exception.UserLoggedNotFoundException;
import com.innopro.android.sample.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserLoggedCacheImpl implements UserLoggedCache {

  private static final String SETTINGS_FILE_NAME = "com.innopro.android.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "userlogged_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final UserLoggedJsonSerializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link UserLoggedCacheImpl}.
   *
   * @param context A
   * @param userLoggedCacheSerializer {@link UserLoggedJsonSerializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public UserLoggedCacheImpl(Context context, UserLoggedJsonSerializer userLoggedCacheSerializer,
                             FileManager fileManager, ThreadExecutor executor) {
    if (context == null || userLoggedCacheSerializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = userLoggedCacheSerializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<UserLoggedEntity> get() {
    return Observable.create(subscriber -> {
      File userEntityFile = UserLoggedCacheImpl.this.buildFile();
      String fileContent = UserLoggedCacheImpl.this.fileManager.readFileContent(userEntityFile);
      UserLoggedEntity userLoggedEntity = UserLoggedCacheImpl.this.serializer.deserialize(fileContent);

      if (userLoggedEntity != null) {
        subscriber.onNext(userLoggedEntity);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new UserLoggedNotFoundException());
      }
    });
  }

  @Override public void put(UserLoggedEntity userLoggedEntity) {
    if (userLoggedEntity != null) {
      File userLoggedEntitiyFile = this.buildFile();
      if (!isCached()) {
        String jsonString = this.serializer.serialize(userLoggedEntity);
        this.executeAsynchronously(new CacheWriter(this.fileManager, userLoggedEntitiyFile,
            jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached() {
    File userLoggedEntitiyFile = this.buildFile();
    return this.fileManager.exists(userLoggedEntitiyFile);
  }

  @Override public boolean isExpired() {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll();
    }

    return expired;
  }

  @Override public void evictAll() {
    this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
  }

  /**
   * Build a file, used to be inserted in the disk cache.
   *
   * @return A valid file.
   */
  private File buildFile() {
    StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);

    return new File(fileNameBuilder.toString());
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE);
  }

  /**
   * Executes a {@link Runnable} in another Thread.
   *
   * @param runnable {@link Runnable} to execute
   */
  private void executeAsynchronously(Runnable runnable) {
    this.threadExecutor.execute(runnable);
  }

  /**
   * {@link Runnable} class for writing to disk.
   */
  private static class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
      this.fileManager = fileManager;
      this.fileToWrite = fileToWrite;
      this.fileContent = fileContent;
    }

    @Override public void run() {
      this.fileManager.writeToFile(fileToWrite, fileContent);
    }
  }

  /**
   * {@link Runnable} class for evicting all the cached files
   */
  private static class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    CacheEvictor(FileManager fileManager, File cacheDir) {
      this.fileManager = fileManager;
      this.cacheDir = cacheDir;
    }

    @Override public void run() {
      this.fileManager.clearDirectory(this.cacheDir);
    }
  }
}
