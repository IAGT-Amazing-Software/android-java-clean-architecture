package com.innopro.android.sample.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.TokenCache;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.cache.impl.CategoryCacheImpl;
import com.innopro.android.sample.data.cache.impl.MessageCacheImpl;
import com.innopro.android.sample.data.cache.impl.TokenCacheImpl;
import com.innopro.android.sample.data.cache.impl.UserCacheImpl;
import com.innopro.android.sample.data.cache.impl.UserLoggedCacheImpl;
import com.innopro.android.sample.data.repository.CategoryDataRepository;
import com.innopro.android.sample.data.repository.MessageDataRepository;
import com.innopro.android.sample.data.repository.TokenDataRepository;
import com.innopro.android.sample.data.repository.UserDataRepository;
import com.innopro.android.sample.data.repository.UserLoggedDataRepository;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.CategoryRepository;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.domain.repository.TokenRepository;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.presentation.AndroidApplication;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.UIThread;
import com.innopro.android.sample.presentation.navigation.Navigator;
import com.innopro.android.sample.presentation.utils.PreferencesUtils;
import com.innopro.android.sample.presentation.utils.SharedPreferencesManager;

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
    SharedPreferencesManager provideSharedPreferencesManager() {
        Application application = AndroidApplication.getInstance();
        SharedPreferences sharedPreferences = application.getSharedPreferences(application.getString(R.string.app_name), 0);
        PreferencesUtils preferencesUtils = new PreferencesUtils(sharedPreferences);
        return new SharedPreferencesManager(preferencesUtils);
    }

    @Provides
    @Singleton
    Navigator provideNavigator(){
        return new Navigator();
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

    @Provides
    TokenCache provideTokenCache(TokenCacheImpl cache){
        return cache;
    }

    @Provides
    TokenRepository provideTokenRepository(TokenDataRepository tokenRepository) {
        return tokenRepository;
    }


}
