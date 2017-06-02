package com.innopro.android.sample.domain.repository;

import com.innopro.android.sample.domain.Category;

import java.util.List;

import io.reactivex.Observable;


/**
 * Interface that represents a Repository for getting {@link Category} related data.
 */
public interface CategoryRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link Category}.
   */
  Observable<List<Category>> categories();

  /**
   * Get an {@link Observable} which will emit a {@link Category}.
   *
   * @param categoryId The user id used to retrieve user data.
   */
  Observable<Category> category(final int categoryId);
}
