package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.executor.PostExecutionThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class UseCaseTest {

  private UseCaseTestClass useCase;

  @Mock private com.innopro.android.sample.domain.executor.ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBuildUseCaseObservableReturnCorrectResult() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
    TestScheduler testScheduler = new TestScheduler();
    given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(0));
  }

  @Test
  public void testSubscriptionWhenExecutingUseCase() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

    useCase.execute(testSubscriber);
    useCase.unsubscribe();

    assertThat(testSubscriber.isUnsubscribed(), is(true));
  }

  private static class UseCaseTestClass extends UseCase {

    protected UseCaseTestClass(
        com.innopro.android.sample.domain.executor.ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
      super(threadExecutor, postExecutionThread);
    }

    @Override protected Observable buildUseCaseObservable() {
      return Observable.empty();
    }

    @Override public void execute(Subscriber UseCaseSubscriber) {
      super.execute(UseCaseSubscriber);
    }
  }
}