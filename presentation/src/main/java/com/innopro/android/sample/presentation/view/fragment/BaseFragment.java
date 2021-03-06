package com.innopro.android.sample.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.innopro.android.sample.presentation.AndroidApplication;
import com.innopro.android.sample.presentation.internal.di.HasComponent;
import com.innopro.android.sample.presentation.internal.di.components.ApplicationComponent;
import com.innopro.android.sample.presentation.navigation.Navigator;
import com.innopro.android.sample.presentation.utils.SharedPreferencesManager;

import javax.inject.Inject;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

    @Inject
    Navigator navigator;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AndroidApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    //region Constants
    private static final String TAG = BaseFragment.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    /**
     * Calculated Status bar height for dimanic views
     *
     * @return statusBar in pixels
     */
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Calculated ActionBar height for dinamic view
     *
     * @return Action bar height in pixels
     */
    protected int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv,
                true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, getResources().getDisplayMetrics());
        return actionBarHeight;
    }

    /**
     * Transform dp to pixels based in dpi
     *
     * @param dipValue dp value for transform
     * @return pixels from dp
     */
    protected int transformToPixel(float dipValue) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    /**
     * Get total height from the device screen
     *
     * @return Device screen in pixels
     */
    protected int getCalculatedHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (displayMetrics.heightPixels - getStatusBarHeight() - getActionBarHeight());
    }

    /**
     * Force to hide the keyboard
     */
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public boolean onBackPressed(){
        return false;
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
