package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.presentation.model.MessageModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a message.
 */
public interface MessageDetailsView extends LoadDataView {
  /**
   * Render a message in the UI.
   *
   * @param message The {@link MessageModel} that will be shown.
   */
  void renderMessage(MessageModel message);
}
