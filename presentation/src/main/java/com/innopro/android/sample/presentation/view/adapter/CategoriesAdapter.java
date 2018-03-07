package com.innopro.android.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopro.android.sample.domain.Category;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link Category}.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    //region Constants
    private static final String TAG = CategoriesAdapter.class.getSimpleName();
    //endregion

    //region Fields
    private List<Category> categoriesCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    //endregion

    //region Constructors & Initialization
    @Inject
    public CategoriesAdapter(BaseActivity context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoriesCollection = Collections.emptyList();
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public int getItemCount() {
        return (this.categoriesCollection != null) ? this.categoriesCollection.size() : 0;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        final Category categoryModel = this.categoriesCollection.get(position);
        holder.textViewName.setText(categoryModel.getName());
        holder.itemView.setOnClickListener(v -> {
            if (CategoriesAdapter.this.onItemClickListener != null) {
                CategoriesAdapter.this.onItemClickListener.onCategoryItemClicked(categoryModel);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return this.categoriesCollection.get(position).getCategoryId();
    }
    //endregion

    //region Methods
    private void validateCategoriesCollection(Collection<Category> categoriesCollection) {
        if (categoriesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
    //endregion

    //region Inner and Anonymous Classes
    public interface OnItemClickListener {
        void onCategoryItemClicked(Category category);
    }
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_name)
        TextView textViewName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //endregion

    //region Getter & Setter
    public void setCategoriesCollection(Collection<Category> categoriesCollection) {
        this.validateCategoriesCollection(categoriesCollection);
        this.categoriesCollection = (List<Category>) categoriesCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //endregion


}
