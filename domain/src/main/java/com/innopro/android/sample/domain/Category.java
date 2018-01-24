package com.innopro.android.sample.domain;

/**
 * Class that represents a User in the domain layer.
 */
public class Category {
    //region Constants
    private static final String TAG = Category.class.getSimpleName();
    //endregion

    //region Fields
    private final int categoryId;
    private String imageUrl;
    private String name;

    //endregion

    //region Constructors & Initialization
    public Category(int messageId) {
        this.categoryId = messageId;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Message Category *****\n");
        stringBuilder.append("id=" + this.getCategoryId() + "\n");
        stringBuilder.append("image url=" + this.getImageUrl() + "\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter
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

    //endregion
}
