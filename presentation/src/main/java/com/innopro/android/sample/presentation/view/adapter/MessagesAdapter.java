package com.innopro.android.sample.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.R2;
import com.innopro.android.sample.presentation.model.MessageModel;
import com.innopro.android.sample.presentation.view.activity.BaseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link MessageModel}.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

  public interface OnItemClickListener {
    void onMessageItemClicked(MessageModel messageModel);
  }

  private List<MessageModel> messagesCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject
  public MessagesAdapter(BaseActivity context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.messagesCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.messagesCollection != null) ? this.messagesCollection.size() : 0;
  }

  @Override public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_message, parent, false);
    return new MessageViewHolder(view);
  }

  @Override public void onBindViewHolder(MessageViewHolder holder, final int position) {
    final MessageModel messageModel = this.messagesCollection.get(position);
    holder.textViewName.setText(messageModel.getName());
    holder.itemView.setOnClickListener(v -> {
      if (MessagesAdapter.this.onItemClickListener != null) {
        MessagesAdapter.this.onItemClickListener.onMessageItemClicked(messageModel);
      }
    });
  }

  @Override public long getItemId(int position) {
    return this.messagesCollection.get(position).getMessageId();
  }

  public void setMessagesCollection(Collection<MessageModel> messagesCollection) {
    this.validateMessagesCollection(messagesCollection);
    this.messagesCollection = (List<MessageModel>) messagesCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateMessagesCollection(Collection<MessageModel> messagesCollection) {
    if (messagesCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class MessageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.name) TextView textViewName;

    public MessageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
