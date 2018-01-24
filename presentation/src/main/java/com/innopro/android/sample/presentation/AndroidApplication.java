package com.innopro.android.sample.presentation;

import android.app.Application;

import com.innopro.android.sample.presentation.internal.di.components.ApplicationComponent;
import com.innopro.android.sample.presentation.internal.di.components.DaggerApplicationComponent;
import com.innopro.android.sample.presentation.internal.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {
    //region Constants
    private static final String TAG = AndroidApplication.class.getSimpleName();
    //endregion

    //region Fields
    private ApplicationComponent applicationComponent;
    //endregion

    //region Constructors & Initialization
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
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
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
    //endregion

}
