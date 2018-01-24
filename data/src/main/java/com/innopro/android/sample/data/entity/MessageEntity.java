package com.innopro.android.sample.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * User Entity used in the data layer.
 */
public class MessageEntity extends RealmObject {
    //region Constants
    private static final String TAG = MessageEntity.class.getSimpleName();
    //endregion

    //region Fields
    @PrimaryKey
    @SerializedName("id")
    private int messageId;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    //endregion

    //region Constructors & Initialization
    public MessageEntity() {
        //empty
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getMessageId() + "\n");
        stringBuilder.append("image url=" + this.getImageUrl() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion
}
