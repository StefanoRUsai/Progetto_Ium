package com.example.st3.coinquiapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.st3.coinquiapp.R;

public class SpashScreen extends AppCompatActivity {
    int timeout = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SpashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, timeout);

    }





}
