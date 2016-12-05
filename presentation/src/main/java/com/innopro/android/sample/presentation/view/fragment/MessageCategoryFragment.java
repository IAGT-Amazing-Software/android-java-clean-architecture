package com.innopro.android.sample.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.MainComponent;
import com.innopro.android.sample.presentation.model.CategoryModel;
import com.innopro.android.sample.presentation.presenter.MessageCategoryPresenter;
import com.innopro.android.sample.presentation.view.MessageCategoryView;
import com.innopro.android.sample.presentation.view.adapter.CategoriesAdapter;
import com.innopro.android.sample.presentation.view.adapter.layoutmanager.CategoriesLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Message.
 */
public class MessageCategoryFragment extends BaseFragment implements MessageCategoryView {

  /**
   * Interface for listening message list events.
   */
  public interface MessageCategoryListener {
    void onCategoryClicked(final CategoryModel categoryModel);
  }

  @Inject
  MessageCategoryPresenter messageCategoryPresenter;
  @Inject
  CategoriesAdapter categoriesAdapter;

  @BindView(R2.id.rv_categories) RecyclerView rv_categories;
  @BindView(R2.id.rl_progress) RelativeLayout rl_progress;
  @BindView(R2.id.rl_retry) RelativeLayout rl_retry;
  @BindView(R2.id.bt_retry) Button bt_retry;

  private MessageCategoryListener messageCategoryListener;

  public MessageCategoryFragment() {
    setRetainInstance(true);
  }

  public void attachListener(){
    try {
      this.messageCategoryListener = (MessageCategoryListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(MessageCategoryFragment.class.getName(), e.getLocalizedMessage());
    }
  }


  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    attachListener();
    this.getComponent(MainComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_category_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.messageCategoryPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadCategoryList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.messageCategoryPresenter.resume();
    getActivity().setTitle(getResources().getString(R.string.activity_title_message_category));
  }

  @Override public void onPause() {
    super.onPause();
    this.messageCategoryPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_categories.setAdapter(null);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.messageCategoryPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.messageCategoryListener = null;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderCategoryList(Collection<CategoryModel> categoryModelCollection) {
    if (categoryModelCollection != null) {
      this.categoriesAdapter.setCategoriesCollection(categoryModelCollection);
    }
  }

  @Override public void viewMessageList(CategoryModel categoryModel) {
    if (this.messageCategoryListener != null) {
      this.messageCategoryListener.onCategoryClicked(categoryModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.categoriesAdapter.setOnItemClickListener(onItemClickListener);
    this.rv_categories.setLayoutManager(new CategoriesLayoutManager(context()));
    this.rv_categories.setAdapter(categoriesAdapter);
  }

  /**
   * Loads all messages.
   */
  private void loadCategoryList() {
    this.messageCategoryPresenter.initialize();
  }

  @OnClick(R2.id.bt_retry) void onButtonRetryClick() {
    MessageCategoryFragment.this.loadCategoryList();
  }

  private CategoriesAdapter.OnItemClickListener onItemClickListener =
          categoryModel -> {
            if (MessageCategoryFragment.this.messageCategoryPresenter != null && categoryModel != null) {
              MessageCategoryFragment.this.messageCategoryPresenter.onCategoryClicked(categoryModel);
            }
          };
}
