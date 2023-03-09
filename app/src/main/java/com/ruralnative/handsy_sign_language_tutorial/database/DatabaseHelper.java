package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper{
    //CLASS ATTRIBUTES
    private static final String DATABASE_NAME = "handsyDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase readableDatabase = getReadableDatabase();
    private final SQLiteDatabase writableDatabase = getWritableDatabase();
    String createUserInformationTableQuery = "CREATE TABLE user_information_table (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_name TEXT, " +
            "age INTEGER, " +
            "is_person_with_disability INTEGER, " +
            "is_new_user INTEGER, " +
            "lesson_level INTEGER, " +
            "total_number_of_test_taken INTEGER, " +
            "number_of_correct_test_answers INTEGER, " +
            "number_of_wrong_test_answer INTEGER, " +
            "accuracy_percentage TEXT " +
            ") ";
    String createLessonInformationTableQuery = "CREATE TABLE lesson_information_table (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "lesson_name TEXT, " +
            "lesson_media_source TEXT, " +
            "lesson_description TEXT " +
            ")";
    String createIdentificationTestTableQuery = "CREATE TABLE identification_test_table (" +
            "ID INTEGER, " +
            "lesson_key INTEGER, " +
            "test_question TEXT, " +
            "correct_answer TEXT " +
            ")";
    String createMultipleChoiceTestTableQuery = "CREATE TABLE multiple_choice_test_table (" +
            "ID INTEGER PRIMARY KEY, " +
            "lesson_key INTEGER, " +
            "test_question TEXT, " +
            "correct_choice TEXT, " +
            "first_incorrect_choice TEXT, " +
            "second_incorrect_choice TEXT, " +
            "third_incorrect_choice TEXT " +
            ")";


    //CLASS CONSTRUCTOR
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }


    //GETTERS/SETTERS
    public SQLiteDatabase getReadableDatabase() {
        return this.readableDatabase;
    }
    public SQLiteDatabase getWritableDatabase() {
        return this.writableDatabase;
    }

    //OVERRIDE SUPERCLASS METHODS
    @Override
    public void onCreate(@NonNull SQLiteDatabase database) {
        database.execSQL(createUserInformationTableQuery);
        database.execSQL(createLessonInformationTableQuery);
        database.execSQL(createIdentificationTestTableQuery);
        database.execSQL(createMultipleChoiceTestTableQuery);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS user_information_table");
        database.execSQL("DROP TABLE IF EXISTS lesson_information_table");
        database.execSQL("DROP TABLE IF EXISTS identification_test_table");
        database.execSQL("DROP TABLE IF EXISTS multiple_choice_test_table");
        onCreate(database);
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
    public void insertID(Integer value, String tableName) {
        ContentValues values = new ContentValues();
        values.put("id", value);
        try {
            writableDatabase.insert(tableName, null, values);
        } catch (SQLiteException sqLiteException) {
            sqLiteException.printStackTrace();
        }
        writableDatabase.close();
    }
    //Insert integer data to a certain table column by ID
    public void insertIntegerColumnDataByID(int value, String tableName, String tableColumn, int condition) {
        ContentValues values = new ContentValues();
        values.put(tableColumn, value);
        String[] arguments = {String.valueOf(condition)}; // replace 1 with the value you want to match
        try {
            writableDatabase.update(tableName, values, "id=?", arguments);
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        }
        writableDatabase.close();
    }
    //Insert integer data to a certain table column by ID
    public void insertTextColumnData(String value, String tableName, String tableColumn, int condition) {
        ContentValues values = new ContentValues();
        values.put(tableColumn, value);
        String[] arguments = {String.valueOf(condition)}; // replace 1 with the value you want to match
        try {
            writableDatabase.update(tableName, values, "id=?", arguments);
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        }
        writableDatabase.close();
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
