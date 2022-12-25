package com.firstapp.zebra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registrationform extends AppCompatActivity {
    TextView name, place, lang, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button register = findViewById(R.id.btnRegister);
        name = findViewById(R.id.name);
        email = findViewById(R.id.inputEmail);
        place = findViewById(R.id.place);
        lang = findViewById(R.id.lang);
        RadioGroup gender = findViewById(R.id.gender);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (name.getText().toString().equals("") || email.getText().toString().equals("") || gender.getCheckedRadioButtonId()==-1 || place.getText().toString().equals("") || lang.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Kindly enter all the fields!", Toast.LENGTH_SHORT).show();

                    }else{
                        ProgressDialog pd = new ProgressDialog(registrationform.this);
                        pd.setMessage("Registering....");
                        pd.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                pd.dismiss();
                                Intent i = new Intent(registrationform.this, Couponnumber.class);
                                startActivity(i);
                            }
                        }, 4000);
                  }
                }
            });
        }
    }
