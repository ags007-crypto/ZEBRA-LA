package com.firstapp.zebra;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class tutorlist extends AppCompatActivity {
RecyclerView recyclerView; Button btnchatnow;
recycler_adapter adapter; AnimatorSet animatorSet;
String tutname[]={"Rohini Deshpande","Shalini Gupta","Sheela Patel ","Preet Grewal","Sakshi Kapoor","Jyothi Singh"};
String tutdetails[]={"I am from Mumbai. I have 10+ years of experience in teaching Hindi,English,German to people from different states and countries.",
        "I am from New Delhi. I have 7+ years of experience in teaching Hindi and English to people from different states and countries.",
        "I am from Kanpur. I love learning languages. I know Hindi,Arabic,Spanish,French,English.",
        "I am from Delhi. I am addicted to language learning. All through these years I have cracked how to learn a language fastly.",
        "I am from Ludhiana. I know Punjabi,Hindi,English and Russian. I love to learn languages and I love to share this wisdom to the people.",
        "I am from Bhopal. I have 2+ years of experience in teaching Hindi,English,Korean."};
float rate[] ={5,4.5f,4.5f,5,4.5f,4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutrefractor);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new recycler_adapter(this,tutname,tutdetails,rate);
        recyclerView.setAdapter(adapter);
        LinearLayout chatglow=findViewById(R.id.chatglow);
        btnchatnow=findViewById(R.id.btnchatnow);

        animatorSet=new AnimatorSet();
        ObjectAnimator fadeout=ObjectAnimator.ofFloat(chatglow,"alpha",1.5f,0.5f);
        fadeout.setDuration(350);
        ObjectAnimator fadein=ObjectAnimator.ofFloat(chatglow,"alpha",0.5f,1.5f);
        fadein.setDuration(350);
        animatorSet.play(fadein).after(fadeout);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
        animatorSet.start();
        btnchatnow.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(tutorlist.this,AuthenticationActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed() {
        super.onBackPressed();



    }



}