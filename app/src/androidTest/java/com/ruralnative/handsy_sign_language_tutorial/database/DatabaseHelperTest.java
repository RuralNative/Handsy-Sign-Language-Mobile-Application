package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;
    private final String DATABASE_NAME = "handsy_application_database.db";

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @After
    public void tearDown() {
        databaseHelper.close();
        ApplicationProvider.getApplicationContext().deleteDatabase(DATABASE_NAME);
    }

    @Test
    public void testPrepopulateDatabase() throws IOException {
        assertFalse(databaseHelper.checkIfDatabaseFileExist());
        databaseHelper.prepopulateDatabase();
        assertTrue(databaseHelper.checkIfDatabaseFileExist());
    }

    @Test
    public void testUpgradeDatabase() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int oldVersion = db.getVersion();
        Log.d("Upgrade_Method", String.valueOf(oldVersion));
        databaseHelper.onUpgrade(db, oldVersion, oldVersion + 1);
        assertEquals(databaseHelper.getReadableDatabase().getVersion(), oldVersion + 1);
    }
}
