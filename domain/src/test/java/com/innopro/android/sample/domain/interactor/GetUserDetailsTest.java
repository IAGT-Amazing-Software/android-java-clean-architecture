package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.executor.PostExecutionThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserDetailsTest {

  private static final int FAKE_USER_ID = 123;

  private GetUserDetails getUserDetails;

  @Mock private com.innopro.android.sample.domain.repository.UserRepository mockUserRepository;
  @Mock private com.innopro.android.sample.domain.executor.ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getUserDetails = new GetUserDetails(FAKE_USER_ID, mockUserRepository,
        mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  public void testGetUserDetailsUseCaseObservableHappyCase() {
    getUserDetails.buildUseCaseObservable();

    verify(mockUserRepository).user(FAKE_USER_ID);
    verifyNoMoreInteractions(mockUserRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }
}
