package com.innopro.android.sample.data.cache.serializer;

import com.google.gson.Gson;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class CategoryJsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public CategoryJsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param categoryEntity {@link MessageEntity} to serialize.
   */
  public String serialize(CategoryEntity categoryEntity) {
    return gson.toJson(categoryEntity, CategoryEntity.class);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link MessageEntity}
   */
  public CategoryEntity deserialize(String jsonString) {
    return gson.fromJson(jsonString, CategoryEntity.class);
  }
}
