package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.MessageRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Message}.
 */
public class GetMessageList extends UseCase {

  private final MessageRepository messageRepository;

  @Inject
  public GetMessageList(MessageRepository messageRepository, ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.messageRepository = messageRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.messageRepository.messages();
  }
}
