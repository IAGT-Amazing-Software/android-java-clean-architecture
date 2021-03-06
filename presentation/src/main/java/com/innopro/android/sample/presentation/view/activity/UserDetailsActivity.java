package com.innopro.android.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.HasComponent;
import com.innopro.android.sample.presentation.internal.di.components.DaggerUserComponent;
import com.innopro.android.sample.presentation.internal.di.components.UserComponent;
import com.innopro.android.sample.presentation.view.fragment.UserDetailsFragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent> {
    //region Constants
    private static final String TAG = UserDetailsActivity.class.getSimpleName();

    private static final String INTENT_EXTRA_PARAM_USER_ID = TAG+"INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = TAG+"STATE_PARAM_USER_ID";
    //endregion

    //region Fields
    @BindView(R2.id.toolbar_main)
    Toolbar toolbar;

    private int userId;
    private UserComponent userComponent;
    //endregion

    //region Constructors & Initialization
    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, UserDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        ButterKnife.bind(this);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listener can be replace with a lambda
        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());

        if (savedInstanceState == null) {
            this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
            addFragment(R.id.content_frame, new UserDetailsFragmentBuilder(this.userId).build());
        } else {
            this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter
    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
    //endregion
}
