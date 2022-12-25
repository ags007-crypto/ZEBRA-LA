package com.firstapp.zebra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firstapp.zebra.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    MessageAdapter messageAdapter;
    int a;String set,unread_count;
    String receiverId, idget = "0";long unreadcount,unreadcount_r;
    ArrayList<String> list = new ArrayList<>();
    int sno = 0, check;int i=0;
    DatabaseReference databaseReferenceSender, databaseReferenceReceiver;
    String senderRoom, receiverRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setBackgroundDrawableResource(R.drawable.chatbg);


        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        receiverId = getIntent().getStringExtra("id");
        String uname = getIntent().getStringExtra("uname");

        if (FirebaseAuth.getInstance().getUid().equals("kRCIzKRnqggA3SKPMPfwhZvFaA62")) {
            getSupportActionBar().setTitle("Chatting with " + uname);
        }
        senderRoom = FirebaseAuth.getInstance().getUid() + receiverId;
        receiverRoom = receiverId + FirebaseAuth.getInstance().getUid();
        messageAdapter = new MessageAdapter(this);
        binding.recycler.setAdapter(messageAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        databaseReferenceSender = FirebaseDatabase.getInstance().getReference("chats").child(senderRoom);


        databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference("chats").child(receiverRoom);

        databaseReferenceSender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageAdapter.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                    messageAdapter.add(messageModel);
                    idget = messageModel.getMsgId();
                    binding.recycler.scrollToPosition(messageAdapter.getItemCount() - 1);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Query query = databaseReferenceSender.orderByChild("read").equalTo("False");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                unreadcount = snapshot.getChildrenCount() + 1;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query rquery = databaseReferenceReceiver.orderByChild("read").equalTo("False");
        rquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                unreadcount_r = snapshot.getChildrenCount() + 1;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//TO SEND MESSAGE

        binding.sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RECORD THE COUNT OF UNSEEN MESSAGE FOR TUTOR REFERRAL


                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).child("set").setValue("1");
                String message = binding.messageEd.getText().toString();
                Intent intent = new Intent(ChatActivity.this, UserAdapter.class);
                intent.putExtra("nowtextid", receiverId);
                if (message.trim().length() > 0) {
                    sendMessage1(message);
                    binding.messageEd.setText("");
                }
            }
        });


    }

    private void sendMessage1(String message) {

        sno = Integer.parseInt(idget);
        sno = sno + 1;
        String snId = String.valueOf(sno);

        //   String messageId= UUID.randomUUID().toString();
        MessageModel messageModel_s = new MessageModel(snId, FirebaseAuth.getInstance().getUid(), message, "False",String.valueOf(unreadcount));
        MessageModel messageModel_r = new MessageModel(snId, FirebaseAuth.getInstance().getUid(), message, "False",String.valueOf(unreadcount_r));


        messageAdapter.add(messageModel_s);
        databaseReferenceSender
                .child(snId)
                .setValue(messageModel_s);
        databaseReferenceReceiver
                .child(snId)
                .setValue(messageModel_r);

    }
    @Override
    public void onBackPressed()
    {
        String senderRoom,receiverRoom;

        DatabaseReference databaseReferenceSender,databaseReferenceReceiver;

        senderRoom= FirebaseAuth.getInstance().getUid()+receiverId;
        receiverRoom=receiverId+FirebaseAuth.getInstance().getUid();
        databaseReferenceSender= FirebaseDatabase.getInstance().getReference("chats").child(senderRoom);
        databaseReferenceReceiver= FirebaseDatabase.getInstance().getReference("chats").child(receiverRoom);
        databaseReferenceSender.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String id = dataSnapshot.getKey();
                    databaseReferenceSender.child(id).child("read").setValue("True");

                }}




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });finish();
    }

@Override
public void onStop() {

    super.onStop();
    String senderRoom, receiverRoom;

    DatabaseReference databaseReferenceSender, databaseReferenceReceiver;

    senderRoom = FirebaseAuth.getInstance().getUid() + receiverId;
    receiverRoom = receiverId + FirebaseAuth.getInstance().getUid();
    databaseReferenceSender = FirebaseDatabase.getInstance().getReference("chats").child(senderRoom);
    databaseReferenceReceiver = FirebaseDatabase.getInstance().getReference("chats").child(receiverRoom);
    databaseReferenceSender.addListenerForSingleValueEvent(new ValueEventListener() {

        @Override

        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                String id = dataSnapshot.getKey();
                databaseReferenceSender.child(id).child("read").setValue("True");

            }
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    finish();

}


}