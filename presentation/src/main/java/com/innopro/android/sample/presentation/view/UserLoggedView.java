package com.innopro.android.sample.presentation.view;

import com.innopro.android.sample.domain.UserLogged;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user logged.
 */
public interface UserLoggedView extends LoadDataView {
  /**
   * Render a user in the UI.
   *
   * @param userLogged The {@link UserLogged} that will be shown.
   */
  void renderUserLogged(UserLogged userLogged);
}
