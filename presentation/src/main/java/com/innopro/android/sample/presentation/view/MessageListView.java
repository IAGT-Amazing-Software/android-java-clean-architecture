package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.domain.Message;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link Message}.
 */
public interface MessageListView extends LoadDataView {
  /**
   * Render a message list in the UI.
   *
   * @param messageCollection The collection of {@link Message} that will be shown.
   */
  void renderMessageList(Collection<Message> messageCollection);

  /**
   * View a {@link Message} profile/details.
   *
   * @param message The user that will be shown.
   */
  void viewMessage(Message message);
}
