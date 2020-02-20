package com.example.david.moneytracker.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.moneytracker.Activities.MainActivity;
import com.example.david.moneytracker.R;

import java.util.Calendar;

/**
 * Created by andeladeveloper on 03/01/2019.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        String dateType = ((String) getArguments().getString("dateType"));

        MainActivity main = (MainActivity) getActivity();
        main.setDate(year, month, day);

        String[] monthsOfTheYear = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String dateText = monthsOfTheYear[month] + " " + day + "," + " " + year;

        EditText expenseDateEditText = getActivity().findViewById(R.id.dateId);
        EditText fromDateEditText = getActivity().findViewById(R.id.fromId);
        EditText toDateEditText = getActivity().findViewById(R.id.toId);

        Log.d("TAG", dateType);

        if (dateType.equals("expenseDate")) {
            expenseDateEditText.setText(dateText);
            ((MainActivity) getActivity()).setDateTag(1);
        } else if (dateType.equals("fromDate")) {
            fromDateEditText.setText(dateText);
            ((MainActivity) getActivity()).setDateTag(2);
        } else {
            toDateEditText.setText(dateText);
            ((MainActivity) getActivity()).setDateTag(3);
        }
    }
}