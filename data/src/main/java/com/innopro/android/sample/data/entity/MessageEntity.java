package com.innopro.android.sample.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * User Entity used in the data layer.
 */
public class MessageEntity {

  @SerializedName("id")
  private int messageId;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  public MessageEntity() {
    //empty
  }

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

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** User Entity Details *****\n");
    stringBuilder.append("id=" + this.getMessageId() + "\n");
    stringBuilder.append("image url=" + this.getImageUrl() + "\n");
    stringBuilder.append("name=" + this.getName() + "\n");
    stringBuilder.append("description=" + this.getDescription() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
