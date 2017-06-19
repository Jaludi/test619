package com.example.android.greateapp.DB;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContactsContract {

    /**
     * Is the name for the entire ContentProvider
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.greateapp";

    /**
     *
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     *
     */
    public static final String PATH_CONTACTS = "contacts";

    /**
     *
     */


    /**
     *
     */

    public static final class contactEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONTACTS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_CONTACTS;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_CONTACTS;


        public static final String TABLE_NAME = "contactsTABLE";
        public static final String COLUMN_NAME = "contactName";
        public static final String COLUMN_IMG = "imgURL";
        public static final String COLUMN_EMAIL = "contactEmail";
        public static final String COLUMN_ADDRESS = "contactAddress";


        public static Uri buildGenreUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
