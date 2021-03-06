package com.innopro.android.sample.data.net;

import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.entity.UserLoggedEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestEndPoint {
    /**
     * Retrieves an {@link Observable} which will emit a {@link TokenEntity}.
     * Api url for getting a token
     */
    @GET("token.json")
    Observable<TokenEntity> tokenEntity();

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     *
     * @param userId The user id used to get user data.
     */
    @GET("user_{userId}.json")
    Observable<UserEntity> userEntityById(@Path("userId") int userId);

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
     * Api url for getting all users
     */
    @GET("users.json")
    Observable<List<UserEntity>> userEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserLoggedEntity}.
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    @GET("user_logged.json")
    Observable<UserLoggedEntity> userLoggedEntity();

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MessageEntity}.
     * Api url for getting all messages
     */
    @GET("messages.json")
    Observable<List<MessageEntity>> messageEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link MessageEntity}.
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param messageId The user id used to get user data.
     */
    @GET("message_{messageId}.json")
    Observable<MessageEntity> messageEntityById(@Path("messageId") int messageId);


    /**
     * Retrieves an {@link Observable} which will emit a List of {@link MessageEntity}.
     * Api url for getting all messages
     */
    @GET("categories.json")
    Observable<List<CategoryEntity>> categoryEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link MessageEntity}.
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param categoryId The user id used to get user data.
     */
    @GET("category_{categoryId}.json")
    Observable<CategoryEntity> categoryEntityById(@Path("categoryId") int categoryId);
}
