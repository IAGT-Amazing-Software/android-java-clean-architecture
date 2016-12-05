package com.innopro.android.sample.domain;

/**
 * Class that represents a User Logged in the domain layer.
 */
public class UserLogged {

  private final int userLoggedId;

  public UserLogged(int userLoggedId) {
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

    stringBuilder.append("***** User Details *****\n");
    stringBuilder.append("id=" + this.getUserLoggedId() + "\n");
    stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
    stringBuilder.append("fullname=" + this.getFullName() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
