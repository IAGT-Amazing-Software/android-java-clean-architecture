package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.UserRepository;
import com.innopro.android.sample.domain.executor.PostExecutionThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserListTest {

  private GetUserList getUserList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private UserRepository mockUserRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getUserList = new GetUserList(mockUserRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetUserListUseCaseObservableHappyCase() {
    getUserList.buildUseCaseObservable();

    verify(mockUserRepository).users();
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
