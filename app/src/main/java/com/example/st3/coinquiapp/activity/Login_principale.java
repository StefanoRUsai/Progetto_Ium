package com.example.st3.coinquiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.st3.coinquiapp.R;

public class Login_principale extends AppCompatActivity {

    //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_principale );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //per logo
        //toolbar.setNavigationIcon( R.mipmap.logo );
        // bottoneLogin

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.coinquiapp_logo2 );
        //getSupportActionBar().setIcon(@drawable/my_logo);
        Button buttonSignIn = findViewById( R.id.buttonNewAccount );

        buttonSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getBaseContext(), Registrazione.class);
                startActivity(intent);
            }
        } );
    }

     public void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick10(View view){
        Intent intent = new  Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }





}
