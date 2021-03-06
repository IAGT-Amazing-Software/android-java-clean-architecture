package com.innopro.android.sample.data.repository.datasource.source.cloud;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.repository.datasource.source.CategoryDataStore;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * {@link MessageDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudCategoryDataStore implements CategoryDataStore {
    //region Constants
    private static final String TAG = CloudCategoryDataStore.class.getSimpleName();
    //endregion

    //region Fields
    private final RestApi restApi;
    private final CategoryCache categoryCache;

    //endregion

    //region Constructors & Initialization
    /**
     * Construct a {@link MessageDataStore} based on connections to the api (Cloud).
     *
     * @param restApi       The {@link RestApi} implementation to use.
     * @param categoryCache A {@link UserCache} to cache data retrieved from the api.
     */
    public CloudCategoryDataStore(RestApi restApi, CategoryCache categoryCache) {
        this.restApi = restApi;
        this.categoryCache = categoryCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    @Override
    public Observable<List<CategoryEntity>> categoryEntityList() {
        return this.restApi.categoryEntityList();
    }


    @Override
    public Observable<CategoryEntity> categoryEntityDetails(final int categoryId) {
        return this.restApi.categoryEntityById(categoryId).doOnNext(categoryCache::put);
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
