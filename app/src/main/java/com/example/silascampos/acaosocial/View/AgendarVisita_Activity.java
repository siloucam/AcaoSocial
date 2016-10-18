package com.example.silascampos.acaosocial.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.silascampos.acaosocial.Model.Visita;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.Fragments.TimePickerFragment;

@SuppressWarnings("deprecation")
public class AgendarVisita_Activity extends AppCompatActivity{
    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_visita);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_frame);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText(i2 + " / " + i1 + " / " + i);
            }
        });

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("nome");
        TextView wordToGuess = (TextView) findViewById(R.id.textView5);
        wordToGuess.setText(value);
    }

    public void showTimePickerDialog(View v){
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void AgendarVisita(String data, String hora, String instituicao, int n_pessoas){
        Visita v = new Visita(instituicao,data,hora,n_pessoas);
    }

}
