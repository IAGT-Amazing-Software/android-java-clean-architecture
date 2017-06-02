package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.executor.PostExecutionThread;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<T,Params> {

  private final PostExecutionThread postExecutionThread;

  private final CompositeDisposable disposables;

  protected UseCase(PostExecutionThread postExecutionThread) {
    this.postExecutionThread = postExecutionThread;
    this.disposables = new CompositeDisposable();
  }

  /**
   * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
   */
  protected abstract Observable<T> buildUseCaseObservable(Params params);

  /**
   * Executes the current use case.
   *
   * @param observer The guy who will be listen to the observable build
   *                          with {@link #buildUseCaseObservable(Params params)}.
   */
  @SuppressWarnings("unchecked")
  public void execute(DisposableObserver<T> observer,Params params) {

    Preconditions.checkNotNull(observer);
    final Observable<T> observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.getScheduler())
            .unsubscribeOn(Schedulers.io());
    addDisposable(observable.subscribeWith(observer));
  }

  /**
   * Unsubscribes from current {@link Subscription}.
   */
  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  /**
   * Dispose from current {@link CompositeDisposable}.
   */
  private void addDisposable(Disposable disposable) {
    Preconditions.checkNotNull(disposable);
    Preconditions.checkNotNull(disposables);
    disposables.add(disposable);
  }

}
