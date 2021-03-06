package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.GetMessageDetails;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.view.MessageDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class MessageDetailsPresenter implements Presenter {
    //region Constants
    private static final String TAG = MessageDetailsPresenter.class.getSimpleName();
    //endregion

    //region Fields
    private MessageDetailsView viewDetailsView;

    private final UseCase getMessageDetailsUseCase;

    //endregion

    //region Constructors & Initialization
    @Inject
    public MessageDetailsPresenter(@Named("messageDetails") UseCase getMessageDetailsUseCase) {
        this.getMessageDetailsUseCase = getMessageDetailsUseCase;
    }
    /**
     * Initializes the presenter by start retrieving message details.
     */
    public void initialize(int messageId) {
        this.loadMessageDetails(messageId);
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
        this.getMessageDetailsUseCase.dispose();
        this.viewDetailsView = null;
    }

    //endregion

    //region Methods
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
        this.viewDetailsView.renderMessage(message);
    }

    private void getMessageDetails(int messageId) {
        this.getMessageDetailsUseCase.execute(new MessageDetailsSubscriber(), GetMessageDetails.Params.forGetUserFiles(messageId));
    }
    //endregion

    //region Inner and Anonymous Classes
    private final class MessageDetailsSubscriber extends DefaultSubscriber<Message> {

        @Override
        public void onComplete() {
            MessageDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MessageDetailsPresenter.this.hideViewLoading();
            MessageDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MessageDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Message message) {
            MessageDetailsPresenter.this.showMessageDetailsInView(message);
        }
    }
    //endregion

    //region Getter & Setter
    public void setView(@NonNull MessageDetailsView view) {
        this.viewDetailsView = view;
    }
    //endregion


}
