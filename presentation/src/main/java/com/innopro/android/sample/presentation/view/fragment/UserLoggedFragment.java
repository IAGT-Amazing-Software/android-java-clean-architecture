package com.innopro.android.sample.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.MainComponent;
import com.innopro.android.sample.presentation.model.UserLoggedModel;
import com.innopro.android.sample.presentation.presenter.UserLoggedPresenter;
import com.innopro.android.sample.presentation.view.UserLoggedView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Fragment that shows details of a certain user.
 */
public class UserLoggedFragment extends BaseFragment implements UserLoggedView {

    //region Constants
    private static final String TAG = UserLoggedFragment.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.imageView_Avatar)
    ImageView imageView_Avatar;
    @BindView(R2.id.textView_Name)
    TextView textView_Name;

    @Inject
    UserLoggedPresenter userLoggedPresenter;
    //endregion

    //region Constructors & Initialization
    public UserLoggedFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.header_profile, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userLoggedPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserLogged();
        }
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public void onResume() {
        super.onResume();
        this.userLoggedPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userLoggedPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userLoggedPresenter.destroy();
    }

    @Override
    public void renderUserLogged(UserLoggedModel userLogged) {
        if (userLogged != null) {
            Log.d(getTag(), userLogged.getFullName());
            Picasso.with(getContext())
                    .load(userLogged.getAvatarUrl())
                    .transform(new CropCircleTransformation())
                    .fit()
                    .into(imageView_Avatar);
            this.textView_Name.setText(userLogged.getFullName());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {
    }

    @Override
    public void hideRetry() {

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
    private void loadUserLogged() {
        if (this.userLoggedPresenter != null) {
            this.userLoggedPresenter.initialize();
        }
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

    //@OnClick(R.id.bt_retry)
    //void onButtonRetryClick() {
    // UserLoggedFragment.this.loadUserLogged();
    //}

}
