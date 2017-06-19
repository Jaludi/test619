package com.example.android.greateapp.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ContactsProvider extends ContentProvider {

    private static final int CONTACT = 100;
    private static final int CONTACT_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private ContactDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new ContactDBHelper(getContext());
        return true;
    }

    public static UriMatcher buildUriMatcher() {
        String content = ContactsContract.CONTENT_AUTHORITY;

        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(content, ContactsContract.PATH_CONTACTS, CONTACT);
        matcher.addURI(content, ContactsContract.PATH_CONTACTS+"/#",CONTACT_ID);

        return matcher;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACT:
                return ContactsContract.contactEntry.CONTENT_TYPE;
            case CONTACT_ID:
                return ContactsContract.contactEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor returnCursor;
        switch (uriMatcher.match(uri)) {
            case CONTACT:
                returnCursor = db.query(
                        ContactsContract.contactEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CONTACT_ID:
                long _id = ContentUris.parseId(uri);
                returnCursor = db.query(
                        ContactsContract.contactEntry.TABLE_NAME,
                        projection,
                        ContactsContract.contactEntry._ID + " =?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch (uriMatcher.match(uri)) {
            case CONTACT:
                _id = db.insert(ContactsContract.contactEntry.TABLE_NAME,null,values);
                if(_id > 0) {
                    returnUri = ContactsContract.contactEntry.buildGenreUri(_id);
                } else {
                    throw new UnsupportedOperationException("Unable to insert row into: " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch (uriMatcher.match(uri)) {
            case CONTACT:
                rows = db.delete(ContactsContract.contactEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(selection == null || rows != 0 ) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch (uriMatcher.match(uri)) {
            case CONTACT:
                rows = db.update(ContactsContract.contactEntry.TABLE_NAME, values, selection, selectionArgs);;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
