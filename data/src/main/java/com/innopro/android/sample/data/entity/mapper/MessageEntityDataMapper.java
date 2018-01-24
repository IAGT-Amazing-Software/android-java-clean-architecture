package com.innopro.android.sample.data.entity.mapper;

import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MessageEntity} (in the data layer) to {@link Message} in the
 * domain layer.
 */
@Singleton
public class MessageEntityDataMapper {
    //region Constants
    private static final String TAG = MessageEntityDataMapper.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Inject
    public MessageEntityDataMapper() {
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Transform a {@link MessageEntity} into an {@link User}.
     *
     * @param messageEntity Object to be transformed.
     * @return {@link Message} if valid {@link MessageEntity} otherwise null.
     */
    public Message transform(MessageEntity messageEntity) {
        Message message = null;
        if (messageEntity != null) {
            message = new Message(messageEntity.getMessageId());
            message.setImageUrl(messageEntity.getImageUrl());
            message.setName(messageEntity.getName());
            message.setDescription(messageEntity.getDescription());
        }

        return message;
    }

    /**
     * Transform a List of {@link MessageEntity} into a Collection of {@link Message}.
     *
     * @param messageEntityCollection Object Collection to be transformed.
     * @return {@link Message} if valid {@link UserEntity} otherwise null.
     */
    public List<Message> transform(Collection<MessageEntity> messageEntityCollection) {
        List<Message> messageList = new ArrayList<>(20);
        Message message;
        for (MessageEntity messageEntity : messageEntityCollection) {
            message = transform(messageEntity);
            if (message != null) {
                messageList.add(message);
            }
        }

        return messageList;
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
