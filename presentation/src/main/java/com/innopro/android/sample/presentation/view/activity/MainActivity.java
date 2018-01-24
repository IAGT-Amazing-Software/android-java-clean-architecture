package com.innopro.android.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.HasComponent;
import com.innopro.android.sample.presentation.internal.di.components.ApplicationComponent;
import com.innopro.android.sample.presentation.internal.di.components.DaggerMainComponent;
import com.innopro.android.sample.presentation.internal.di.components.MainComponent;
import com.innopro.android.sample.presentation.internal.di.modules.ApplicationModule;
import com.innopro.android.sample.presentation.model.CategoryModel;
import com.innopro.android.sample.presentation.model.TokenModel;
import com.innopro.android.sample.presentation.model.UserModel;
import com.innopro.android.sample.presentation.presenter.TokenPresenter;
import com.innopro.android.sample.presentation.view.TokenView;
import com.innopro.android.sample.presentation.view.fragment.MessageCategoryFragment;
import com.innopro.android.sample.presentation.view.fragment.UserListFragment;
import com.innopro.android.sample.presentation.view.fragment.UserLoggedFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity with navigation drawer
 */
public class MainActivity extends BaseActivity implements HasComponent<MainComponent>,
        MessageCategoryFragment.MessageCategoryListener, UserListFragment.UserListListener,TokenView {

    //region Constants
    private static final String TAG = MainActivity.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R2.id.navigation_view_menu)
    NavigationView navigationView;
    @BindView(R2.id.toolbar_main)
    Toolbar toolbar;

    private MainComponent mainComponent;

    @Inject
    TokenPresenter tokenPresenter;

    //endregion

    //region Constructors & Initialization
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout_main);

        ButterKnife.bind(this);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);

        tokenPresenter.setView(this);
    }


    private void initializeInjector() {
        this.mainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {

        //Listener can be replace with a lambda
        navigationView.setNavigationItemSelectedListener((MenuItem menuItem) ->{
            switch (menuItem.getItemId()) {
                case R.id.action_messages:
                    navigator.navigateToMessageCategoryList(MainActivity.this);
                    break;
                case R.id.action_users:
                    navigator.navigateToUserList(MainActivity.this);
                    break;
                case R.id.token:
                    tokenPresenter.initialize();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        if (savedInstanceState == null) {
            addFragment(R.id.content_frame, new MessageCategoryFragment());
            View headerContainer = navigationView.inflateHeaderView(R.layout.header_layout);
            headerContainer.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.header_frame, new UserLoggedFragment())
                            .commit();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {

                }
            });
        }
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public MainComponent getComponent() {
        return mainComponent;
    }

    @Override
    public void onCategoryClicked(CategoryModel categoryModel) {
        this.navigator.navigateToMessageList(this, categoryModel.getCategoryId());
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        this.navigator.navigateToUserDetails(this, userModel.getUserId());
    }

    @Override
    public void renderToken(TokenModel token) {
        Toast.makeText(this,"Token: "+ token.getValue(), Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public Context context() {
        return null;
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
