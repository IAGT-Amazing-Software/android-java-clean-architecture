package com.innopro.android.sample.data.repository;

import com.innopro.android.sample.data.entity.mapper.CategoryEntityDataMapper;
import com.innopro.android.sample.data.repository.datasource.CategoryDataStoreFactory;
import com.innopro.android.sample.data.repository.datasource.source.CategoryDataStore;
import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.repository.CategoryRepository;
import com.innopro.android.sample.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link MessageRepository} for retrieving message data.
 */
@Singleton
public class CategoryDataRepository implements CategoryRepository {
    //region Constants
    private static final String TAG = CategoryDataRepository.class.getSimpleName();
    //endregion

    //region Fields
    private final CategoryDataStoreFactory categoryDataStoreFactory;
    private final CategoryEntityDataMapper categoryEntityDataMapper;

    //endregion

    //region Constructors & Initialization
    /**
     * Constructs a {@link CategoryRepository}.
     *
     * @param categoryDataStoreFactory A factory to construct different data source implementations.
     * @param categoryEntityDataMapper {@link CategoryEntityDataMapper}.
     */
    @Inject
    public CategoryDataRepository(CategoryDataStoreFactory categoryDataStoreFactory,
                                  CategoryEntityDataMapper categoryEntityDataMapper) {
        this.categoryDataStoreFactory = categoryDataStoreFactory;
        this.categoryEntityDataMapper = categoryEntityDataMapper;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<Category>> categories() {
        //we always get all messages from the cloud
        final CategoryDataStore categoryDataStore = this.categoryDataStoreFactory.createCloudDataStore();
        return categoryDataStore.categoryEntityList()
                .map(categoriesEntity -> this.categoryEntityDataMapper.transform(categoriesEntity));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Category> category(int categoryId) {
        final CategoryDataStore categoryDataStore = this.categoryDataStoreFactory.create(categoryId);
        return categoryDataStore.categoryEntityDetails(categoryId)
                .map(categoryEntity -> this.categoryEntityDataMapper.transform(categoryEntity));
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
