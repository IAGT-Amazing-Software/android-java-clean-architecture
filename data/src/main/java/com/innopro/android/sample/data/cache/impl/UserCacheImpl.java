package com.innopro.android.sample.data.cache.impl;

import android.content.Context;
import android.util.Log;

import com.innopro.android.sample.data.cache.FileManager;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.exception.UserNotFoundException;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.reactivex.Observable;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserCacheImpl implements UserCache {

  private static final String SETTINGS_KEY = "USER";

  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final FileManager fileManager;

  private Realm realm;

  /**
   * Constructor of the class {@link UserCacheImpl}.
   *
   * @param context A
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public UserCacheImpl(Context context, FileManager fileManager) {
    if (context == null || fileManager == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.fileManager = fileManager;
    realm= Realm.getDefaultInstance();
  }

  @Override public Observable<UserEntity> get(final int userId) {
    return Observable.create(subscriber -> {
      realm= Realm.getDefaultInstance();
      RealmResults<UserEntity> result = realm.where(UserEntity.class).equalTo("userId",userId).findAll();
      result.load();

      Log.i("REAL User in realms", String.valueOf(realm.where(UserEntity.class).findAll().size()));

      for (UserEntity userEntity: result) {
        if (userEntity != null) {
          subscriber.onNext(userEntity);
          subscriber.onComplete();
        } else {
          subscriber.onError(new UserNotFoundException());
        }
      }
      realm.close();
    });
  }

  @Override public void put(UserEntity userEntity) {
    if (userEntity != null) {
      if (!isCached(userEntity.getUserId())) {
        realm= Realm.getDefaultInstance();
        realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(userEntity));
        Log.i("Users in realm", String.valueOf(realm.where(UserEntity.class).findAll().size()));
        realm.close();
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(int userId) {
    boolean res = false;
    realm= Realm.getDefaultInstance();
    RealmResults<UserEntity> result = realm.where(UserEntity.class).equalTo("userId",userId).findAll();
    if(result.size()>0){
      res=true;
    }
    realm.close();
    return res;
  }

  @Override public boolean isExpired(int userId) {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll(userId);
    }
    return expired;
  }

  @Override public void evictAll(int userId) {
    realm= Realm.getDefaultInstance();
    RealmResults<UserEntity> result = realm.where(UserEntity.class).equalTo("userId",userId).findAll();
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
