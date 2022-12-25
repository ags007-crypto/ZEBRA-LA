package com.firstapp.zebra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class display extends AppCompatActivity {
    DatabaseReference databaseReference;
    UserAdapter userAdapter; RecyclerView recycler;
ArrayList<UserModel> list; long unreadc,aaa,userscount;String unreadmsg,useridget,aa;
    String tutorid="kRCIzKRnqggA3SKPMPfwhZvFaA62";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        list = new ArrayList<>();
recycler = findViewById(R.id.recycler);
        userAdapter=new UserAdapter(this,list);
        recycler.setAdapter(userAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
getSupportActionBar().setTitle("Chat Now");
//TO RECORD UNREAD COUNT FOR THE ID yajySROQmZZJcDRwHqY44IQ42dF2
       DatabaseReference databaseReference0= FirebaseDatabase.getInstance().getReference("users");


                    String userid="yajySROQmZZJcDRwHqY44IQ42dF2";
                 String room="kRCIzKRnqggA3SKPMPfwhZvFaA62"+userid;
                    DatabaseReference databaseReferenceSender = FirebaseDatabase.getInstance().getReference("chats").child(room);
                    Query query = databaseReferenceSender.orderByChild("read").equalTo("False");
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                            long unreadcount= snapshot1.getChildrenCount();
                            databaseReference0.child(userid).child("unreadcount").setValue(String.valueOf(unreadcount));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//TO RECORD UNREAD COUNT FOR THE ID dKr6JTv4wHMkSnPOEZXSIMUkzXh2
        DatabaseReference databaseReference1= FirebaseDatabase.getInstance().getReference("users");

        String userid1="dKr6JTv4wHMkSnPOEZXSIMUkzXh2";
        String room1="kRCIzKRnqggA3SKPMPfwhZvFaA62"+userid1;
        DatabaseReference databaseReferenceSender1 = FirebaseDatabase.getInstance().getReference("chats").child(room1);
        Query query1 = databaseReferenceSender1.orderByChild("read").equalTo("False");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long unreadcount1= snapshot.getChildrenCount();
                databaseReference1.child(userid1).child("unreadcount").setValue(String.valueOf(unreadcount1));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child("kRCIzKRnqggA3SKPMPfwhZvFaA62").child("fZoBb9flsygcroIzllteQU72KxD3").setValue("2");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userAdapter.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String uid = dataSnapshot.getKey();
                    if(!FirebaseAuth.getInstance().getUid().equals("kRCIzKRnqggA3SKPMPfwhZvFaA62")){
                        UserModel userModel = dataSnapshot.getValue(UserModel.class);
                        if(userModel.getUserEmail().equals("tutor@zebralanguageacademy.com")){
                            list.add(userModel);
                        }

                    }else{

                        if(!uid.equals(FirebaseAuth.getInstance().getUid())) {

                            UserModel userModel = dataSnapshot.getValue(UserModel.class);
                            list.add(userModel);}


                        }}

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==R.id.logout){
           AlertDialog.Builder alert = new AlertDialog.Builder(display.this);
           alert.setTitle("Logout");
           alert.setMessage("Are you sure you want to Logout?");
           alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {
                   FirebaseAuth.getInstance().signOut();
                   startActivity(new Intent(display.this,AuthenticationActivity.class));
                   finish();             dialog.dismiss();
                   Toast.makeText(getApplicationContext(), "Logged out successfully!", Toast.LENGTH_SHORT).show();

               }
           });

           alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
               }
           });

           alert.show();

  return true;
       }
return false;    }

    @Override
    public void onBackPressed() {

        logout();


    }

    private void logout(){
        AlertDialog.Builder alert = new AlertDialog.Builder(display.this);
        alert.setTitle("Logout");
        alert.setMessage("Are you sure you want to Logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();

                Intent intent=new Intent(display.this,tutorlist.class);

                startActivity(intent);     finish();           dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Logged out successfully!", Toast.LENGTH_SHORT).show();

            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();


    }

}






