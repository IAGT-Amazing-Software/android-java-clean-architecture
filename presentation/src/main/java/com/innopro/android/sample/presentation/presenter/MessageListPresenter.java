package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.view.MessageListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class MessageListPresenter implements Presenter {
    //region Constants
    private static final String TAG = MessageListPresenter.class.getSimpleName();
    //endregion

    //region Fields
    private MessageListView viewListView;

    private final UseCase getMessageListUseCase;

    //endregion

    //region Constructors & Initialization
    @Inject
    public MessageListPresenter(@Named("messageList") UseCase getMessageListUseCase) {
        this.getMessageListUseCase = getMessageListUseCase;
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
        this.getMessageListUseCase.dispose();
        this.viewListView = null;
    }

    //endregion

    //region Methods
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

    public void onMessageClicked(Message message) {
        this.viewListView.viewMessage(message);
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
        this.viewListView.renderMessageList(messagesCollection);
    }

    private void getMessageList() {
        this.getMessageListUseCase.execute(new MessageListSubscriber(), null);
    }
    //endregion

    //region Inner and Anonymous Classes
    private final class MessageListSubscriber extends DefaultSubscriber<List<Message>> {

        @Override
        public void onComplete() {
            MessageListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MessageListPresenter.this.hideViewLoading();
            MessageListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MessageListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Message> messages) {
            MessageListPresenter.this.showMessageCollectionInView(messages);
        }
    }
    //endregion

    //region Getter & Setter
    public void setView(@NonNull MessageListView view) {
        this.viewListView = view;
    }

    //endregion
}
