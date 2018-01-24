package com.innopro.android.sample.data.repository.datasource;

import android.content.Context;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.net.RestApi;
import com.innopro.android.sample.data.net.RestApiImpl;
import com.innopro.android.sample.data.repository.datasource.source.CategoryDataStore;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;
import com.innopro.android.sample.data.repository.datasource.source.cloud.CloudCategoryDataStore;
import com.innopro.android.sample.data.repository.datasource.source.disk.DiskCategoryDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class CategoryDataStoreFactory {
    //region Constants
    private static final String TAG = CategoryDataStoreFactory.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private final CategoryCache categoryCache;

    //endregion

    //region Constructors & Initialization
    @Inject
    public CategoryDataStoreFactory(Context context, CategoryCache categoryCache) {
        if (context == null || categoryCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.categoryCache = categoryCache;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Create {@link MessageDataStore} from a message id.
     */
    public CategoryDataStore create(int categoryId) {
        CategoryDataStore categoryDataStore;

        if (!this.categoryCache.isExpired(categoryId) && this.categoryCache.isCached(categoryId)) {
            categoryDataStore = new DiskCategoryDataStore(this.categoryCache);
        } else {
            categoryDataStore = createCloudDataStore();
        }

        return categoryDataStore;
    }

    /**
     * Create {@link MessageDataStore} to retrieve data from the Cloud.
     */
    public CategoryDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);

        return new CloudCategoryDataStore(restApi, this.categoryCache);
    }

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
