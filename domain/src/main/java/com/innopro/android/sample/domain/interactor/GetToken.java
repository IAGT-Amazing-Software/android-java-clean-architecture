package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Token;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.TokenRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Token}.
 */
public class GetToken extends UseCase<Token,Void> {
    private final TokenRepository tokenRepository;

    @Inject
    public GetToken(TokenRepository tokenRepository,
                    PostExecutionThread postExecutionThread) {
        super( postExecutionThread);
        this.tokenRepository = tokenRepository;
    }

    @Override protected Observable buildUseCaseObservable(Void nothing) {
        return this.tokenRepository.token();
    }
}
