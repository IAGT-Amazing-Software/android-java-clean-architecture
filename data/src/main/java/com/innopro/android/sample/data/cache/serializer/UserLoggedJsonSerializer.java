package com.innopro.android.sample.data.cache.serializer;

import com.google.gson.Gson;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.entity.UserLoggedEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class UserLoggedJsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public UserLoggedJsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userLoggedEntity {@link UserEntity} to serialize.
   */
  public String serialize(UserLoggedEntity userLoggedEntity) {
    return gson.toJson(userLoggedEntity, UserLoggedEntity.class);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link UserEntity}
   */
  public UserLoggedEntity deserialize(String jsonString) {
    return gson.fromJson(jsonString, UserLoggedEntity.class);
  }
}
