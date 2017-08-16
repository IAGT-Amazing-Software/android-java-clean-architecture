package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.view.TokenView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by iagt on 15/08/17.
 */

public class TokenPresenter implements Presenter {

    private final UseCase useCase;
    private TokenView tokenView;

    @Inject
    public TokenPresenter(@Named("TokenUseCase") UseCase useCase) {
        this.useCase = useCase;
    }

    public void setView(@NonNull TokenView view) {
        this.tokenView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.useCase.dispose();
        this.tokenView = null;
    }

    /**
     * Initializes the presenter by start retrieving the message list.
     */
    public void initialize() {
        this.load();
    }

    /**
     * Loads all message.
     */
    private void load() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getToken();
    }

    private void showViewLoading() {
        this.tokenView.showLoading();
    }

    private void hideViewLoading() {

    }

    private void showViewRetry() {
        this.tokenView.showRetry();
    }

    private void hideViewRetry() {
        this.tokenView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.tokenView.context(),
                errorBundle.getException());
        this.tokenView.showError(errorMessage);
    }

    private void showTokenInView(String token) {
        this.tokenView.renderToken(token);
    }

    private void getToken() {
        this.useCase.execute(new TokenSubscriber(), null);
    }

    private final class TokenSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onComplete() {
            TokenPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            TokenPresenter.this.hideViewLoading();
            TokenPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            TokenPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String token) {
            Log.i("Token from Presenter", token);
            TokenPresenter.this.showTokenInView(token);
        }
    }
}
