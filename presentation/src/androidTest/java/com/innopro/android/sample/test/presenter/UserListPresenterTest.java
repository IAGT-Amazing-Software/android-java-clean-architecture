package com.innopro.android.sample.test.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.innopro.android.sample.presentation.mapper.UserModelDataMapper;
import com.innopro.android.sample.presentation.presenter.UserListPresenter;
import com.innopro.android.sample.presentation.view.UserListView;
import com.innopro.android.sample.domain.interactor.GetUserList;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserListPresenterTest extends AndroidTestCase {

  private UserListPresenter userListPresenter;

  @Mock
  private Context mockContext;
  @Mock
  private UserListView mockUserListView;
  @Mock
  private GetUserList mockGetUserList;
  @Mock
  private UserModelDataMapper mockUserModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    userListPresenter = new UserListPresenter(mockGetUserList, mockUserModelDataMapper);
    userListPresenter.setView(mockUserListView);
  }

  public void testUserListPresenterInitialize() {
    given(mockUserListView.context()).willReturn(mockContext);

    userListPresenter.initialize();

    verify(mockUserListView).hideRetry();
    verify(mockUserListView).showLoading();
    verify(mockGetUserList).execute(any(Subscriber.class));
  }
}
