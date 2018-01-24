package com.innopro.android.sample.presentation.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class MessageModel {
    //region Constants
    private static final String TAG = MessageModel.class.getSimpleName();
    //endregion

    //region Fields
    private final int messageId;
    private String imageUrl;
    private String name;
    private String description;

    //endregion

    //region Constructors & Initialization
    public MessageModel(int messageId) {
        this.messageId = messageId;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getMessageId() + "\n");
        stringBuilder.append("image url=" + this.getImageUrl() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    public int getMessageId() {
        return messageId;
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
