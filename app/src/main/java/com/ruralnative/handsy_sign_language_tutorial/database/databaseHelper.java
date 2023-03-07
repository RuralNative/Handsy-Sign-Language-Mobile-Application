package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;

public class databaseHelper extends SQLiteOpenHelper {
    //Default DB Helper Attributes
    private static final String DATABASE_NAME = "handsyDatabase.db";
    private static final int DATABASE_VERSION = 1;
    //Table Creation Queries
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


    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(createUserInformationTableQuery);
        database.execSQL(createLessonInformationTableQuery);
        database.execSQL(createIdentificationTestTableQuery);
        database.execSQL(createMultipleChoiceTestTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS user_information_table");
        database.execSQL("DROP TABLE IF EXISTS lesson_information_table");
        database.execSQL("DROP TABLE IF EXISTS identification_test_table");
        database.execSQL("DROP TABLE IF EXISTS multiple_choice_test_table");
        onCreate(database);
    }

}
