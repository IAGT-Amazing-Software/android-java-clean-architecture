package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.executor.ThreadExecutor;
import com.innopro.android.sample.domain.repository.CategoryRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Category}.
 */
public class GetMessageCategory extends UseCase {

  private final CategoryRepository categoryRepository;

  @Inject
  public GetMessageCategory(CategoryRepository categoryRepository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.categoryRepository = categoryRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.categoryRepository.categories();
  }
}
