package com.example.st3.coinquiapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.st3.coinquiapp.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Turni extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    TextView mese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turni);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mese = (TextView) findViewById(R.id.textView3);
        String meseAttuale = dateFormatMonth.format(new Date());
        mese.setText(meseAttuale);

        FloatingActionButton mShowDialog = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Turni.this);
                View mView = getLayoutInflater().inflate(R.layout.turni_dialog, null);
                final EditText mTurno = (EditText) mView.findViewById(R.id.turno);
                final EditText mData = (EditText) mView.findViewById(R.id.data_turno);
                Button mButton = (Button) mView.findViewById(R.id.aggiungi_turno);


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mTurno.getText().toString().isEmpty() && !mData.getText().toString().isEmpty()){
                            Toast.makeText(Turni.this, R.string.check_aggiunto, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Turni.this, R.string.error_campi, Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new  Intent(view.getContext(), Turni.class);
                        startActivity(intent);

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);


        Event ev1 = new Event(Color.BLUE, 1517788800000L, "Turno piatti Alessandro");
        Event ev2 = new Event(Color.YELLOW, 1518048000000L, "Turno bagno Stefano");
        Event ev3 = new Event(Color.GREEN, 1518134400000L, "Turno pulizie corridoio Marta");
        Event ev4 = new Event(Color.BLUE, 1518220800000L, "Turno cucina Giorgia");
        compactCalendar.addEvent(ev1);
        compactCalendar.addEvent(ev2);
        compactCalendar.addEvent(ev3);
        compactCalendar.addEvent(ev4);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Mon Feb 05 00:00:00 GMT+00:00 2018") == 0) {
                    Toast.makeText(context, "Turno piatti Alessandro", Toast.LENGTH_SHORT).show();
                } else {
                    if (dateClicked.toString().compareTo("Fri Feb 09 00:00:00 GMT+00:00 2018") == 0) {
                        Toast.makeText(context, "Turno bagno Stefano", Toast.LENGTH_SHORT).show();
                    } else {

                        if (dateClicked.toString().compareTo("Sat Feb 10 00:00:00 GMT+00:00 2018") == 0) {
                            Toast.makeText(context, "Turno pulizie corridoio Marta", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dateClicked.toString().compareTo("Thu Feb 08 00:00:00 GMT+00:00 2018") == 0) {
                                Toast.makeText(context, "Turno cucina Giorgia", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Non ci sono turni in questo momento", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Turni.this);
                                View mView = getLayoutInflater().inflate(R.layout.turni_dialog, null);
                                final EditText mTurno = (EditText) mView.findViewById(R.id.turno);
                                final EditText mData = (EditText) mView.findViewById(R.id.data_turno);
                                Button mButton = (Button) mView.findViewById(R.id.aggiungi_turno);


                                mButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(!mTurno.getText().toString().isEmpty() && !mData.getText().toString().isEmpty()){
                                            Toast.makeText(Turni.this, R.string.check_aggiunto, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(Turni.this, R.string.error_campi, Toast.LENGTH_SHORT).show();
                                        }
                                        Intent intent = new  Intent(view.getContext(), Turni.class);
                                        startActivity(intent);
                                    }
                                });

                                mBuilder.setView(mView);
                                AlertDialog dialog = mBuilder.create();
                                dialog.show();
                            }
                        }
                    }
                }
            }
                @Override
                public void onMonthScroll (Date firstDayOfNewMonth){
                    mese.setText(dateFormatMonth.format(firstDayOfNewMonth));
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
