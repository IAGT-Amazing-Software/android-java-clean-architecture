package com.innopro.android.sample.data.repository;

import com.innopro.android.sample.data.entity.mapper.MessageEntityDataMapper;
import com.innopro.android.sample.data.entity.mapper.UserEntityDataMapper;
import com.innopro.android.sample.data.repository.datasource.source.MessageDataStore;
import com.innopro.android.sample.data.repository.datasource.MessageDataStoreFactory;
import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.repository.MessageRepository;
import com.innopro.android.sample.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link MessageRepository} for retrieving message data.
 */
@Singleton
public class MessageDataRepository implements MessageRepository {

  private final MessageDataStoreFactory messageDataStoreFactory;
  private final MessageEntityDataMapper messageEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param messageDataStoreFactory A factory to construct different data source implementations.
   * @param messageEntityDataMapper {@link UserEntityDataMapper}.
   */
  @Inject
  public MessageDataRepository(MessageDataStoreFactory messageDataStoreFactory,
                               MessageEntityDataMapper messageEntityDataMapper) {
    this.messageDataStoreFactory = messageDataStoreFactory;
    this.messageEntityDataMapper = messageEntityDataMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<List<Message>> messages() {
    //we always get all messages from the cloud
    final MessageDataStore messageDataStore = this.messageDataStoreFactory.createCloudDataStore();
    return messageDataStore.messageEntityList()
        .map(messageEntities -> this.messageEntityDataMapper.transform(messageEntities));
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<Message> message(int messageId) {
    final MessageDataStore messageDataStore = this.messageDataStoreFactory.create(messageId);
    return messageDataStore.messageEntityDetails(messageId)
        .map(messageEntity -> this.messageEntityDataMapper.transform(messageEntity));
  }
}
