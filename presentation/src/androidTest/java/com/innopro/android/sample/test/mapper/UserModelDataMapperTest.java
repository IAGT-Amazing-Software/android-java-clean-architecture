package com.innopro.android.sample.test.mapper;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.presentation.mapper.UserModelDataMapper;
import com.innopro.android.sample.presentation.model.UserModel;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class UserModelDataMapperTest extends TestCase {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULLNAME = "Tony Stark";

  private UserModelDataMapper userModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    userModelDataMapper = new UserModelDataMapper();
  }

  public void testTransformUser() {
    User user = createFakeUser();
    UserModel userModel = userModelDataMapper.transform(user);

    assertThat(userModel, is(instanceOf(UserModel.class)));
    assertThat(userModel.getUserId(), is(FAKE_USER_ID));
    assertThat(userModel.getFullName(), is(FAKE_FULLNAME));
  }

  public void testTransformUserCollection() {
    User mockUserOne = mock(User.class);
    User mockUserTwo = mock(User.class);

    List<User> userList = new ArrayList<User>(5);
    userList.add(mockUserOne);
    userList.add(mockUserTwo);

    Collection<UserModel> userModelList = userModelDataMapper.transform(userList);

    assertThat(userModelList.toArray()[0], is(instanceOf(UserModel.class)));
    assertThat(userModelList.toArray()[1], is(instanceOf(UserModel.class)));
    assertThat(userModelList.size(), is(2));
  }

  private User createFakeUser() {
    User user = new User(FAKE_USER_ID);
    user.setFullName(FAKE_FULLNAME);

    return user;
  }
}
