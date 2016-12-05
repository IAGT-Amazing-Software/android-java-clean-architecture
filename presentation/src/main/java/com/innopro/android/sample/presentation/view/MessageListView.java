package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.presentation.model.MessageModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link MessageModel}.
 */
public interface MessageListView extends LoadDataView {
  /**
   * Render a message list in the UI.
   *
   * @param messageModelCollection The collection of {@link MessageModel} that will be shown.
   */
  void renderMessageList(Collection<MessageModel> messageModelCollection);

  /**
   * View a {@link MessageModel} profile/details.
   *
   * @param messageModel The user that will be shown.
   */
  void viewMessage(MessageModel messageModel);
}
