package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.GetUserDetails;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.view.UserDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class UserDetailsPresenter implements Presenter {
    //region Constants
    private static final String TAG = UserDetailsPresenter.class.getSimpleName();
    //endregion

    //region Fields
    private UserDetailsView viewDetailsView;

    private final UseCase getUserDetailsUseCase;

    //endregion

    //region Constructors & Initialization
    @Inject
    public UserDetailsPresenter(@Named("userDetails") UseCase getUserDetailsUseCase) {
        this.getUserDetailsUseCase = getUserDetailsUseCase;
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
        this.getUserDetailsUseCase.dispose();
        this.viewDetailsView = null;
    }
    //endregion

    //region Methods
    /**
     * Initializes the presenter by start retrieving user details.
     */
    public void initialize(int userId) {
        this.loadUserDetails(userId);
    }

    /**
     * Loads user details.
     */
    private void loadUserDetails(int userId) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserDetails(userId);
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
                errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showUserDetailsInView(User user) {
        this.viewDetailsView.renderUser(user);
    }

    private void getUserDetails(int userId) {
        this.getUserDetailsUseCase.execute(new UserDetailsSubscriber(), GetUserDetails.Params.forGetUserFiles(userId));
    }
    //endregion

    //region Inner and Anonymous Classes
    private final class UserDetailsSubscriber extends DefaultSubscriber<User> {

        @Override
        public void onComplete() {
            UserDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserDetailsPresenter.this.hideViewLoading();
            UserDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(User user) {
            UserDetailsPresenter.this.showUserDetailsInView(user);
        }
    }
    //endregion

    //region Getter & Setter
    public void setView(@NonNull UserDetailsView view) {
        this.viewDetailsView = view;
    }

    //endregion
}
