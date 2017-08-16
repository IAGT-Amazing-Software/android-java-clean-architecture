package com.innopro.android.sample.data.net;

import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.entity.UserLoggedEntity;

import java.util.List;
import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    /**
     * Retrieves an {@link Observable} which will emit a  {@link TokenEntity}.
     * Api url for getting token
     */
    Observable<TokenEntity> tokenEntity();

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     *
     * @param userId The user id used to get user data.
     */
    Observable<UserEntity> userEntityById(int userId);

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
     * Api url for getting all users
     */
    Observable<List<UserEntity>> userEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserLoggedEntity}.
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    Observable<UserLoggedEntity> userLoggedEntity();

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MessageEntity}.
     * Api url for getting all messages
     */
    Observable<List<MessageEntity>> messageEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link MessageEntity}.
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param messageId The user id used to get user data.
     */
    Observable<MessageEntity> messageEntityById( int messageId);


    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MessageEntity}.
     * Api url for getting all messages
     */
    Observable<List<CategoryEntity>> categoryEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link MessageEntity}.
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param categoryId The user id used to get user data.
     */
    Observable<CategoryEntity> categoryEntityById(int categoryId);
}
