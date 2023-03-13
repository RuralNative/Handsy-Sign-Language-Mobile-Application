package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "handsy_application_database.db";
    private static final String DATABASE_NAME_FOR_PREPOPULATE = "handsy_database.db";
    private static final int DATABASE_VERSION = 1;
    File databaseFile;
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
            "correct_answer TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USER_INFORMATION);
        database.execSQL(CREATE_LESSON_INFORMATION_TABLE);
        database.execSQL(CREATE_MULTIPLE_CHOICE_TEST_TABLE);
        database.execSQL(CREATE_IDENTIFICATION_TEST_TABLE);
        if(!checkIfDatabaseFileExist()) {
            try {
                prepopulateDatabase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS user_information_table");
        database.execSQL("DROP TABLE IF EXISTS lesson_information_table");
        database.execSQL("DROP TABLE IF EXISTS multiple_choice_test_table");
        database.execSQL("DROP TABLE IF EXISTS identification_test_table");
        onCreate(database);
    }

    public void prepopulateDatabase() throws IOException {
            // Open the .db file in the assets folder
            InputStream inputStream = context.getAssets().open(DATABASE_NAME_FOR_PREPOPULATE);
            // Create a new file in the default location for the database
            OutputStream outputStream = Files.newOutputStream(databaseFile.toPath());

            // Copy the contents of the .db file to the new file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();
    }

    private boolean checkIfDatabaseFileExist() {
        databaseFile = context.getDatabasePath(DATABASE_NAME);
        return databaseFile.exists();
    }
}
