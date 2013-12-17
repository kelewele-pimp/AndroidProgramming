package db_package;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class ToDoContentProvider extends ContentProvider {

    // Publish the URI for this provider.This URI will be used to
    // access this Content Provider from within other application components
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.desmond.todoprovider/todoitems");

    // Create public static variables that define the column names.
    public static final String KEY_ID = "_id";
    public static final String KEY_TASK = "task";
    public static final String KEY_CREATION_DATE = "creation_date";

    private MySQLiteOpenHelper myOpenHelper;

    // Create UriMatcher to allow the content provider to differentiate
    // between a query against the entire table and one that addresses
    // a particular row.
    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;
    private static final UriMatcher uriMatcher;

    // Populate the UriMatcher object , where a URI ending in 'todoitems'
    // will correspond to a request for all items, and 'todoitems/[rowID]'
    // represents a single row
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.desmond.todoprovider", "todoitems", ALLROWS);
        uriMatcher
                .addURI("com.desmond.todoprovider", "todoitems/#", SINGLE_ROW);
    }

    @Override
    public boolean onCreate() {
        // Construct the underlying database.
        // Defer opening the database until you need to perform
        // a query or transaction

        myOpenHelper = new MySQLiteOpenHelper(getContext(),
                MySQLiteOpenHelper.DATABASE_NAME, null,
                MySQLiteOpenHelper.DATABASE_VERSION);

        return true;
    }

    // Use it within the getType handler to return the correct MIME type,
    // depending on the query type
    @Override
    public String getType(Uri uri) {
        // Return a string that identifies the MIME type
        // for a Content Provider URI
        switch (uriMatcher.match(uri)) {
            case ALLROWS:
                return "vnd.android.cursor.dir/vnd.desmond.todos";
            case SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.desmond.todos";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Open a read-only database
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // Replace these with valid SQL statements if necessary
        String groupBy = null;
        String having = null;

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MySQLiteOpenHelper.DATABASE_TABLE);

        // If this is a row query, limit the result set to the passed in row
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(KEY_ID + "=" + rowID);
            default:
                break;
        }
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, groupBy, having, sortOrder);

        return cursor;
    }

    // Implement the delete,insert, and update methods using the
    // same approach - pass through the received parameters while
    // handling the special case of single-rows URIs

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Open a read/write database to support the transaction
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // To add empty rows to your database by passing in an empty Content
        // Values
        // object, you must use the null column hack parameter to specify the
        // name of
        // the column that can be set to null
        String nullColumnHack = null;

        // Insert the values into the table
        long id = db.insert(MySQLiteOpenHelper.DATABASE_TABLE, nullColumnHack,
                values);

        if (id > -1) {
            // Construct and return the URI of the newly inserted row.
            Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, id);

            // Notify any observers of the change in the data set.
            getContext().getContentResolver().notifyChange(insertedId, null);

            return insertedId;
        } else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Open a read/write database to support the transaction
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // If this is a row URI, limit the deletion to a specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                selection = KEY_ID
                        + "="
                        + rowID
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
                        + ')' : " ");
            default:
                break;
        }

        // To return the number of deleted items, you must specify a where
        // clause.
        // To delete all rows and return values, pass in "1".
        if (selection == null)
            selection = "1";

        // Execute the deletion.
        int deleteCount = db.delete(MySQLiteOpenHelper.DATABASE_TABLE,
                selection, selectionArgs);

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // Open a read/write database to support the transaction
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // If this is a row URI, limit the deletion to a specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                selection = KEY_ID
                        + "="
                        + rowID
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
                        + ')' : " ");
            default:
                break;
        }

        // Perform the update
        int updateCount = db.update(MySQLiteOpenHelper.DATABASE_TABLE, values,
                selection, selectionArgs);

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        return updateCount;
    }

    private static class MySQLiteOpenHelper extends SQLiteOpenHelper {

        // Create variables to store the database name and version
        // along with the table name
        private static final String DATABASE_NAME = "todoDatabase.db";
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_TABLE = "todoItemTable";

        // SQL statement to create a new database
        private static final String DATABASE_CREATE = "create table "
                + DATABASE_TABLE + " (" + KEY_ID
                + " integer primary key autoincrement, " + KEY_TASK
                + " text not null, " + KEY_CREATION_DATE + " long);";

        public MySQLiteOpenHelper(Context context, String name,
                                  CursorFactory factory, int databaseVersion) {
            super(context, name, factory, databaseVersion);
        }

        // Called when no database exists in disk and the helper
        // class needs to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Called when there is a database version mismatch the the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log the version upgrade
            Log.w("TaskDBAdapter", "Upgrading from version" + oldVersion
                    + " to " + newVersion + " which will destrot all old data");

            // Upgrade the existing database to conform to the new version.
            // Multiple previous versions can be handled by comparing oldVersion
            // and newVersion values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
            // create a new one.
            onCreate(db);
        }

    }

}
