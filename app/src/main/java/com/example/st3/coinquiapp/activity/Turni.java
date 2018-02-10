package com.example.st3.coinquiapp.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.st3.coinquiapp.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


public class Turni extends AppCompatActivity {

    private static CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "Turni";
    TextView mese;
    static String turnoStringa=null;
    static  long time;
    static Timestamp currentTimestamp;
    EditText mTurno;
    static boolean flag=false;
    static Calendar cal;
    static HashMap<Long, String> listaTime= new HashMap<>();
    static boolean inizializzazione=true;
    static ArrayList<Event> listaEvent = new ArrayList<>();


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
                mTurno = (EditText) mView.findViewById(R.id.turno);
                final TextView mData = (TextView) mView.findViewById(R.id.data_turno);
                Button mButton = (Button) mView.findViewById(R.id.aggiungi_turno);


                mData.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        cal = Calendar.getInstance(android.icu.util.TimeZone.GMT_ZONE);
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        time= cal.get(Calendar.MILLISECONDS_IN_DAY);

                        // 2) get a java.util.Date from the calendar instance.
                        //    this date will represent the current instant, or "now".


                        DatePickerDialog dialog = new DatePickerDialog(
                                Turni.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                mDateSetListener,
                                year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });

                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                        String date = month + "/" + day + "/" + year;
                        mData.setText(date);
                        Date datal= new Date(date);

                        currentTimestamp = new Timestamp(datal.getTime());
                        turnoStringa=mTurno.getText().toString();


                    }
                };



                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(!mTurno.getText().toString().isEmpty() && !mData.getText().toString().isEmpty()){
                            turnoStringa=mTurno.getText().toString();
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
                flag=true;
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


        if(flag == true) {
            long times= currentTimestamp.getTime();
            listaTime.put(times, turnoStringa);
            Event eventNew = new Event(Color.BLUE, times, turnoStringa);
            listaEvent.add(eventNew);
            for(Event event: listaEvent){
                compactCalendar.addEvent(event);
            }
        flag=false;
        }



        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.getTime() == 1517788800000L) {
                    Toast.makeText(context, "Turno piatti Alessandro", Toast.LENGTH_SHORT).show();
                } else {
                    if (dateClicked.getTime() == 1518134400000L) {
                        Toast.makeText(context, "Turno bagno Stefano", Toast.LENGTH_SHORT).show();
                    } else {

                        if (dateClicked.getTime() == 1518048000000L)  {
                            Toast.makeText(context, "Turno pulizie corridoio Marta", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dateClicked.getTime() == 1518220800000L) {
                                Toast.makeText(context, "Turno cucina Giorgia", Toast.LENGTH_SHORT).show();
                            } else {
                                if (listaTime.containsKey(dateClicked.getTime()) ){
                                    Toast.makeText(context, listaTime.get(dateClicked.getTime()), Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(context, "Non ci sono turni in questo momento", Toast.LENGTH_SHORT).show();
                                }

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
