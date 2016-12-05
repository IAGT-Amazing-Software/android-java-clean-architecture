package com.innopro.android.sample.data.entity.mapper;

import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MessageEntity} (in the data layer) to {@link Message} in the
 * domain layer.
 */
@Singleton
public class CategoryEntityDataMapper {

  @Inject
  public CategoryEntityDataMapper() {}

  /**
   * Transform a {@link MessageEntity} into an {@link User}.
   *
   * @param categoryEntity Object to be transformed.
   * @return {@link Message} if valid {@link MessageEntity} otherwise null.
   */
  public Category transform(CategoryEntity categoryEntity) {
    Category category = null;
    if (categoryEntity != null) {
      category = new Category(categoryEntity.getCategoryId());
      category.setImageUrl(categoryEntity.getImageUrl());
      category.setName(categoryEntity.getName());
    }

    return category;
  }

  /**
   * Transform a List of {@link MessageEntity} into a Collection of {@link Message}.
   *
   * @param categoryEntityCollection Object Collection to be transformed.
   * @return {@link Message} if valid {@link UserEntity} otherwise null.
   */
  public List<Category> transform(Collection<CategoryEntity> categoryEntityCollection) {
    List<Category> categories = new ArrayList<>(20);
    Category category;
    for (CategoryEntity categoryEntity : categoryEntityCollection) {
      category = transform(categoryEntity);
      if (category != null) {
        categories.add(category);
      }
    }

    return categories;
  }
}
