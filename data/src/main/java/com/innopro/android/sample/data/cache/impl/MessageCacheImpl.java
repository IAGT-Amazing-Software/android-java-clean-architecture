package com.innopro.android.sample.data.cache.impl;

import android.content.Context;

import com.innopro.android.sample.data.cache.FileManager;
import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.exception.MessageNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.reactivex.Observable;

/**
 * {@link MessageCache} implementation.
 */
@Singleton
public class MessageCacheImpl implements MessageCache {

  private static final String SETTINGS_KEY = "MESSAGE";

  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final FileManager fileManager;

  private Realm realm;

  /**
   * Constructor of the class {@link MessageCacheImpl}.
   *
   * @param context A
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public MessageCacheImpl(Context context, FileManager fileManager) {
    if (context == null || fileManager == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.fileManager = fileManager;
    realm= Realm.getDefaultInstance();
  }

  @Override public Observable<MessageEntity> get(final int messageId) {

    return Observable.create(subscriber -> {
      realm= Realm.getDefaultInstance();
      RealmResults<MessageEntity> result = realm.where(MessageEntity.class).equalTo("messageId",messageId).findAll();
      result.load();

      for (MessageEntity m: result) {
        if (m!=null) {
          subscriber.onNext(m);
          subscriber.onComplete();
        } else {
          subscriber.onError(new MessageNotFoundException());
        }
      }
      realm.close();
    });
  }

  @Override public void put(MessageEntity messageEntity) {
    if (messageEntity != null) {
      if (!isCached(messageEntity.getMessageId())) {
        realm= Realm.getDefaultInstance();
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(messageEntity));

        setLastCacheUpdateTimeMillis();
        realm.close();
      }
    }
  }

  @Override public boolean isCached(int messageId) {
    boolean res = false;
    realm= Realm.getDefaultInstance();
    RealmResults<MessageEntity> result = realm.where(MessageEntity.class).equalTo("messageId",messageId).findAll();
    if(result.size()>0){
      res=true;
    }
    realm.close();
    return res;
  }

  @Override public boolean isExpired(int messageId) {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll(messageId);
    }

    return expired;
  }

  @Override public void evictAll(int messageId) {
    realm= Realm.getDefaultInstance();
    RealmResults<MessageEntity> result = realm.where(MessageEntity.class).equalTo("messageId",messageId).findAll();
    realm.executeTransaction(bgRealm -> result.deleteAllFromRealm());
    realm.close();
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_KEY, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_KEY);
  }

}
