package com.innopro.android.sample.data.cache;

import android.content.Context;

import com.innopro.android.sample.data.cache.serializer.MessageJsonSerializer;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.exception.MessageNotFoundException;
import com.innopro.android.sample.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link MessageCache} implementation.
 */
@Singleton
public class MessageCacheImpl implements MessageCache {

  private static final String SETTINGS_FILE_NAME = "com.innopro.android.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "message_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final MessageJsonSerializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link MessageCacheImpl}.
   *
   * @param context A
   * @param messageCacheSerializer {@link MessageJsonSerializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public MessageCacheImpl(Context context, MessageJsonSerializer messageCacheSerializer,
                          FileManager fileManager, ThreadExecutor executor) {
    if (context == null || messageCacheSerializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = messageCacheSerializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<MessageEntity> get(final int messageId) {
    return Observable.create(subscriber -> {
      File messageEntityFile = MessageCacheImpl.this.buildFile(messageId);
      String fileContent = MessageCacheImpl.this.fileManager.readFileContent(messageEntityFile);
      MessageEntity messageEntity = MessageCacheImpl.this.serializer.deserialize(fileContent);

      if (messageEntity != null) {
        subscriber.onNext(messageEntity);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new MessageNotFoundException());
      }
    });
  }

  @Override public void put(MessageEntity messageEntity) {
    if (messageEntity != null) {
      File messageEntitiyFile = this.buildFile(messageEntity.getMessageId());
      if (!isCached(messageEntity.getMessageId())) {
        String jsonString = this.serializer.serialize(messageEntity);
        this.executeAsynchronously(new CacheWriter(this.fileManager, messageEntitiyFile,
            jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(int messageId) {
    File messageEntitiyFile = this.buildFile(messageId);
    return this.fileManager.exists(messageEntitiyFile);
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
   * @param messageId The id message to build the file.
   * @return A valid file.
   */
  private File buildFile(int messageId) {
    StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(messageId);

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
