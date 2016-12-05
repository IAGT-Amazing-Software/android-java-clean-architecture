package com.innopro.android.sample.presentation.internal.di.modules;

import android.content.Context;

import com.innopro.android.sample.data.cache.CategoryCache;
import com.innopro.android.sample.data.cache.CategoryCacheImpl;
import com.innopro.android.sample.data.cache.MessageCache;
import com.innopro.android.sample.data.cache.MessageCacheImpl;
import com.innopro.android.sample.data.cache.UserCache;
import com.innopro.android.sample.data.cache.UserCacheImpl;
import com.innopro.android.sample.data.cache.UserLoggedCache;
import com.innopro.android.sample.data.cache.UserLoggedCacheImpl;
import com.innopro.android.sample.data.executor.JobExecutor;
import com.innopro.android.sample.data.repository.CategoryDataRepository;
import com.innopro.android.sample.data.repository.MessageDataRepository;
import com.innopro.android.sample.data.repository.UserDataRepository;
import com.innopro.android.sample.data.repository.UserLoggedDataRepository;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.CategoryRepository;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.presentation.AndroidApplication;
import com.innopro.android.sample.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton
  UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton
  UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }

  @Provides @Singleton
  MessageCache provideMessageCache(MessageCacheImpl messageCache) {
    return messageCache;
  }

  @Provides @Singleton
  MessageRepository provideMessageRepository(MessageDataRepository messageDataRepository) {
    return messageDataRepository;
  }

  @Provides @Singleton
  UserLoggedCache provideUserLoggedCache(UserLoggedCacheImpl userLoggedCache) {
    return userLoggedCache;
  }

  @Provides @Singleton
  UserLoggedRepository provideUserLoggedRepository(UserLoggedDataRepository userLoggedDataRepository) {
    return userLoggedDataRepository;
  }

  @Provides @Singleton
  CategoryCache provideCategoryCache(CategoryCacheImpl categoryCache) {
    return categoryCache;
  }

  @Provides @Singleton
  CategoryRepository provideCategoryRepository(CategoryDataRepository categoryDataRepository) {
    return categoryDataRepository;
  }
}
