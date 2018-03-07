package com.innopro.android.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopro.android.sample.domain.Message;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link Message}.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {
    //region Constants
    private static final String TAG = MessagesAdapter.class.getSimpleName();
    //endregion

    //region Fields
    private List<Message> messagesCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;
    //endregion

    //region Constructors & Initialization
    @Inject
    public MessagesAdapter(BaseActivity context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messagesCollection = Collections.emptyList();
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public int getItemCount() {
        return (this.messagesCollection != null) ? this.messagesCollection.size() : 0;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, final int position) {
        final Message messageModel = this.messagesCollection.get(position);
        holder.textViewName.setText(messageModel.getName());
        holder.itemView.setOnClickListener(v -> {
            if (MessagesAdapter.this.onItemClickListener != null) {
                MessagesAdapter.this.onItemClickListener.onMessageItemClicked(messageModel);
            }
        });
    }
    @Override
    public long getItemId(int position) {
        return this.messagesCollection.get(position).getMessageId();
    }
    //endregion

    //region Methods
    private void validateMessagesCollection(Collection<Message> messagesCollection) {
        if (messagesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
    //endregion

    //region Inner and Anonymous Classes
    public interface OnItemClickListener {
        void onMessageItemClicked(Message messageModel);
    }
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.name)
        TextView textViewName;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //endregion

    //region Getter & Setter
    public void setMessagesCollection(Collection<Message> messagesCollection) {
        this.validateMessagesCollection(messagesCollection);
        this.messagesCollection = (List<Message>) messagesCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //endregion
}
