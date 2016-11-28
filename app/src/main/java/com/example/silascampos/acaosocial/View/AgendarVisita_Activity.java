package com.example.silascampos.acaosocial.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silascampos.acaosocial.Model.Visita;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.Fragments.TimePickerFragment;
import com.example.silascampos.acaosocial.db.DAO;

import java.io.IOException;

@SuppressWarnings("deprecation")
public class AgendarVisita_Activity extends Lifecycle{
    CalendarView calendarView;
    TextView dateDisplay;
    private AlertDialog alerta;

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

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Agendar", "At this time:");
        Log.i("Agendar", user.getName());
        Log.i("Agendar", Integer.toString((int) user.getId()));
    }

    public void showTimePickerDialog(View v){
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void AgendarVisita(View v){
        TextView i = (TextView) findViewById(R.id.textView5);
        Button data = (Button) findViewById(R.id.date_frame);
        Button hora = (Button) findViewById(R.id.timepick);
        EditText n_pessoas = (EditText) findViewById(R.id.editText);

        String t_i = i.getText().toString();
        String t_data = data.getText().toString();
        String t_hora = hora.getText().toString();
        String n_pessoas_t = n_pessoas.getText().toString();

        DAO dao = null;
        try {
            dao = new DAO(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.putVisita(t_i,t_data,t_hora,n_pessoas_t,user.getId());

        dialog_confirmacao();
    }

    private void dialog_confirmacao() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Confirmação");
        //define a mensagem
        builder.setMessage("Solicitação de Agendamento Enviada!");
        //define um botão como positivo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(AgendarVisita_Activity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

}
