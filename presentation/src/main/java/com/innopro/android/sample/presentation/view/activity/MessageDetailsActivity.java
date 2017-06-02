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
import com.innopro.android.sample.presentation.view.fragment.MessageDetailsFragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that shows details of a certain message.
 */
public class MessageDetailsActivity extends BaseActivity implements HasComponent<MessageComponent> {

    private static final String INTENT_EXTRA_PARAM_MESSAGE_ID = "com.innopro.INTENT_PARAM_MESSAGE_ID";
    private static final String INSTANCE_STATE_PARAM_MESSAGE_ID = "com.innopro.STATE_PARAM_MESSAGE_ID";

    public static Intent getCallingIntent(Context context, int messageId) {
        Intent callingIntent = new Intent(context, MessageDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_MESSAGE_ID, messageId);
        return callingIntent;
    }

    private int messageId;
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
            outState.putInt(INSTANCE_STATE_PARAM_MESSAGE_ID, this.messageId);
        }
        super.onSaveInstanceState(outState);
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
            this.messageId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_MESSAGE_ID, -1);
            addFragment(R.id.content_frame, new MessageDetailsFragmentBuilder(this.messageId).build());
        } else {
            this.messageId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_MESSAGE_ID);
        }
    }

    private void initializeInjector() {
        this.messageComponent = DaggerMessageComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public MessageComponent getComponent() {
        return messageComponent;
    }
}
