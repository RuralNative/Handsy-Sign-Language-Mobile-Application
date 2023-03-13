package com.ruralnative.handsy_sign_language_tutorial.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class DatabaseHelperTest {
    private static final String DATABASE_NAME = "handsy_test_database.db";

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase writableDatabase;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
        writableDatabase = databaseHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        writableDatabase.close();
        databaseHelper.close();
        context.deleteDatabase(DATABASE_NAME);
    }

    @Test
    public void testDatabaseCreationMethod() {
        Cursor cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user_information_table'", null);
        assertTrue(cursor.moveToFirst());

        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='lesson_information_table'", null);
        assertTrue(cursor.moveToFirst());

        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='multiple_choice_test_table'", null);
        assertTrue(cursor.moveToFirst());

        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='identification_test_table'", null);
        assertTrue(cursor.moveToFirst());
    }

    @Test
    public void onUpgrade() {
        // Drop all tables
        writableDatabase.execSQL("DROP TABLE IF EXISTS user_information_table");
        writableDatabase.execSQL("DROP TABLE IF EXISTS lesson_information_table");
        writableDatabase.execSQL("DROP TABLE IF EXISTS multiple_choice_test_table");
        writableDatabase.execSQL("DROP TABLE IF EXISTS identification_test_table");

        // Upgrade the database
        databaseHelper.onUpgrade(writableDatabase, 1, 2);

        // Verify that user_information_table is created
        Cursor cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user_information_table'", null);
        assertTrue(cursor.moveToFirst());

        // Verify that lesson_information_table is created
        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='lesson_information_table'", null);
        assertTrue(cursor.moveToFirst());

        // Verify that multiple_choice_test_table is created
        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='multiple_choice_test_table'", null);
        assertTrue(cursor.moveToFirst());

        // Verify that identification_test_table is created
        cursor = writableDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='identification_test_table'", null);
        assertTrue(cursor.moveToFirst());
    }

    @Test
    public void testPrepopulateDatabaseFile() throws IOException {
        InputStream inputStream = context.getAssets().open(DATABASE_NAME);
        File testDatabaseFile = new File(context.getCacheDir(), DATABASE_NAME);

        OutputStream outputStream = Files.newOutputStream(testDatabaseFile.toPath());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        assertTrue(testDatabaseFile.exists());

        databaseHelper.prepopulateDatabase();
        File databaseFile = context.getDatabasePath(DATABASE_NAME);
        assertTrue(databaseFile.exists());

        SQLiteDatabase database = SQLiteDatabase.openDatabase(databaseFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
        assertNotNull(database);
        assertEquals(1, database.getVersion());
        database.close();
    }
}
