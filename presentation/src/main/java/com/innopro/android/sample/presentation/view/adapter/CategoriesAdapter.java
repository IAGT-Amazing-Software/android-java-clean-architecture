package com.innopro.android.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.model.CategoryModel;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link CategoryModel}.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

  public interface OnItemClickListener {
    void onCategoryItemClicked(CategoryModel categoryModel);
  }

  private List<CategoryModel> categoriesCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject
  public CategoriesAdapter(BaseActivity context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.categoriesCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.categoriesCollection != null) ? this.categoriesCollection.size() : 0;
  }

  @Override public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_category, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override public void onBindViewHolder(CategoryViewHolder holder, final int position) {
    final CategoryModel categoryModel = this.categoriesCollection.get(position);
    holder.textViewName.setText(categoryModel.getName());
    holder.itemView.setOnClickListener(v -> {
      if (CategoriesAdapter.this.onItemClickListener != null) {
        CategoriesAdapter.this.onItemClickListener.onCategoryItemClicked(categoryModel);
      }
    });
  }

  @Override public long getItemId(int position) {
    return this.categoriesCollection.get(position).getCategoryId();
  }

  public void setCategoriesCollection(Collection<CategoryModel> categoriesCollection) {
    this.validateCategoriesCollection(categoriesCollection);
    this.categoriesCollection = (List<CategoryModel>) categoriesCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateCategoriesCollection(Collection<CategoryModel> categoriesCollection) {
    if (categoriesCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class CategoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.tv_name) TextView textViewName;

    public CategoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
