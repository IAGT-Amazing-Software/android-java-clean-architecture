package com.innopro.android.sample.presentation.model;

/**
 * Class that represents a user logged in the presentation layer.
 */
public class UserLoggedModel {
    //region Constants
    private static final String TAG = UserLoggedModel.class.getSimpleName();
    //endregion

    //region Fields
    private final int userLoggedId;
    private String avatarUrl;
    private String fullName;
    //endregion

    //region Constructors & Initialization
    public UserLoggedModel(int userLoggedId) {
        this.userLoggedId = userLoggedId;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getUserLoggedId() + "\n");
        stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
        stringBuilder.append("fullname=" + this.getFullName() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter
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

    //endregion
}
