package com.innopro.android.sample.presentation.mapper;

import com.innopro.android.sample.domain.UserLogged;
import com.innopro.android.sample.presentation.internal.di.PerActivity;
import com.innopro.android.sample.presentation.model.UserLoggedModel;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link UserLogged} (in the domain layer) to {@link UserLoggedModel} in the
 * presentation layer.
 */
@PerActivity
public class UserLoggedModelDataMapper {

  @Inject
  public UserLoggedModelDataMapper() {}

  /**
   * Transform a {@link UserLogged} into an {@link UserLoggedModel}.
   *
   * @param userLogged Object to be transformed.
   * @return {@link UserLoggedModel}.
   */
  public UserLoggedModel transform(UserLogged userLogged) {
    if (userLogged == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    UserLoggedModel userLoggedModel = new UserLoggedModel(userLogged.getUserLoggedId());
    userLoggedModel.setAvatarUrl(userLogged.getAvatarUrl());
    userLoggedModel.setFullName(userLogged.getFullName());

    return userLoggedModel;
  }

}
