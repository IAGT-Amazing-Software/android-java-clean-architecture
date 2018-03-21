package com.innopro.android.sample.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.UserComponent;
import com.innopro.android.sample.presentation.presenter.UserDetailsPresenter;
import com.innopro.android.sample.presentation.view.UserDetailsView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain user.
 */
@FragmentWithArgs
public class UserDetailsFragment extends BaseFragment implements UserDetailsView {
    //region Constants
    private static final String TAG = UserDetailsFragment.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.iv_cover)
    ImageView iv_cover;
    @BindView(R2.id.tv_fullname)
    TextView tv_fullname;
    @BindView(R2.id.tv_email)
    TextView tv_email;
    @BindView(R2.id.tv_followers)
    TextView tv_followers;
    @BindView(R2.id.tv_description)
    TextView tv_description;
    @BindView(R2.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R2.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R2.id.bt_retry)
    Button bt_retry;

    @Arg
    private int userId;

    @Inject
    UserDetailsPresenter userDetailsPresenter;
    //endregion

    //region Constructors & Initialization
    public UserDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public void onResume() {
        super.onResume();
        this.userDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userDetailsPresenter.destroy();
    }

    @Override
    public void renderUser(User user) {
        if (user != null) {
            Picasso.with(getContext()).load(user.getCoverUrl()).fit().into(this.iv_cover);
            this.tv_fullname.setText(user.getFullName());
            this.tv_email.setText(user.getEmail());
            this.tv_followers.setText(String.valueOf(user.getFollowers()));
            this.tv_description.setText(user.getDescription());
        }
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
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
    //endregion

    //region Methods
    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.userDetailsPresenter != null) {
            this.userDetailsPresenter.initialize(userId);
        }
    }

    @OnClick(R2.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    /**
     * Variable Setters
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    //endregion

}
