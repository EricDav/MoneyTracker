package com.example.david.moneytracker.Database;

/**
 * Created by andeladeveloper on 02/01/2019.
 */

public class Expense {
    public static final String TABLE_NAME = "Expenses";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIME_SPECIFIED = "time_specified";
    public static final String COLUMN_EXPENSE_TYPE = "expense_type";
    public static final String COLUMN_BUSINESS_NAME = "business_name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EXPENSE + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_AMOUNT + " TEXT,"
            + COLUMN_NOTE + " TEXT,"
            + COLUMN_TIME_SPECIFIED + " INTEGER,"
            + COLUMN_EXPENSE_TYPE + " INTEGER,"
            + COLUMN_BUSINESS_NAME + " TEXT"
            + ")";

    private Integer id;
    private String amount;
    private String expense;
    private String note;
    private String date;
    private Integer timeSpecified;
    private Integer expenseType;
    private String businessName;

    public Expense(Integer id, Integer timeSpecified, Integer expenseType, String amount, String expense, String note, String date, String businessName) {
        this.id = id;
        this.timeSpecified =  timeSpecified;
        this.expenseType = expenseType;
        this.amount = amount;
        this.expense = expense;
        this.note =  note;
        this.date = date;
        this.businessName = businessName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public void setTimeSpecified(Integer timeSpecified) {
        this.timeSpecified = timeSpecified;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setExpenseType(Integer expenseType) {
        this.expenseType = expenseType;
    }

    public String getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getExpense() {
        return expense;
    }

    public Integer getExpenseType() {
        return expenseType;
    }

    public Integer getTimeSpecified() {
        return timeSpecified;
    }

    public String getAmount() {
        return amount;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getNote() {
        return note;
    }
}
