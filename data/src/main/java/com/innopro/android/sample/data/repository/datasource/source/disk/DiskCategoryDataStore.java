package com.innopro.android.sample.data.repository.datasource.source.disk;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.repository.datasource.source.CategoryDataStore;
import com.innopro.android.sample.data.repository.datasource.source.UserDataStore;

import java.util.List;

import rx.Observable;

/**
 * {@link CategoryDataStore} implementation based on file system data store.
 */
public class DiskCategoryDataStore implements CategoryDataStore {

  private final CategoryCache categoryCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param categoryCache A {@link CategoryCache} to cache data retrieved from the api.
   */
  public DiskCategoryDataStore(CategoryCache categoryCache) {
    this.categoryCache = categoryCache;
  }

  @Override public Observable<List<CategoryEntity>> categoryEntityList() {
    //TODO: implement simple cache for storing/retrieving collections of messages.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<CategoryEntity> categoryEntityDetails(final int categoryId) {
     return this.categoryCache.get(categoryId);
  }
}
