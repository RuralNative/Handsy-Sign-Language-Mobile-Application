package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseMethodHelper {
    //CLASS ATTRIBUTES
    private Context classContext;
    private final DatabaseHelper database = new DatabaseHelper(classContext);
    private final SQLiteDatabase readableDatabase = database.getReadableDatabase();
    private final SQLiteDatabase writableDatabase = database.getWritableDatabase();
    private final String defaultUpdateReturnStatement = "UPDATED NUMBER OF COLUMNS: ";

    //CLASS CONSTRUCTOR
    public DatabaseMethodHelper() {
    }
    public DatabaseMethodHelper(Context instanceContext) {
        this.classContext = instanceContext;
    }

    //CLASS BEHAVIOR
    //Select all data from a certain row by ID
    public String[] selectRowDataWithID(String tableName, int id) {
        String[] data = null;
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        try {
            Cursor cursor = readableDatabase.query(tableName, null, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                data = new String[cursor.getColumnCount()];
                for (int loopCount = 0; loopCount < cursor.getColumnCount(); loopCount++) {
                    data[loopCount] = cursor.getString(loopCount);
                }
                cursor.close();
            }
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        readableDatabase.close();
        return data;
    }
    //Select data from a certain column by ID
    public String getColumnDataByID(String tableName, String tableColumn, String ID) {
        String result = "";
        String[] columns = {tableColumn};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(ID)};
        try {
            Cursor cursor = readableDatabase.query(tableName, columns, selection, selectionArgs,
                    null, null, null);
            int columnIndex = cursor.getColumnIndex(tableColumn);
            if (columnIndex >= 0 && cursor.moveToFirst()) {
                result = cursor.getString(columnIndex);
            }
            cursor.close();
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        readableDatabase.close();
        return result;
    }
    //Insert integer data to ID of a specific table
    public String insertID(Integer value, String tableName) {
        long newRowId = 0;
        ContentValues values = new ContentValues();
        values.put("id", value);
        try {
            newRowId = writableDatabase.insert(tableName, null, values);
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        writableDatabase.close();
        return defaultUpdateReturnStatement + newRowId;
    }
    //Insert integer data to a certain table column by ID
    public String insertIntegerColumnDataByID(Integer value, String tableName, String tableColumn, String ID) {
        long numRowsInserted = 0;
        Integer integerValue = value;
        String whereClause = "id = ?";
        String[] whereArgs = {ID};
        ContentValues values = new ContentValues();
        values.put(tableColumn, integerValue);
        try {
            numRowsInserted = writableDatabase.insert(tableName, null, values);
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        }
        readableDatabase.close();
        return defaultUpdateReturnStatement + numRowsInserted;
    }
    //Insert integer data to a certain table column by ID
    public String insertTextColumnDataByID(String value, String tableName, String tableColumn, String ID) {
        long numRowsInserted = 0;
        String textValue = value;
        String whereClause = "id = ?";
        String[] whereArgs = {ID};
        ContentValues values = new ContentValues();
        values.put(tableColumn, textValue);
        try {
            numRowsInserted = writableDatabase.insert(tableName, null, values);
        } catch (SQLiteException sqliteexception) {
            sqliteexception.printStackTrace();
        }
        readableDatabase.close();
        return defaultUpdateReturnStatement + numRowsInserted;
    }
    //Update with a certain integer value the data in a certain table column of specific ID
    public void updateIntegerColumnDataByID(Integer newValue, String tableName, String columnName, int ID) {
        ContentValues values = new ContentValues();
        values.put(columnName, newValue);
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(ID)};
        try {
            writableDatabase.update(tableName, values, whereClause, whereArgs);
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        writableDatabase.close();
    }
    //Update with a certain String value the data in a certain table column of specific ID
    public void updateTextColumnDataByID(String newValue, String tableName, String columnName, int ID) {
        ContentValues values = new ContentValues();
        values.put(columnName, newValue);
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(ID)};
        try {
            writableDatabase.update(tableName, values, whereClause, whereArgs);
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        writableDatabase.close();
    }
}
