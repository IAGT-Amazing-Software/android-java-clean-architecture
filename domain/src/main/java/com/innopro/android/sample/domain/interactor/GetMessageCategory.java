package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Category}.
 */
public class GetMessageCategory extends UseCase<List<Category>,Void> {

  private final CategoryRepository categoryRepository;

  @Inject
  public GetMessageCategory(CategoryRepository categoryRepository, PostExecutionThread postExecutionThread) {
    super(postExecutionThread);
    this.categoryRepository = categoryRepository;
  }

  @Override public Observable buildUseCaseObservable(Void nothing) {
    return this.categoryRepository.categories();
  }
}
