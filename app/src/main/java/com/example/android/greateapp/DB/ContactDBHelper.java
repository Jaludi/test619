package com.example.android.greateapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        addContactsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addContactsTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ContactsContract.contactEntry.TABLE_NAME + " (" +
                        ContactsContract.contactEntry._ID + " INTEGER PRIMARY KEY, " +
                        ContactsContract.contactEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        ContactsContract.contactEntry.COLUMN_ADDRESS + " TEXT UNIQUE NOT NULL, " +
                        ContactsContract.contactEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        ContactsContract.contactEntry.COLUMN_IMG + " TEXT UNIQUE NOT ULL);"
        );
    }
}

