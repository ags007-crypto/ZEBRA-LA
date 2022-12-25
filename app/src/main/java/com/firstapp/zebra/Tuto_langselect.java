package com.firstapp.zebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Tuto_langselect extends AppCompatActivity {
Button btnse,btnsh,btnst;
    long number= 918595509389L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_langselect);
        btnse=findViewById(R.id.btnTutor_se);
        btnsh=findViewById(R.id.btnTutor_sh);
        btnst=findViewById(R.id.btnTutor_st);

        btnse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = null;
                try {
                    url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode("Hi ZEBRA Language Academy. Checking availability for SPOKEN ENGLISH. My name is ", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //  String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btnsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = null;
                try {
                    url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode("Hi ZEBRA Language Academy. Checking availability for SPOKEN HINDI. My name is ", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //  String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btnst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = null;
                try {
                    url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode("Hi ZEBRA Language Academy. Checking availability for Arabic Tutor. My name is ", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //  String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}