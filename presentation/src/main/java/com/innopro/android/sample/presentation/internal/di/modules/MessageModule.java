package com.innopro.android.sample.presentation.internal.di.modules;

import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.interactor.GetMessageCategory;
import com.innopro.android.sample.domain.interactor.GetMessageDetails;
import com.innopro.android.sample.domain.interactor.GetMessageList;
import com.innopro.android.sample.domain.interactor.UseCase;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class MessageModule {

  private int messageId = -1;

  public MessageModule() {}

  public MessageModule(int messageId) {
    this.messageId = messageId;
  }

  @Provides @PerActivity
  @Named("categoryList")
  UseCase provideGetMessageCategoryUseCase(
          GetMessageCategory getMessageCategory) {
    return getMessageCategory;
  }

  @Provides @PerActivity
  @Named("messageList")
  UseCase provideGetMessageListUseCase(
      GetMessageList getMessageList) {
    return getMessageList;
  }

  @Provides @PerActivity @Named("messageDetails")
  UseCase provideGetMessageDetailsUseCase(
          MessageRepository messageRepository, ThreadExecutor threadExecutor,
          PostExecutionThread postExecutionThread) {
    return new GetMessageDetails(messageId, messageRepository, threadExecutor, postExecutionThread);
  }
}