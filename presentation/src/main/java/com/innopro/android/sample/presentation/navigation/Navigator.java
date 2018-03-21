package com.innopro.android.sample.presentation.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.view.activity.LoginActivity;
import com.innopro.android.sample.presentation.view.activity.MainActivity;
import com.innopro.android.sample.presentation.view.activity.MessageDetailsActivity;
import com.innopro.android.sample.presentation.view.activity.MessageListActivity;
import com.innopro.android.sample.presentation.view.activity.SliderActivity;
import com.innopro.android.sample.presentation.view.activity.SplashActivity;
import com.innopro.android.sample.presentation.view.activity.UserDetailsActivity;
import com.innopro.android.sample.presentation.view.fragment.MessageCategoryFragment;
import com.innopro.android.sample.presentation.view.fragment.UserListFragment;

/**
 * Class used to navigate through the application.
 */
public class Navigator {

    public Navigator() {
        //empty
    }

    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Goes to main screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToMain(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }


    public void navigateToUserList() {
        mainActivity.replaceFragment(R.id.content_frame, new UserListFragment());
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserDetails(Context context, int userId) {
        if (context != null) {
            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userId);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToMessageCategoryList() {
        mainActivity.replaceFragment(R.id.content_frame, new MessageCategoryFragment());
    }

    /**
     * Goes to the message list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToMessageList(MainActivity context, int categoryId) {
        if (context != null) {
            Intent intentToLaunch = MessageListActivity.getCallingIntent(context, categoryId);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the message details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToMessageDetails(Context context, int messageId) {
        if (context != null) {
            Intent intentToLaunch = MessageDetailsActivity.getCallingIntent(context, messageId);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the login screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToLogin(Activity context) {
        if (context != null) {
            Intent intentToLaunch = LoginActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
            context.finish();
        }
    }

    public void navigateToSplash(SliderActivity sliderActivity) {
        Intent i = new Intent(sliderActivity, SplashActivity.class);
        sliderActivity.startActivity(i);
        sliderActivity.finish();
    }

    public void navigateToSlide(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent(activity, SliderActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
