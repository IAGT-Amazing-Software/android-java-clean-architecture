package com.innopro.android.sample.data.cache.impl;

import android.content.Context;

import com.innopro.android.sample.data.cache.FileManager;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.data.exception.UserLoggedNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.reactivex.Observable;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserLoggedCacheImpl implements UserLoggedCache {

  private static final String SETTINGS_KEY = "USERLOGGED";

  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final FileManager fileManager;

  private Realm realm;

  /**
   * Constructor of the class {@link UserLoggedCacheImpl}.
   *
   * @param context A
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public UserLoggedCacheImpl(Context context, FileManager fileManager) {
    if (context == null  || fileManager == null ) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.fileManager = fileManager;
    realm= Realm.getDefaultInstance();
  }

  @Override public Observable<UserLoggedEntity> get() {
    return Observable.create(subscriber -> {

      realm= Realm.getDefaultInstance();
      RealmResults<UserLoggedEntity> result = realm.where(UserLoggedEntity.class).findAll();
      result.load();

      for (UserLoggedEntity userLoggedEntity: result) {
        if (userLoggedEntity != null) {
          subscriber.onNext(userLoggedEntity);
          subscriber.onComplete();
        } else {
          subscriber.onError(new UserLoggedNotFoundException());
        }
      }
      realm.close();
    });
  }

  @Override public void put(UserLoggedEntity userLoggedEntity) {
    if (userLoggedEntity != null) {
      if (!isCached()) {
        realm= Realm.getDefaultInstance();
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(userLoggedEntity));
        setLastCacheUpdateTimeMillis();
        realm.close();
      }
    }
  }

  @Override public boolean isCached() {
    realm= Realm.getDefaultInstance();
    boolean res = false;
    UserLoggedEntity userLoggedEntity = realm.where(UserLoggedEntity.class).findFirst();
    if(userLoggedEntity!=null){
      res=true;
    }
    realm.close();
    return res;
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
    realm= Realm.getDefaultInstance();
    RealmResults<UserLoggedEntity> result = realm.where(UserLoggedEntity.class).findAll();
    realm.executeTransaction(bgRealm -> result.deleteAllFromRealm());
    realm.close();
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_KEY,currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_KEY);
  }
}