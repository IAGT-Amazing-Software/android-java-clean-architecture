package com.innopro.android.sample.data.cache.impl;

import android.content.Context;
import android.util.Log;

import com.innopro.android.sample.data.cache.FileManager;
import com.innopro.android.sample.data.cache.TokenCache;
import com.innopro.android.sample.data.cache.migrator.Migrator;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.exception.TokenNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * {@link TokenCache} implementation.
 */
@Singleton
public class TokenCacheImpl implements TokenCache {
    //region Constants
    private static final String TAG = TokenCacheImpl.class.getSimpleName();
    private static final String SETTINGS_KEY = "TOKEN";

    private static long EXPIRATION_TIME;
    //endregion

    //region Fields
    private final Context context;
    private final FileManager fileManager;

    private RealmConfiguration realmConfiguration;

    //endregion

    //region Constructors & Initialization
    /**
     * Constructor of the class {@link UserCacheImpl}.
     *
     * @param context     A
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public TokenCacheImpl(Context context, FileManager fileManager) {
        if (context == null || fileManager == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.fileManager = fileManager;
        realmConfiguration = new RealmConfiguration.Builder()
                .name(TAG + ".realm")
                .schemaVersion(0)
                .migration(new Migrator())
                .build();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<TokenEntity> get() {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(realmConfiguration);
            TokenEntity result = realm.where(TokenEntity.class).findFirst();

            Log.i("Token in realms", String.valueOf(realm.where(TokenEntity.class).findAll().size()));

            if (result != null && !isExpired()) {
                subscriber.onNext(result);
                subscriber.onComplete();
            } else {
                evictAll();
                subscriber.onError(new TokenNotFoundException());
            }
            realm.close();
        });
    }

    @Override
    public void put(TokenEntity tokenEntity) {
        if (tokenEntity != null) {
            Realm realm = Realm.getInstance(realmConfiguration);
            realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(tokenEntity));
            Log.i("Users in realm", String.valueOf(realm.where(TokenEntity.class).findAll().size()));
            realm.close();
            setLastCacheUpdateTimeMillis();
        }
    }

    @Override
    public boolean isCached() {
        Realm realm = Realm.getInstance(realmConfiguration);
        TokenEntity result = realm.where(TokenEntity.class).findFirst();
        return result != null;
    }

    @Override
    public boolean isExpired() {
        /*
        boolean res=true;
        realm= Realm.getDefaultInstance();
        TokenEntity tokenEntity = realm.where(TokenEntity.class).findFirst();
        if(tokenEntity!=null && tokenEntity.getExpiresIn()+getLastCacheUpdateTimeMillis()>System.currentTimeMillis()){
            res=false;
        }
        realm.close();
        return res;
         */
        Realm realm = Realm.getInstance(realmConfiguration);
        TokenEntity tokenEntity = realm.where(TokenEntity.class).findFirst();
        realm.close();
        return tokenEntity == null || tokenEntity.getExpiresIn() * 1000 + getLastCacheUpdateTimeMillis() <= System.currentTimeMillis();
    }

    @Override
    public void evictAll() {
        Realm realm = Realm.getInstance(realmConfiguration);
        RealmResults<TokenEntity> result = realm.where(TokenEntity.class).findAll();
        realm.executeTransaction(bgRealm -> result.deleteAllFromRealm());
        realm.close();
    }

    //endregion

    //region Methods

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
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
