package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.interactor.GetMessageCategory;
import com.innopro.android.sample.domain.interactor.GetMessageDetails;
import com.innopro.android.sample.domain.interactor.GetMessageList;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.MessageRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class MessageModule {

  public MessageModule() {}

  @Provides
  @Named("categoryList")
  UseCase provideGetMessageCategoryUseCase(
          GetMessageCategory getMessageCategory) {
    return getMessageCategory;
  }

  @Provides
  @Named("messageList")
  UseCase provideGetMessageListUseCase(
      GetMessageList getMessageList) {
    return getMessageList;
  }

  @Provides @Named("messageDetails")
  UseCase provideGetMessageDetailsUseCase(
          MessageRepository messageRepository,
          PostExecutionThread postExecutionThread) {
    return new GetMessageDetails( messageRepository, postExecutionThread);
  }
}