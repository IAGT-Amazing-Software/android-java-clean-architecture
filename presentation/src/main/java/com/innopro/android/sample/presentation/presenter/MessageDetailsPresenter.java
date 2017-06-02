package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.GetMessageDetails;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.mapper.MessageModelDataMapper;
import com.innopro.android.sample.presentation.model.MessageModel;
import com.innopro.android.sample.presentation.view.MessageDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class MessageDetailsPresenter implements Presenter {

  private MessageDetailsView viewDetailsView;

  private final UseCase getMessageDetailsUseCase;
  private final MessageModelDataMapper messageModelDataMapper;

  @Inject
  public MessageDetailsPresenter(@Named("messageDetails") UseCase getMessageDetailsUseCase,
                                 MessageModelDataMapper messageModelDataMapper) {
    this.getMessageDetailsUseCase = getMessageDetailsUseCase;
    this.messageModelDataMapper = messageModelDataMapper;
  }

  public void setView(@NonNull MessageDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getMessageDetailsUseCase.dispose();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving message details.
   */
  public void initialize(int messageId) {
    this.loadMessageDetails(messageId);
  }

  /**
   * Loads user details.
   */
  private void loadMessageDetails(int messageId) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getMessageDetails(messageId);
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

  private void showMessageDetailsInView(Message message) {
    final MessageModel messageModel = this.messageModelDataMapper.transform(message);
    this.viewDetailsView.renderMessage(messageModel);
  }

  private void getMessageDetails(int messageId) {
    this.getMessageDetailsUseCase.execute(new MessageDetailsSubscriber(), GetMessageDetails.Params.forGetUserFiles(messageId));
  }

  private final class MessageDetailsSubscriber extends DefaultSubscriber<Message> {

    @Override public void onComplete() {
      MessageDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MessageDetailsPresenter.this.hideViewLoading();
      MessageDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MessageDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(Message message) {
      MessageDetailsPresenter.this.showMessageDetailsInView(message);
    }
  }
}
