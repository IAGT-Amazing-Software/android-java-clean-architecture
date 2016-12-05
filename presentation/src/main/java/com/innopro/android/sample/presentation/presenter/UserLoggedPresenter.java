package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.UserLogged;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.mapper.UserLoggedModelDataMapper;
import com.innopro.android.sample.presentation.model.UserLoggedModel;
import com.innopro.android.sample.presentation.view.UserLoggedView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserLoggedPresenter implements Presenter {

  private UserLoggedView viewDetailsView;

  private final UseCase getUserLoggedUseCase;
  private final UserLoggedModelDataMapper userLoggedModelDataMapper;

  @Inject
  public UserLoggedPresenter(@Named("userLogged") UseCase getUserLoggedUseCase,
                             UserLoggedModelDataMapper userLoggedModelDataMapper) {
    this.getUserLoggedUseCase = getUserLoggedUseCase;
    this.userLoggedModelDataMapper = userLoggedModelDataMapper;
  }

  public void setView(@NonNull UserLoggedView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUserLoggedUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving message details.
   */
  public void initialize() {
    this.loadMessageDetails();
  }

  /**
   * Loads user details.
   */
  private void loadMessageDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserLogged();
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

  private void showUserLoggedInView(UserLogged userLogged) {
    final UserLoggedModel userLoggedModel = this.userLoggedModelDataMapper.transform(userLogged);
    this.viewDetailsView.renderUserLogged(userLoggedModel);
  }

  private void getUserLogged() {
    this.getUserLoggedUseCase.execute(new UserLoggedSubscriber());
  }

  private final class UserLoggedSubscriber extends DefaultSubscriber<UserLogged> {

    @Override public void onCompleted() {
      UserLoggedPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      UserLoggedPresenter.this.hideViewLoading();
      UserLoggedPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      UserLoggedPresenter.this.showViewRetry();
    }

    @Override public void onNext(UserLogged userLogged) {
      UserLoggedPresenter.this.showUserLoggedInView(userLogged);
    }
  }
}
