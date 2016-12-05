package com.innopro.android.sample.presentation.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class CategoryModel {

  private final int categoryId;

  public CategoryModel(int categoryId) {
    this.categoryId = categoryId;
  }

  private String imageUrl;
  private String name;

  public int getCategoryId() {
    return categoryId;
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

    stringBuilder.append("***** Message Model Category *****\n");
    stringBuilder.append("id=" + this.getCategoryId() + "\n");
    stringBuilder.append("image url=" + this.getImageUrl() + "\n");
    stringBuilder.append("name=" + this.getName() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }



}
