package com.innopro.android.sample.presentation.internal.di.modules;

import android.content.Context;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.CategoryRepository;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.domain.repository.TokenRepository;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.presentation.AndroidApplication;
import com.innopro.android.sample.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        Realm.init(this.application);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        return this.application;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    MessageCache provideMessageCache(MessageCacheImpl messageCache) {
        return messageCache;
    }

    @Provides
    @Singleton
    MessageRepository provideMessageRepository(MessageDataRepository messageDataRepository) {
        return messageDataRepository;
    }

    @Provides
    @Singleton
    UserLoggedCache provideUserLoggedCache(UserLoggedCacheImpl userLoggedCache) {
        return userLoggedCache;
    }

    @Provides
    @Singleton
    UserLoggedRepository provideUserLoggedRepository(UserLoggedDataRepository userLoggedDataRepository) {
        return userLoggedDataRepository;
    }

    @Provides
    @Singleton
    CategoryCache provideCategoryCache(CategoryCacheImpl categoryCache) {
        return categoryCache;
    }

    @Provides
    @Singleton
    CategoryRepository provideCategoryRepository(CategoryDataRepository categoryDataRepository) {
        return categoryDataRepository;
    }

    @Provides @Singleton
    TokenRepository provideTokenRepository(TokenDataRepository tokenRepository) {
        return tokenRepository;
    }
}
