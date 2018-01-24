package com.innopro.android.sample.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * User Entity used in the data layer.
 */
public class UserLoggedEntity extends RealmObject {
    //region Constants
    private static final String TAG = UserLoggedEntity.class.getSimpleName();
    //endregion

    //region Fields
    @PrimaryKey
    @SerializedName("id")
    private int userLoggedId;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("full_name")
    private String fullname;

    //endregion

    //region Constructors & Initialization
    public UserLoggedEntity() {
        //empty
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getUserLoggedId() + "\n");
        stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
        stringBuilder.append("fullname=" + this.getFullname() + "\n");
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

    public void setUserLoggedId(int userLoggedId) {
        this.userLoggedId = userLoggedId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    //endregion
}
