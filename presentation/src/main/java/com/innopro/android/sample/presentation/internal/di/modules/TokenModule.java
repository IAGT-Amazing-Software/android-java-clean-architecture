package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.interactor.GetToken;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.TokenRepository;
import com.innopro.android.sample.presentation.mapper.TokenModelDataMapper;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class TokenModule {

    public TokenModule() {
    }

    @Provides
    @Named("TokenUseCase")
    UseCase provideGetTokenUseCase(TokenRepository tokenRepository, PostExecutionThread postExecutionThread) {
        return new GetToken(tokenRepository, postExecutionThread);
    }
    @Provides
    TokenModelDataMapper provideTokenModelDataMapper(){
        return new TokenModelDataMapper();
    }


}
