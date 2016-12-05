package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.MessageRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Message}.
 */
public class GetMessageDetails extends UseCase {

  private final int messageId;
  private final MessageRepository messageRepository;

  @Inject
  public GetMessageDetails(int messageId, MessageRepository messageRepository,
                           ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.messageId = messageId;
    this.messageRepository = messageRepository;
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.messageRepository.message(this.messageId);
  }
}
