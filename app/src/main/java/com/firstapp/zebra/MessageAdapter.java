package com.firstapp.zebra;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<MessageModel> messageModelList;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    public MessageAdapter(Context context) {
        this.context = context;
        messageModelList = new ArrayList<>();
    }

    public void add(MessageModel messageModel) {
        messageModelList.add(messageModel);
        notifyDataSetChanged();
    }

    public void clear() {
        messageModelList.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    @NonNull
    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        MessageModel messageModel = messageModelList.get(position);

        if (messageModel.getSenderId().equals(FirebaseAuth.getInstance().getUid())) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row2, parent, false);
        return new SentMessageHolder(view);}
        else if(viewType == VIEW_TYPE_MESSAGE_RECEIVED){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row, parent, false);
        return new ReceivedMessageHolder(view);}
        return null;

        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel=messageModelList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).msg2.setText(messageModel.getMessage());

                ((SentMessageHolder) holder).msg2.setTextColor(context.getResources().getColor(R.color.white));                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).msg.setText(messageModel.getMessage());

                ((ReceivedMessageHolder) holder).msg.setTextColor(context.getResources().getColor(R.color.white));        }

    }
    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView msg2; LinearLayout main2;

        SentMessageHolder(View itemView) {
            super(itemView);

            msg2 = (TextView) itemView.findViewById(R.id.message2);
            main2 = (LinearLayout) itemView.findViewById(R.id.mainMessageLayout2);
        }}

        private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
            TextView msg; LinearLayout main;
            ReceivedMessageHolder(View itemView) {
                super(itemView);

                msg = (TextView) itemView.findViewById(R.id.message);
                main = (LinearLayout) itemView.findViewById(R.id.mainMessageLayout);
            }
        }






}

