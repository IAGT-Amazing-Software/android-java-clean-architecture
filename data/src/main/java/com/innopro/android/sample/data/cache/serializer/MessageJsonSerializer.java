package com.innopro.android.sample.data.cache.serializer;

import com.google.gson.Gson;
import com.innopro.android.sample.data.entity.MessageEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class MessageJsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public MessageJsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param messageEntity {@link MessageEntity} to serialize.
   */
  public String serialize(MessageEntity messageEntity) {
    return gson.toJson(messageEntity, MessageEntity.class);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link MessageEntity}
   */
  public MessageEntity deserialize(String jsonString) {
    return gson.fromJson(jsonString, MessageEntity.class);
  }
}
