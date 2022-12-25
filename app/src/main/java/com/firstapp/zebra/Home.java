package com.firstapp.zebra;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;

public class Home extends AppCompatActivity {
Button tutor,student,btnabout,btnfinalassign,btntutorlist,btnchecktutor;
TextView address;
long number= 918595509389L;
LinearLayout glow;VideoView videoview;
AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        glow=(LinearLayout)findViewById(R.id.glow);
        btnchecktutor=findViewById(R.id.btnJoin_details);
        address=findViewById(R.id.address);
       // tutor=(Button) findViewById(R.id.btnTutor);
        btnabout=(Button) findViewById(R.id.btnAbout);
        btnfinalassign=(Button)findViewById(R.id.btnFinal_Assignment);
        btntutorlist=(Button)findViewById(R.id.btnTutor_list);





        animatorSet=new AnimatorSet();
        ObjectAnimator fadeout=ObjectAnimator.ofFloat(glow,"alpha",1.5f,0.5f);
        fadeout.setDuration(500);
        ObjectAnimator fadein=ObjectAnimator.ofFloat(glow,"alpha",0.5f,1.5f);
        fadein.setDuration(500);
        animatorSet.play(fadein).after(fadeout);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
        animatorSet.start();

       // address.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {
           //     String url = null;
             //   url = "https://www.google.com/maps/place/ZEBRA+Language+Academy+-+Online+Spoken+English,+Spoken+Hindi,+Spoken+Arabic,+Spoken+Urdu/@13.0551556,80.2146572,15z/data=!4m12!1m6!3m5!1s0x0:0x4a7bc1f9092518e1!2sZEBRA+Language+Academy+-+Online+Spoken+English,+Spoken+Hindi,+Spoken+Arabic,+Spoken+Urdu!8m2!3d13.0551556!4d80.2234119!3m4!1s0x0:0x4a7bc1f9092518e1!8m2!3d13.0551556!4d80.2234119?hl=en";
                //  String url = "https://api.whatsapp.com/send?phone="+number;
               // Intent i = new Intent(Intent.ACTION_VIEW);
                //i.setData(Uri.parse(url));
                //startActivity(i);
            //}
       // });


       // tutor.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View view) {
      //          Intent i = new Intent(Home.this,registrationform.class);
     //           startActivity(i);
    //        }
    //    });
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,webview.class);
                startActivity(i);
            }
        });
        btnfinalassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Videostudents.class);
                startActivity(i);
            }
        });
        btntutorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, tutorlist.class);
                startActivity(i);
            }
        });
        btnchecktutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Tuto_langselect.class);
                startActivity(i);
            }
        });



    }

}