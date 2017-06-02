package com.innopro.android.sample.domain.repository;

import com.innopro.android.sample.domain.Message;

import java.util.List;

import io.reactivex.Observable;


/**
 * Interface that represents a Repository for getting {@link Message} related data.
 */
public interface MessageRepository {
  /**
   * Get an {@link Observable} which will emit a List of {@link Message}.
   */
  Observable<List<Message>> messages();

  /**
   * Get an {@link Observable} which will emit a {@link Message}.
   *
   * @param messageId The user id used to retrieve user data.
   */
  Observable<Message> message(final int messageId);
}
