/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.innopro.android10.sample.data.cache.serializer;

import com.innopro.android.sample.data.cache.serializer.UserJsonSerializer;
import com.innopro.android.sample.data.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JsonSerializerTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private UserJsonSerializer userJsonSerializer;

  @Before
  public void setUp() {
    userJsonSerializer = new UserJsonSerializer();
  }

  @Test
  public void testSerializeHappyCase() {
    UserEntity userEntityOne = userJsonSerializer.deserialize(JSON_RESPONSE);
    String jsonString = userJsonSerializer.serialize(userEntityOne);
    UserEntity userEntityTwo = userJsonSerializer.deserialize(jsonString);

    assertThat(userEntityOne.getUserId(), is(userEntityTwo.getUserId()));
    assertThat(userEntityOne.getFullname(), is(equalTo(userEntityTwo.getFullname())));
    assertThat(userEntityOne.getFollowers(), is(userEntityTwo.getFollowers()));
  }

  @Test
  public void testDesearializeHappyCase() {
    UserEntity userEntity = userJsonSerializer.deserialize(JSON_RESPONSE);

    assertThat(userEntity.getUserId(), is(1));
    assertThat(userEntity.getFullname(), is("Simon Hill"));
    assertThat(userEntity.getFollowers(), is(7484));
  }
}
