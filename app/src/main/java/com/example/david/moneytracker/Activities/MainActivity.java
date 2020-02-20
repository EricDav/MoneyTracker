package com.example.david.moneytracker.Activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.moneytracker.Database.DatabaseHelper;
import com.example.david.moneytracker.Fragments.ConfirmationDialogFragment;
import com.example.david.moneytracker.Fragments.DatePickerFragment;
import com.example.david.moneytracker.Fragments.TimePickerFragment;
import com.example.david.moneytracker.R;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private TextView mTextMessage;
    EditText expenseEditText;
    EditText amountEditText;
    EditText noteEditText;
    EditText dateEditText;
    EditText timeEditText;
    private DatabaseHelper db;
    private int position; //current view showing in the main activity
    View personalExpenses;
    View viewExpenses;
    LinearLayout businessWrapperLinearLayout;

    public  int year = -1;
    public int month = -1;
    public int day = -1;
    public int hour = -1;
    public int min = -1;

    public int dateTag = 1;

    public  int fromYear = -1;
    public int fromMonth = -1;
    public int fromDay = -1;

    public  int toYear = -1;
    public int toMonth = -1;
    public int toDay = -1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_personal_expenses:
                    personalExpenses.setVisibility(View.VISIBLE);
                    viewExpenses.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_business_expenses:
                    mTextMessage.setText(R.string.title_view);
                    return true;
                case R.id.navigation_view_expenses:
                    personalExpenses.setVisibility(View.INVISIBLE);
                    viewExpenses.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.save:
                String expense = expenseEditText.getText().toString();
                String note = noteEditText.getText().toString();
                String amount = amountEditText.getText().toString();

                if (expense.equals("")  || amount.equals("") || year == -1 || month == -1 || day == -1) {
                    Toast.makeText(this, "Fill all required details", Toast.LENGTH_SHORT).show();
                } else {
                    String date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + "-" + Integer.toString(hour) + Integer.toString(min);
                    db.insertExpense(1, 0, amount, expense, note, date, null);
                    resetState();
                    DialogFragment newFragment = new ConfirmationDialogFragment();
                    newFragment.show(getFragmentManager(), "missiles");
                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        position = 1;

        personalExpenses = findViewById(R.id.personal_expense_fragment);
        viewExpenses = findViewById(R.id.view_expense_fragment);
        businessWrapperLinearLayout = findViewById(R.id.businessWrapperId);
        businessWrapperLinearLayout.setVisibility(View.GONE);

        db = new DatabaseHelper(this);

        expenseEditText = findViewById(R.id.expenseId);
        amountEditText = findViewById(R.id.amountId);
        noteEditText = findViewById(R.id.noteId);
        timeEditText = findViewById(R.id.timeId);
        dateEditText = findViewById(R.id.dateId);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewExpenses.setVisibility(View.GONE);

        timeEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("dateType", "expenseDate");

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(data);
                newFragment.setCancelable(false);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        displaySpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 1) {
            businessWrapperLinearLayout.setVisibility(View.VISIBLE);
        } else {
            businessWrapperLinearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void displaySpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.expenseTypeId);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void resetState() {
        amountEditText.setText("");
        expenseEditText.setText("");
        noteEditText.setText("");
        dateEditText.setText("");
        timeEditText.setText("");
    }

    public void setTime(int hour, int min) {
        this.hour = hour;
        this.min = min;

    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setDateTag(int dateTag) {
        this.dateTag = dateTag;
    }

}
