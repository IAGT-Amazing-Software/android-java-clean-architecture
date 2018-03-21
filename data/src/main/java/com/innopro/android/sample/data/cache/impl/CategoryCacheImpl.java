package com.innopro.android.sample.data.cache.impl;

import android.content.Context;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.cache.FileManager;
import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.migrator.Migrator;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.exception.CategoryNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * {@link MessageCache} implementation.
 */
@Singleton
public class CategoryCacheImpl implements CategoryCache {
    //region Constants
    private static final String TAG = CategoryCacheImpl.class.getSimpleName();
    private static final String SETTINGS_KEY = "CATEGORY";

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;
    //endregion

    //region Fields
    private final Context context;
    private final FileManager fileManager;
    private RealmConfiguration realmConfiguration;
    //endregion

    //region Constructors & Initialization
    /**
     * Constructor of the class {@link CategoryCacheImpl}.
     *
     * @param context     A
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public CategoryCacheImpl(Context context, FileManager fileManager) {
        if (context == null || fileManager == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.fileManager = fileManager;
        realmConfiguration=new RealmConfiguration.Builder()
                .name(TAG+".realm")
                .schemaVersion(0)
                .migration(new Migrator())
                .build();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<CategoryEntity> get(final int categoryId) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(realmConfiguration);
            RealmResults<CategoryEntity> result = realm.where(CategoryEntity.class).equalTo("categoryId", categoryId).findAll();
            result.load();

            for (CategoryEntity categoryEntity : result) {

                if (categoryEntity != null) {
                    subscriber.onNext(categoryEntity);
                    subscriber.onComplete();
                } else {
                    subscriber.onError(new CategoryNotFoundException());
                }
            }
            realm.close();
        });
    }

    @Override
    public void put(CategoryEntity categoryEntity) {
        if (categoryEntity != null) {
            if (!isCached(categoryEntity.getCategoryId())) {
                Realm realm = Realm.getInstance(realmConfiguration);
                realm.executeTransaction(bgRealm -> bgRealm.copyToRealmOrUpdate(categoryEntity));
                realm.close();
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(int categoryId) {
        boolean res = false;
        Realm realm = Realm.getInstance(realmConfiguration);
        RealmResults<CategoryEntity> result = realm.where(CategoryEntity.class).equalTo("categoryId", categoryId).findAll();
        if (result.size() > 0) {
            res = true;
        }
        realm.close();
        return res;
    }

    @Override
    public boolean isExpired(int categoryId) {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll(categoryId);
        }

        return expired;
    }

    @Override
    public void evictAll(int categoryId) {
        Realm realm = Realm.getInstance(realmConfiguration);
        RealmResults<CategoryEntity> result = realm.where(CategoryEntity.class).equalTo("categoryId", categoryId).findAll();
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
