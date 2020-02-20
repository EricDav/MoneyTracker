package com.example.david.moneytracker.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.david.moneytracker.Activities.MainActivity;
import com.example.david.moneytracker.R;

import java.util.Calendar;

/**
 * Created by andeladeveloper on 03/01/2019.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        MainActivity main = (MainActivity) getActivity();
        main.setTime(hourOfDay, minute);
        // Do something with the time chosen by the user
        TextView tv = (TextView) getActivity().findViewById(R.id.timeId);
        tv.setText(formatTime(hourOfDay, minute));
    }

    public String formatTime(int hour, int minute) {
        if (hour == 0) {
            if (minute == 0) {
                return  "12am";
            } else {
                return "12:" + minute + "am";
            }
        }
        else if (hour < 12) {
            if (minute == 0) {
                return Integer.toString(hour) + "am";
            } else {
                return Integer.toString(hour) + ":" + minute + "am";
            }
        } else {
            if (minute == 0) {
                return Integer.toString(hour) + "pm";
            } else {
                return Integer.toString(hour) + ":" + minute + "pm";
            }
        }
    }

}


