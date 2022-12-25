package com.firstapp.zebra;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserModel> userModelList;
    public UserAdapter(Context context,ArrayList<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    public void add(UserModel userModel) {
        userModelList.add(userModel);
        notifyDataSetChanged();
    }

    public void clear() {
        userModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserModel userModel=userModelList.get(position);
if(userModel.getUserId().equals("kRCIzKRnqggA3SKPMPfwhZvFaA62")){
    holder.img.setImageResource(R.drawable.tut3);
}else{holder.img.setImageResource(R.drawable.person);}
        if(!userModel.getSet().equals("0")){
holder.name.setText(userModel.getUserName());
holder.email.setText(userModel.getUserEmail());
holder.unread_count.setText(userModel.getUnreadcount());
    holder.unread_count.setText(userModel.getUnreadcount());
        holder.unreadbg.setVisibility(View.VISIBLE);

           }

        else{
            holder.name.setText(userModel.getUserName());
            holder.email.setText(userModel.getUserEmail());
            holder.unread_count.setText(userModel.getUnreadcount());
            holder.unread_count.setText(userModel.getUnreadcount());
            holder.unreadbg.setVisibility(View.INVISIBLE);

            }

        holder.itemView.setOnClickListener(v -> {
           String senderRoom,receiverRoom;
            String receiverId;
            receiverId=userModel.getUserId();
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
            });

    if(userModel.getUserId().equals("kRCIzKRnqggA3SKPMPfwhZvFaA62")){
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Connecting to ZEBRA Tutor...");
        pd.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                pd.dismiss();
                Intent intent=new Intent(context,ChatActivity.class);
                intent.putExtra("id",userModel.getUserId());
                intent.putExtra("uname",userModel.getUserName());

                context.startActivity(intent);
                Toast.makeText(context.getApplicationContext(), "Connected Successfully!", Toast.LENGTH_SHORT).show();

            }
        }, 5000);

    }else{
        //FOR HIGHLIGHTING THE NEW MESSAGE
        if(FirebaseAuth.getInstance().getUid().equals("kRCIzKRnqggA3SKPMPfwhZvFaA62")){
            FirebaseDatabase.getInstance().getReference("users").child(userModel.getUserId()).child("set").setValue("0");



        }
            Intent intent=new Intent(context,ChatActivity.class);

        intent.putExtra("id",userModel.getUserId());
            intent.putExtra("uname",userModel.getUserName());


        context.startActivity(intent);

        }});
    }



    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, email,unread_count;private ImageView unreadbg,img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            unread_count=itemView.findViewById(R.id.unread_count);
            unreadbg=itemView.findViewById(R.id.unreadbg);
            img=itemView.findViewById(R.id.imgperson);


        }
    }
}
