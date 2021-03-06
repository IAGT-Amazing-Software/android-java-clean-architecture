package com.innopro.android.sample.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.innopro.android.sample.domain.Token;
import com.innopro.android.sample.domain.exception.DefaultErrorBundle;
import com.innopro.android.sample.domain.exception.ErrorBundle;
import com.innopro.android.sample.domain.interactor.DefaultSubscriber;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.presentation.exception.ErrorMessageFactory;
import com.innopro.android.sample.presentation.view.TokenView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class TokenPresenter implements Presenter {
    //region Constants
    private static final String TAG = TokenPresenter.class.getSimpleName();
    //endregion

    //region Fields
    private final UseCase useCase;
    private TokenView tokenView;

    //endregion

    //region Constructors & Initialization
    @Inject
    public TokenPresenter(@Named("TokenUseCase") UseCase useCase) {
        this.useCase = useCase;
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
        this.useCase.dispose();
        this.tokenView = null;
    }

    //endregion

    //region Methods

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

    private void showTokenInView(Token token) {
        this.tokenView.renderToken(token);
    }

    private void getToken() {
        this.useCase.execute(new TokenSubscriber(), null);
    }
    //endregion

    //region Inner and Anonymous Classes
    private final class TokenSubscriber extends DefaultSubscriber<Token> {

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
        public void onNext(Token token) {
            Log.i("Token from Presenter", token.getValue());
            TokenPresenter.this.showTokenInView(token);
        }
    }
    //endregion

    //region Getter & Setter
    public void setView(@NonNull TokenView view) {
        this.tokenView = view;
    }
    //endregion


}
