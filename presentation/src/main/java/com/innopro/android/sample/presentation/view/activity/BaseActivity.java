package com.innopro.android.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.innopro.android.sample.presentation.AndroidApplication;
import com.innopro.android.sample.presentation.internal.di.components.ApplicationComponent;
import com.innopro.android.sample.presentation.internal.di.modules.ActivityModule;
import com.innopro.android.sample.presentation.navigation.Navigator;
import com.innopro.android.sample.presentation.view.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Replace a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;

        for (int i = 0; i < fragmentList.size() && !handled; i++) {
            Fragment f = fragmentList.get(i);
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPressed();
            } else {
                Log.w(TAG, "onBackPressed: All fragment should extend from BaseFragment");
            }
        }
        if (!handled) {
            super.onBackPressed();
        }
    }

}
