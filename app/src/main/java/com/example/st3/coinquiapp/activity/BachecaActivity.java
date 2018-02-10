package com.example.st3.coinquiapp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.st3.coinquiapp.R;

public class BachecaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacheca);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton mShowDialog = (FloatingActionButton) findViewById(R.id.floatingAggiungiPost);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BachecaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.post_it_dialog, null);
                final EditText mTitolo_post_it = (EditText) mView.findViewById(R.id.post_it_titolo);
                final EditText mTesto_post_it = (EditText) mView.findViewById(R.id.post_it_testo_da_inserire);
                Button mButton = (Button) mView.findViewById(R.id.aggiungi_post_it);


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mTitolo_post_it.getText().toString().isEmpty() && !mTesto_post_it.getText().toString().isEmpty()){
                            Toast.makeText(BachecaActivity.this, R.string.check_aggiunto, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BachecaActivity.this, R.string.error_campi, Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new  Intent(view.getContext(), BachecaActivity.class);
                        startActivity(intent);

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
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
}
