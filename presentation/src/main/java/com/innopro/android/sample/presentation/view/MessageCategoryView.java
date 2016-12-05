package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.presentation.model.CategoryModel;
import com.innopro.android.sample.presentation.model.MessageModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link MessageModel}.
 */
public interface MessageCategoryView extends LoadDataView {
  /**
   * Render a category list in the UI.
   *
   * @param categoryModelCollection The collection of {@link CategoryModel} that will be shown.
   */
  void renderCategoryList(Collection<CategoryModel> categoryModelCollection);

  /**
   * View a {@link CategoryModel} profile/details.
   *
   * @param categoryModel The category that will be shown.
   */
  void viewMessageList(CategoryModel categoryModel);
}
