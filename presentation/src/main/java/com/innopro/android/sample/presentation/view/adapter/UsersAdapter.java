package com.innopro.android.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopro.android.sample.domain.User;
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
 * Adaptar that manages a collection of {@link User}.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    //region Constants
    private static final String TAG = UsersAdapter.class.getSimpleName();
    //endregion

    //region Fields
    private List<User> usersCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;
    //endregion

    //region Constructors & Initialization
    @Inject
    public UsersAdapter(BaseActivity context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usersCollection = Collections.emptyList();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public int getItemCount() {
        return (this.usersCollection != null) ? this.usersCollection.size() : 0;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        final User userModel = this.usersCollection.get(position);
        holder.textViewTitle.setText(userModel.getFullName());
        holder.itemView.setOnClickListener(v -> {
            if (UsersAdapter.this.onItemClickListener != null) {
                UsersAdapter.this.onItemClickListener.onUserItemClicked(userModel);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //endregion

    //region Methods
    private void validateUsersCollection(Collection<User> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
    //endregion

    //region Inner and Anonymous Classes
    public interface OnItemClickListener {
        void onUserItemClicked(User userModel);
    }
    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.title)
        TextView textViewTitle;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //endregion

    //region Getter & Setter
    public void setUsersCollection(Collection<User> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.usersCollection = (List<User>) usersCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //endregion

}
