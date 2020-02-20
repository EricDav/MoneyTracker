package com.example.david.moneytracker.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andeladeveloper on 02/01/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "expenses_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Expense.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Expense.TABLE_NAME);
    }

    public void insertExpense(Integer timeSpecified, Integer expenseType, String amount,
                              String expense, String note, String date, String businessName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Expense.COLUMN_AMOUNT, amount);
        values.put(Expense.COLUMN_BUSINESS_NAME, businessName);
        values.put(Expense.COLUMN_DATE, date);
        values.put(Expense.COLUMN_EXPENSE_TYPE, expenseType);
        values.put(Expense.COLUMN_EXPENSE, expense);
        values.put(Expense.COLUMN_NOTE, note);
        values.put(Expense.COLUMN_TIME_SPECIFIED, timeSpecified);

        db.insert(Expense.TABLE_NAME, null, values);

        db.close();

    }
}
