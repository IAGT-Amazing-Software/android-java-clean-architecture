package com.innopro.android.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.HasComponent;
import com.innopro.android.sample.presentation.internal.di.components.DaggerMessageComponent;
import com.innopro.android.sample.presentation.internal.di.components.MessageComponent;
import com.innopro.android.sample.presentation.model.MessageModel;
import com.innopro.android.sample.presentation.view.fragment.MessageListFragment;
import com.innopro.android.sample.presentation.view.fragment.MessageListFragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity with navigation drawer
 */
public class MessageListActivity extends BaseActivity implements HasComponent<MessageComponent>,
        MessageListFragment.MessageListListener {

    private static final String INTENT_EXTRA_PARAM_CATEGORY_ID = "com.innopro.INTENT_PARAM_CATEGORY_ID";
    private static final String INSTANCE_STATE_PARAM_CATEGORY_ID = "com.innopro.STATE_PARAM_CATEGORY_ID";


    public static Intent getCallingIntent(Context context, int categoryId) {
        Intent callingIntent = new Intent(context, MessageListActivity.class);
        callingIntent.putExtra(INSTANCE_STATE_PARAM_CATEGORY_ID, categoryId);
        return callingIntent;
    }


    private int categoryId;
    private MessageComponent messageComponent;
    @BindView(R2.id.toolbar_main)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout_main);

        ButterKnife.bind(this);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(INSTANCE_STATE_PARAM_CATEGORY_ID, this.categoryId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeInjector() {
        this.messageComponent = DaggerMessageComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Listener can be replace with a lambda
        toolbar.setNavigationOnClickListener((View v)-> onBackPressed());


        if (savedInstanceState == null) {
            this.categoryId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_CATEGORY_ID, -1);
            addFragment(R.id.content_frame, new MessageListFragmentBuilder(this.categoryId).build());
        } else {
            this.categoryId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_CATEGORY_ID);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public MessageComponent getComponent() {
        return messageComponent;
    }

    @Override
    public void onMessageClicked(MessageModel messageModel) {
        this.navigator.navigateToMessageDetails(this, messageModel.getMessageId());
    }
}
