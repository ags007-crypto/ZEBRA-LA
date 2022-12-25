package com.firstapp.zebra;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class Couponnumber extends AppCompatActivity {
private final String Letters="ABCDEFGHIJKLMNOPQRST";
private final String num="0123456789";
TextView txtview,cpnum,txtview2;
private char[] alphanumeric=(Letters+Letters.toLowerCase(Locale.ROOT)+num).toCharArray();
    public String generatecoupon(int length){
        StringBuilder result=new StringBuilder();
        for (int i=0;i<length;i++)
        {
            result.append(alphanumeric[new Random().nextInt(alphanumeric.length)]);
        }
        return result.toString();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couponnumber);
        txtview=findViewById(R.id.textView);
        cpnum=findViewById(R.id.cpnum);
        txtview2=findViewById(R.id.txtview2);


        GENERATE();


    }
    private void GENERATE(){
        Couponnumber couponnumber=new Couponnumber();
        String result=couponnumber.generatecoupon(6);
        txtview.setText("Registered Successfully!!");
        txtview2.setText("CONGRATZ! Since you are one of the top" +
                " 100 applicants, you received a Gift Coupon for Spoken English." +
                " Use the below Gift Coupon Number to avail 80% discount on ZEBRA's Spoken English Training.");
    cpnum.setText(result);}
}