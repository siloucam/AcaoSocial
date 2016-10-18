package com.example.silascampos.acaosocial.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.silascampos.acaosocial.R;

import java.util.Calendar;

/**
 * Created by Silas Campos on 17/10/2016.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.d("myTag", "This is my message");
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d("myTag", "Entrou!");
        Button btn = (Button)getActivity().findViewById(R.id.timepick);
        if(btn==null){
            Log.d("myTag", "Não achou!");
        }else{
            Log.d("myTag", "Achou alguma coisa!");
            btn.setText(hourOfDay + ":" + minute);
        }
    }
}
