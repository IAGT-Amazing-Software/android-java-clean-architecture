package com.innopro.android.sample.presentation.mapper;

import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.model.CategoryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Category} (in the domain layer) to {@link CategoryModel} in the
 * presentation layer.
 */
@PerActivity
public class CategoryModelDataMapper {
    //region Constants
    private static final String TAG = CategoryModelDataMapper.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Inject
    public CategoryModelDataMapper() {
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /** Transform a {@link Message} into an {@link CategoryModel}.
            *
            * @param category Object to be transformed.
     * @return {@link CategoryModel}.
            */
    public CategoryModel transform(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        CategoryModel categoryModel = new CategoryModel(category.getCategoryId());
        categoryModel.setImageUrl(category.getImageUrl());
        categoryModel.setName(category.getName());

        return categoryModel;
    }

    /**
     * Transform a Collection of {@link Category} into a Collection of {@link CategoryModel}.
     *
     * @param categoriesCollection Objects to be transformed.
     * @return List of {@link CategoryModel}.
     */
    public Collection<CategoryModel> transform(Collection<Category> categoriesCollection) {
        Collection<CategoryModel> categoriesModelsCollection;

        if (categoriesCollection != null && !categoriesCollection.isEmpty()) {
            categoriesModelsCollection = new ArrayList<>();
            for (Category category : categoriesCollection) {
                categoriesModelsCollection.add(transform(category));
            }
        } else {
            categoriesModelsCollection = Collections.emptyList();
        }

        return categoriesModelsCollection;
    }

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
