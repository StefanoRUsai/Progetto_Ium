package com.example.st3.coinquiapp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.st3.coinquiapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Spese extends AppCompatActivity {
    private static TextView totale;
    private ListView listaS;
    private ArrayAdapter<String> listAdapter;
    private static LinkedHashMap<String, String> stringHashMap = new LinkedHashMap<String, String>(){
        { put("Scottex", "2,50€");
        put("Bombola cucina","22,30 €");
        put("Detersivo piatti","1,00 €");
        put("Tovaglia","11,00 €");}
    };
    private static float totalefloat = 35.80f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spese);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listaS = (ListView) findViewById(R.id.listViewSpesePersonali);
        totale = (TextView) findViewById(R.id.spesetotale);




        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter= new SimpleAdapter(this, listItems, R.layout.layoutlistasp,
                new String[] {"First", "Second"},
                new int[] {R.id.riga1, R.id.riga2});

        Iterator it = stringHashMap.entrySet().iterator();
        while (it.hasNext()){

            HashMap<String,String> resultsMap = new HashMap<>();
            Map.Entry pair=(Map.Entry)it.next();
            resultsMap.put("First", pair.getKey().toString());
            resultsMap.put("Second", pair.getValue().toString());
            listItems.add(resultsMap);
        }
      
        totale.setText(String.valueOf((Float)totalefloat)+" €");
        listaS.setAdapter(adapter);


        FloatingActionButton mShowDialog = (FloatingActionButton) findViewById(R.id.floatingSpese);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Spese.this);
                View mView = getLayoutInflater().inflate(R.layout.spesec_dialog, null);
                final EditText mSpesa = (EditText) mView.findViewById(R.id.spesa);
                final EditText mPrezzo = (EditText) mView.findViewById(R.id.prezzo);
                Button mButton = (Button) mView.findViewById(R.id.aggiungi_spesa);


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String prezzo = null;
                        if(!mSpesa.getText().toString().isEmpty() && !mPrezzo.getText().toString().isEmpty()){
                            if (!(mPrezzo.getText().toString().contains(",")) && !(mPrezzo.getText().toString().contains("."))){
                                prezzo= mPrezzo.getText().toString()+",00";

                            }
                            if (mPrezzo.getText().toString().contains(".")){
                                prezzo= mPrezzo.getText().toString();
                                prezzo.replace(".", ",");

                            }
                            totalefloat+=Float.parseFloat(mPrezzo.getText().toString());
                            stringHashMap.put(mSpesa.getText().toString(),prezzo+" €");
                            Toast.makeText(Spese.this, R.string.check_aggiunto, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Spese.this, R.string.error_campi, Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new  Intent(view.getContext(), Spese.class);
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
