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

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.MainComponent;
import com.innopro.android.sample.presentation.presenter.UserListPresenter;
import com.innopro.android.sample.presentation.view.UserListView;
import com.innopro.android.sample.presentation.view.adapter.UsersAdapter;
import com.innopro.android.sample.presentation.view.adapter.layoutmanager.UsersLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Users.
 */
public class UserListFragment extends BaseFragment implements UserListView {
    //region Constants
    private static final String TAG = UserListFragment.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.rv_users)
    RecyclerView rv_users;
    @BindView(R2.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R2.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R2.id.bt_retry)
    Button bt_retry;

    private UserListListener userListListener;

    private UsersAdapter.OnItemClickListener onItemClickListener =
            user -> {
                if (UserListFragment.this.userListPresenter != null && user != null) {
                    UserListFragment.this.userListPresenter.onUserClicked(user);
                }
            };

    @Inject
    UserListPresenter userListPresenter;
    @Inject
    UsersAdapter usersAdapter;
    //endregion

    //region Constructors & Initialization

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.attachListener();
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    @Override
    public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
        getActivity().setTitle(getResources().getString(R.string.activity_title_user_list));
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.userListListener = null;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderUserList(Collection<User> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);
        }
    }

    @Override
    public void viewUser(User userModel) {
        if (this.userListListener != null) {
            this.userListListener.onUserClicked(userModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }
    //endregion

    //region Methods
    public void attachListener() {
        try {
            this.userListListener = (UserListListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(UserListFragment.class.getName(), e.getLocalizedMessage());
        }
    }

    private void setupRecyclerView() {
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_users.setLayoutManager(new UsersLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.userListPresenter.initialize();
    }


    @OnClick(R2.id.bt_retry)
    void onButtonRetryClick() {
        UserListFragment.this.loadUserList();
    }
    //endregion

    //region Inner and Anonymous Classes
    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {
        void onUserClicked(final User user);
    }
    //endregion

    //region Getter & Setter

    //endregion
}
