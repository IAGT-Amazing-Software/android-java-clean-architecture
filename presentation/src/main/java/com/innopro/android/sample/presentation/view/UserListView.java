package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.domain.User;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link User}.
 */
public interface UserListView extends LoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link User} that will be shown.
   */
  void renderUserList(Collection<User> userModelCollection);

  /**
   * View a {@link User} profile/details.
   *
   * @param user The user that will be shown.
   */
  void viewUser(User user);
}
