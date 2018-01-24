package com.innopro.android.sample.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.internal.di.components.MessageComponent;
import com.innopro.android.sample.presentation.model.MessageModel;
import com.innopro.android.sample.presentation.presenter.MessageListPresenter;
import com.innopro.android.sample.presentation.view.MessageListView;
import com.innopro.android.sample.presentation.view.adapter.MessagesAdapter;
import com.innopro.android.sample.presentation.view.adapter.layoutmanager.MessagesLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Message.
 */
@FragmentWithArgs
public class MessageListFragment extends BaseFragment implements MessageListView {
    //region Constants
    private static final String TAG = MessageListFragment.class.getSimpleName();
    //endregion

    //region Fields
    @BindView(R2.id.rv_messages)
    RecyclerView rv_messages;
    @BindView(R2.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R2.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R2.id.bt_retry)
    Button bt_retry;

    @Arg
    private int categoryId;
    private MessageListListener messageListListener;

    private MessagesAdapter.OnItemClickListener onItemClickListener =
            messageModel -> {
                if (MessageListFragment.this.messageListPresenter != null && messageModel != null) {
                    MessageListFragment.this.messageListPresenter.onMessageClicked(messageModel);
                }
            };

    @Inject
    MessageListPresenter messageListPresenter;
    @Inject
    MessagesAdapter messagesAdapter;
    //endregion

    //region Constructors & Initialization
    public MessageListFragment() {
        setRetainInstance(true);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachListener();
        FragmentArgs.inject(this);
        this.getComponent(MessageComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_message_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.messageListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMessageList();
        }
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    @Override
    public void onResume() {
        super.onResume();
        this.messageListPresenter.resume();
        getActivity().setTitle(getResources().getString(R.string.activity_title_message_list));
    }

    @Override
    public void onPause() {
        super.onPause();
        this.messageListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_messages.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.messageListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.messageListListener = null;
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
    public void renderMessageList(Collection<MessageModel> messageModelCollection) {
        if (messageModelCollection != null) {
            this.messagesAdapter.setMessagesCollection(messageModelCollection);
        }
    }

    @Override
    public void viewMessage(MessageModel messageModel) {
        if (this.messageListListener != null) {
            this.messageListListener.onMessageClicked(messageModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }
    //endregion

    //region Methods
    public void attachListener() {
        try {
            this.messageListListener = (MessageListListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(MessageListFragment.class.getName(), e.getLocalizedMessage());
        }
    }

    private void setupRecyclerView() {
        this.messagesAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_messages.setLayoutManager(new MessagesLayoutManager(context()));
        this.rv_messages.setAdapter(messagesAdapter);
    }

    /**
     * Loads all messages.
     */
    private void loadMessageList() {
        this.messageListPresenter.initialize();
    }

    @OnClick(R2.id.bt_retry)
    void onButtonRetryClick() {
        MessageListFragment.this.loadMessageList();
    }

    //endregion

    //region Inner and Anonymous Classes
    /**
     * Interface for listening message list events.
     */
    public interface MessageListListener {
        void onMessageClicked(final MessageModel messageModel);
    }
    //endregion

    //region Getter & Setter

    /**
     * Variable sets
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    //endregion

}
