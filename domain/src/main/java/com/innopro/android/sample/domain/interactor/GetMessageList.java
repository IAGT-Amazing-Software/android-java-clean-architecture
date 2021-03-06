package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Message}.
 */
public class GetMessageList extends UseCase<List<Message>, Void> {
    //region Constants
    private static final String TAG = GetMessageList.class.getSimpleName();
    //endregion

    //region Fields
    private final MessageRepository messageRepository;

    //endregion

    //region Constructors & Initialization
    @Inject
    public GetMessageList(MessageRepository messageRepository,
                          PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.messageRepository = messageRepository;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable buildUseCaseObservable(Void nothing) {
        return this.messageRepository.messages();
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
