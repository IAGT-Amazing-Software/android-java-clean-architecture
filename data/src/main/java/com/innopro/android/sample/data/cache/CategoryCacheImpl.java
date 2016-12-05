package com.innopro.android.sample.data.cache;

import android.content.Context;

import com.innopro.android.sample.data.cache.serializer.CategoryJsonSerializer;
import com.innopro.android.sample.data.cache.serializer.MessageJsonSerializer;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.exception.CategoryNotFoundException;
import com.innopro.android.sample.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link MessageCache} implementation.
 */
@Singleton
public class CategoryCacheImpl implements CategoryCache {

  private static final String SETTINGS_FILE_NAME = "com.innopro.android.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "category_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final CategoryJsonSerializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link CategoryCacheImpl}.
   *
   * @param context A
   * @param categoryCacheSerializer {@link MessageJsonSerializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public CategoryCacheImpl(Context context, CategoryJsonSerializer categoryCacheSerializer,
                           FileManager fileManager, ThreadExecutor executor) {
    if (context == null || categoryCacheSerializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = categoryCacheSerializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<CategoryEntity> get(final int categoryId) {
    return Observable.create(subscriber -> {
      File categoryEntityFile = CategoryCacheImpl.this.buildFile(categoryId);
      String fileContent = CategoryCacheImpl.this.fileManager.readFileContent(categoryEntityFile);
      CategoryEntity categoryEntity = CategoryCacheImpl.this.serializer.deserialize(fileContent);

      if (categoryEntity != null) {
        subscriber.onNext(categoryEntity);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new CategoryNotFoundException());
      }
    });
  }

  @Override public void put(CategoryEntity categoryEntity) {
    if (categoryEntity != null) {
      File categoryEntitiyFile = this.buildFile(categoryEntity.getCategoryId());
      if (!isCached(categoryEntity.getCategoryId())) {
        String jsonString = this.serializer.serialize(categoryEntity);
        this.executeAsynchronously(new CacheWriter(this.fileManager, categoryEntitiyFile,
            jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(int categoryId) {
    File categoryEntitiyFile = this.buildFile(categoryId);
    return this.fileManager.exists(categoryEntitiyFile);
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
   * @param categoryId The id message to build the file.
   * @return A valid file.
   */
  private File buildFile(int categoryId) {
    StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(categoryId);

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
