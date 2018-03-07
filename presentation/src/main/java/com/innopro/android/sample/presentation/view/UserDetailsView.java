package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.domain.User;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface UserDetailsView extends LoadDataView {
  /**
   * Render a user in the UI.
   *
   * @param user The {@link User} that will be shown.
   */
  void renderUser(User user);
}
