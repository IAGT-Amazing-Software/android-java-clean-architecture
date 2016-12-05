package com.innopro.android.sample.presentation.model;

/**
 * Class that represents a user logged in the presentation layer.
 */
public class UserLoggedModel {

  private final int userLoggedId;

  public UserLoggedModel(int userLoggedId) {
    this.userLoggedId = userLoggedId;
  }

  private String avatarUrl;
  private String fullName;

  public int getUserLoggedId() {
    return userLoggedId;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** User Model Details *****\n");
    stringBuilder.append("id=" + this.getUserLoggedId() + "\n");
    stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
    stringBuilder.append("fullname=" + this.getFullName() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
