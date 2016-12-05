package com.innopro.android.sample.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Category Entity used in the data layer.
 */
public class CategoryEntity {

  @SerializedName("id")
  private int categoryId;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("name")
  private String name;

  public CategoryEntity() {
    //empty
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
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

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** Category Entity Details *****\n");
    stringBuilder.append("id=" + this.getCategoryId() + "\n");
    stringBuilder.append("image url=" + this.getImageUrl() + "\n");
    stringBuilder.append("name=" + this.getName() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
