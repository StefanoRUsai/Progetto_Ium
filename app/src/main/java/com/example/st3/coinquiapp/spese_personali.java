package com.example.st3.coinquiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;

public class spese_personali extends AppCompatActivity {

    private ListView listaSP;
    private ArrayAdapter<String> listAdapter;
    private HashMap<String, String> stringHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spese_personali);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listaSP = (ListView) findViewById(R.id.listViewSpesePersonali);

        stringHashMap.put("Coprimaterasso", "12,50");
        stringHashMap.put("Lampadina camera","2,30");
        stringHashMap.put("Bagnoschiuma","1,90");

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter= new SimpleAdapter(this, listItems, R.layout.layoutlistasp,
                new String[] {"First", "Second"},
                new int[] {R.id.riga1, R.id.riga2});

        Iterator it = stringHashMap.entrySet().iterator();
        while (it.hasNext()){

            HashMap<String,String> resultsMap = new HashMap<>();
            Map.Entry pair=(Map.Entry)it.next();
            resultsMap.put("First", pair.getKey().toString());
            resultsMap.put("Second", pair.getKey().toString());
            listItems.add(resultsMap);
        }

        listaSP.setAdapter(adapter);
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
