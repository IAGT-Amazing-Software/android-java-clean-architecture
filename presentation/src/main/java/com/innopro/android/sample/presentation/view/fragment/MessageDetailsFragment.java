package com.innopro.android.sample.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.MessageComponent;
import com.innopro.android.sample.presentation.presenter.MessageDetailsPresenter;
import com.innopro.android.sample.presentation.view.MessageDetailsView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain user.
 */
@FragmentWithArgs
public class MessageDetailsFragment extends BaseFragment implements MessageDetailsView {

    //region Constants
    private static final String TAG = MessageDetailsFragment.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.iv_image)
    ImageView iv_image;
    @BindView(R2.id.tv_title)
    TextView tv_name;
    @BindView(R2.id.tv_description)
    TextView tv_description;
    @BindView(R2.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R2.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R2.id.bt_retry)
    Button bt_retry;
    @BindView(R.id.tv_toolBar_title)
    TextView tv_toolBar_title;
    @BindView(R.id.appbarLayout)
    AppBarLayout appBarLayout;

    @Arg
    private int messageId;

    @Inject
    MessageDetailsPresenter messageDetailsPresenter;

    //endregion

    //region Constructors & Initialization
    public MessageDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
        this.getComponent(MessageComponent.class).inject(this);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_message_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.messageDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMessageDetails();
        }

        tv_toolBar_title.setAlpha(0f);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset == - appBarLayout.getTotalScrollRange()){
                    tv_toolBar_title.setAlpha(1f);
                }else{
                    tv_toolBar_title.setAlpha(0f);
                }
            }
        });
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public void onResume() {
        super.onResume();
        this.messageDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.messageDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.messageDetailsPresenter.destroy();
    }

    @Override
    public void renderMessage(Message message) {
        if (message != null) {
            Picasso.with(getContext()).load(message.getImageUrl()).fit().into(this.iv_image);
            this.tv_name.setText(message.getName());
            tv_toolBar_title.setText(message.getName());
            this.tv_description.setText(message.getDescription());
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
    //endregion

    //region Methods
    /**
     * Loads all message.
     */
    private void loadMessageDetails() {
        if (this.messageDetailsPresenter != null) {
            this.messageDetailsPresenter.initialize(messageId);
        }
    }

    @OnClick(R2.id.bt_retry)
    void onButtonRetryClick() {
        MessageDetailsFragment.this.loadMessageDetails();
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter
    /**
     * variable sets
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
    //endregion



}
