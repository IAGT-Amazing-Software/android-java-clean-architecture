package com.innopro.android.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.irozon.library.HideKey;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen. This is the app entry point.
 */
public class LoginActivity extends BaseActivity {
    //region Constants
    private static final String TAG = LoginActivity.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        HideKey.initialize(this);
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    /**
     * Goes to the message list screen.
     */
    @OnClick(R2.id.btn_Login)
    void navigateToMessageList() {
        this.navigator.navigateToMain(this);
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
