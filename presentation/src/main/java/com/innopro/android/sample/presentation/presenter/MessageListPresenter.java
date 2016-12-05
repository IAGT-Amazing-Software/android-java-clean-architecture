package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.mapper.MessageModelDataMapper;
import com.innopro.android.sample.presentation.model.MessageModel;
import com.innopro.android.sample.presentation.view.MessageListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MessageListPresenter implements Presenter {

  private MessageListView viewListView;

  private final UseCase getMessageListUseCase;
  private final MessageModelDataMapper messageModelDataMapper;

  @Inject
  public MessageListPresenter(@Named("messageList") UseCase getMessageListUseCase,
                              MessageModelDataMapper messageModelDataMapper) {
    this.getMessageListUseCase = getMessageListUseCase;
    this.messageModelDataMapper = messageModelDataMapper;
  }

  public void setView(@NonNull MessageListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getMessageListUseCase.unsubscribe();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the message list.
   */
  public void initialize() {
    this.loadMessageList();
  }

  /**
   * Loads all message.
   */
  private void loadMessageList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getMessageList();
  }

  public void onMessageClicked(MessageModel messageModel) {
    this.viewListView.viewMessage(messageModel);
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

  private void showMessageCollectionInView(Collection<Message> messagesCollection) {
    final Collection<MessageModel> messageModelsCollection =
        this.messageModelDataMapper.transform(messagesCollection);
    this.viewListView.renderMessageList(messageModelsCollection);
  }

  private void getMessageList() {
    this.getMessageListUseCase.execute(new MessageListSubscriber());
  }

  private final class MessageListSubscriber extends DefaultSubscriber<List<Message>> {

    @Override public void onCompleted() {
      MessageListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MessageListPresenter.this.hideViewLoading();
      MessageListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MessageListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Message> messages) {
      MessageListPresenter.this.showMessageCollectionInView(messages);
    }
  }
}
