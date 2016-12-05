package com.innopro.android.sample.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.view.activity.MainActivity;
import com.innopro.android.sample.presentation.view.activity.MessageDetailsActivity;
import com.innopro.android.sample.presentation.view.activity.MessageListActivity;
import com.innopro.android.sample.presentation.view.activity.UserDetailsActivity;
import com.innopro.android.sample.presentation.view.fragment.MessageCategoryFragment;
import com.innopro.android.sample.presentation.view.fragment.UserListFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
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

  /**
   * Goes to the user list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToUserList(MainActivity context) {
    if (context != null) {
      context.replaceFragment(R.id.content_frame, new UserListFragment());
    }
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

  /**
   * Goes to the message category list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToMessageCategoryList(MainActivity context) {
    if (context != null) {
      context.replaceFragment(R.id.content_frame, new MessageCategoryFragment());
    }
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
}
