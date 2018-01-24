package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.mapper.CategoryModelDataMapper;
import com.innopro.android.sample.presentation.model.CategoryModel;
import com.innopro.android.sample.presentation.view.MessageCategoryView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class MessageCategoryPresenter implements Presenter {
    //region Constants
    private static final String TAG = MessageCategoryPresenter.class.getSimpleName();
    //endregion

    //region Fields
    private MessageCategoryView viewListView;

    private final UseCase getMessageCategoryUseCase;
    private final CategoryModelDataMapper categoryModelDataMapper;

    //endregion

    //region Constructors & Initialization
    @Inject
    public MessageCategoryPresenter(@Named("categoryList") UseCase getMessageCategoryUseCase,
                                    CategoryModelDataMapper categoryModelDataMapper) {
        this.getMessageCategoryUseCase = getMessageCategoryUseCase;
        this.categoryModelDataMapper = categoryModelDataMapper;
    }
    /**
     * Initializes the presenter by start retrieving the message list.
     */
    public void initialize() {
        this.loadMessageCategories();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getMessageCategoryUseCase.dispose();
        this.viewListView = null;
    }
    //endregion

    //region Methods
    /**
     * Loads all message.
     */
    private void loadMessageCategories() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getMessageCategories();
    }

    public void onCategoryClicked(CategoryModel categoryModel) {
        this.viewListView.viewMessageList(categoryModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showCategoryCollectionInView(Collection<Category> categoriesCollection) {
        final Collection<CategoryModel> categoryModelsCollection =
                this.categoryModelDataMapper.transform(categoriesCollection);
        this.viewListView.renderCategoryList(categoryModelsCollection);
    }

    private void getMessageCategories() {
        this.getMessageCategoryUseCase.execute(new CategoryListSubscriber(), null);
    }
    //endregion

    //region Inner and Anonymous Classes
    private final class CategoryListSubscriber extends DefaultSubscriber<List<Category>> {

        @Override
        public void onComplete() {
            MessageCategoryPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MessageCategoryPresenter.this.hideViewLoading();
            MessageCategoryPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MessageCategoryPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Category> categories) {
            MessageCategoryPresenter.this.showCategoryCollectionInView(categories);
        }
    }
    //endregion

    //region Getter & Setter

    public void setView(@NonNull MessageCategoryView view) {
        this.viewListView = view;
    }
    //endregion


}
