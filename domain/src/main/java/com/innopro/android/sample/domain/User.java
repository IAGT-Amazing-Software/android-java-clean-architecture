package com.innopro.android.sample.domain;

/**
 * Class that represents a User in the domain layer.
 */
public class User {
    //region Constants
    private static final String TAG = User.class.getSimpleName();
    //endregion

    //region Fields
    private final int userId;
    private String coverUrl;
    private String fullName;
    private String email;
    private String description;
    private int followers;

    //endregion

    //region Constructors & Initialization
    public User(int userId) {
        this.userId = userId;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
        stringBuilder.append("fullname=" + this.getFullName() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("followers=" + this.getFollowers() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter
    public int getUserId() {
        return userId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
    //endregion
}
