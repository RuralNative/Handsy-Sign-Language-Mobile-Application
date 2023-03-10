package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "handsy_database";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_USER_INFORMATION = "CREATE TABLE user_information_table " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_name TEXT, " +
            "age INTEGER, " +
            "is_person_with_disability INTEGER, " +
            "is_new_user INTEGER, " +
            "lesson_level INTEGER, " +
            "total_number_of_test_taken INTEGER, " +
            "number_of_correct_test_answers INTEGER, " +
            "number_of_wrong_test_answer INTEGER, " +
            "accuracy_percentage TEXT)";
    private static final String CREATE_LESSON_INFORMATION_TABLE = "CREATE TABLE lesson_information_table " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "lesson_name TEXT, " +
            "lesson_media_source TEXT, " +
            "lesson_description TEXT)";
    private static final String CREATE_MULTIPLE_CHOICE_TEST_TABLE = "CREATE TABLE multiple_choice_test_table " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "lesson_key INT, " +
            "question TEXT, " +
            "correct_choice TEXT, " +
            "first_incorrect_choice TEXT, " +
            "second_incorrect_choice TEXT, " +
            "third_incorrect_choice TEXT)";
    private static final String CREATE_IDENTIFICATION_TEST_TABLE = "CREATE TABLE identification_test_table " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "lesson_key INT, " +
            "question TEXT, " +
            "correct_answer)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USER_INFORMATION);
        database.execSQL(CREATE_LESSON_INFORMATION_TABLE);
        database.execSQL(CREATE_MULTIPLE_CHOICE_TEST_TABLE);
        database.execSQL(CREATE_IDENTIFICATION_TEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS user_information_table");
        database.execSQL("DROP TABLE IF EXISTS lesson_information_table");
        database.execSQL("DROP TABLE IF EXISTS multiple_choice_test_table");
        database.execSQL("DROP TABLE IF EXISTS identification_test_table");
        onCreate(database);
    }
}