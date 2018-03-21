package com.innopro.android.sample.presentation.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.innopro.android.sample.presentation.R;

/**
 * Main application screen. This is the app entry point.
 */
public class SplashActivity extends BaseActivity {
    //region Constants
    private static final String TAG = SplashActivity.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> navigator.navigateToSlide(this), 2000);
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
