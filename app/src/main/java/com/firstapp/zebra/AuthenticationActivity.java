package com.firstapp.zebra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.zebra.databinding.ActivityAuthenticationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationActivity extends AppCompatActivity {
    ActivityAuthenticationBinding binding;
    String name,email,password; DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        binding=ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=binding.email.getText().toString();
                password=binding.password.getText().toString();
                login();

            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=binding.name.getText().toString();
                email=binding.email.getText().toString();
                password=binding.password.getText().toString();
                Pattern pattern1 = Pattern.compile( "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

                Matcher matcher1 = pattern1.matcher(email);
if(password.length()<6){
    Toast.makeText(getApplicationContext(), "Minimum 6 Characters required for Password", Toast.LENGTH_SHORT).show();

}else if(!matcher1.matches()) {
    Toast.makeText(getApplicationContext(), "Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                }else{
                signUp();}

            }
        });

    }
    private void login(){
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email.trim(),password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Enter Correct Email/Password", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(AuthenticationActivity.this,display.class));
                    finish();
                    }
                });
    }
    private void signUp(){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email.trim(),password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        UserModel userModel=new UserModel(FirebaseAuth.getInstance().getUid(),name,email,password,"0","0");
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);
                        startActivity(new Intent(AuthenticationActivity.this,display.class));
                        finish();
                    }
                });

    }
}
