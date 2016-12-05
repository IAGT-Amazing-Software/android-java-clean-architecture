package com.innopro.android.sample.presentation.view.activity;

import android.os.Bundle;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.innopro.android.sample.presentation.R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    /**
     * Goes to the message list screen.
     */
    @OnClick(R2.id.btn_Login)
    void navigateToMessageList() {
        this.navigator.navigateToMain(this);
    }

}
