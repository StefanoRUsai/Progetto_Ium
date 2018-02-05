package com.example.st3.coinquiapp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.st3.coinquiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.location.Location.convert;

public class Utenze extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private static  HashMap<String, String> stringHashMap= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenze);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listView = (ListView) findViewById(R.id.listViewUtenze);

        stringHashMap.put("Luce", "15/01/2018");
        stringHashMap.put("Gas", "18/01/2018");
        stringHashMap.put("Acqua", "22/03/2018");
        stringHashMap.put("Telefono", "13/02/2018");


        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter= new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[] {"First", "Second"},
                new int[] {R.id.row1, R.id.row2});

        Iterator it = stringHashMap.entrySet().iterator();
        while (it.hasNext()){

            HashMap<String,String> resultsMap = new HashMap<>();
            Map.Entry pair=(Map.Entry)it.next();
            resultsMap.put("First", pair.getKey().toString());
            resultsMap.put("Second", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new  Intent(Utenze.this, UtenzeText.class);
                intent.putExtra("immagineUtenza", listView.getItemAtPosition(i).toString());
                startActivity(intent);



            }
        });
        listView.setAdapter(adapter);


        FloatingActionButton mShowDialog = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Utenze.this);
                View mView = getLayoutInflater().inflate(R.layout.utenze_dialog, null);
                final EditText mUtenza = (EditText) mView.findViewById(R.id.utenza);
                final EditText mData = (EditText) mView.findViewById(R.id.data);
                Button mButton = (Button) mView.findViewById(R.id.aggiungi_utenza);


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mUtenza.getText().toString().isEmpty() && !mData.getText().toString().isEmpty()){
                            Toast.makeText(Utenze.this, R.string.check_aggiunto, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Utenze.this, R.string.error_campi, Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new  Intent(view.getContext(), Utenze.class);
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

    public void onClick3(View view){
        Intent intent = new  Intent(getBaseContext(), UtenzeText.class);
        startActivity(intent);
    }
}