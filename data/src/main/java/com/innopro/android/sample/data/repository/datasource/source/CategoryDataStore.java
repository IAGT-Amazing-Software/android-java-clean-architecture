package com.innopro.android.sample.data.repository.datasource.source;

import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface CategoryDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link MessageEntity}.
   */
  Observable<List<CategoryEntity>> categoryEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link MessageEntity} by its id.
   *
   * @param categoryId The id to retrieve message data.
   */
  Observable<CategoryEntity> categoryEntityDetails(final int categoryId);
}
