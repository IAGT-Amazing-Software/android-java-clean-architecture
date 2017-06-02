package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.MessageRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Message}.
 */
public class GetMessageDetails extends UseCase<Message,GetMessageDetails.Params> {


  private final MessageRepository messageRepository;

  @Inject
  public GetMessageDetails( MessageRepository messageRepository, PostExecutionThread postExecutionThread) {
    super(postExecutionThread);
    this.messageRepository = messageRepository;
  }

  @Override protected Observable buildUseCaseObservable(Params params) {
    return this.messageRepository.message(params.messageId);
  }

  public static final class Params {
    private final int messageId;

    private Params(int messageId) {
      this.messageId = messageId;
    }

    public static Params forGetUserFiles(int messageId) {
      return new Params(messageId);
    }
  }
}
