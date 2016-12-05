package com.innopro.android.sample.presentation.mapper;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.model.MessageModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Message} (in the domain layer) to {@link MessageModel} in the
 * presentation layer.
 */
@PerActivity
public class MessageModelDataMapper {

  @Inject
  public MessageModelDataMapper() {}

  /**
   * Transform a {@link Message} into an {@link MessageModel}.
   *
   * @param message Object to be transformed.
   * @return {@link MessageModel}.
   */
  public MessageModel transform(Message message) {
    if (message == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    MessageModel mesageModel = new MessageModel(message.getMessageId());
    mesageModel.setImageUrl(message.getImageUrl());
    mesageModel.setName(message.getName());
    mesageModel.setDescription(message.getDescription());

    return mesageModel;
  }

  /**
   * Transform a Collection of {@link User} into a Collection of {@link MessageModel}.
   *
   * @param messagesCollection Objects to be transformed.
   * @return List of {@link MessageModel}.
   */
  public Collection<MessageModel> transform(Collection<Message> messagesCollection) {
    Collection<MessageModel> messageModelsCollection;

    if (messagesCollection != null && !messagesCollection.isEmpty()) {
      messageModelsCollection = new ArrayList<>();
      for (Message message : messagesCollection) {
        messageModelsCollection.add(transform(message));
      }
    } else {
      messageModelsCollection = Collections.emptyList();
    }

    return messageModelsCollection;
  }
}
