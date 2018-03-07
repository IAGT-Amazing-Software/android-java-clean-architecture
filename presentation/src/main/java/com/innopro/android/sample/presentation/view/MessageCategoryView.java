package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.domain.Category;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link Message}.
 */
public interface MessageCategoryView extends LoadDataView {
  /**
   * Render a category list in the UI.
   *
   * @param categoryCollection The collection of {@link Category} that will be shown.
   */
  void renderCategoryList(Collection<Category> categoryCollection);

  /**
   * View a {@link Category} profile/details.
   *
   * @param category The category that will be shown.
   */
  void viewMessageList(Category category);
}
