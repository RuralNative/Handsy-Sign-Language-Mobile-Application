package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @After
    public void tearDown() {
        databaseHelper.close();
    }

    @Test
    public void testPrepopulateDatabase() throws IOException {
        assertFalse(databaseHelper.checkIfDatabaseFileExist());
        databaseHelper.prepopulateDatabase();
        assertTrue(databaseHelper.checkIfDatabaseFileExist());
    }

    @Test
    public void testUpgradeDatabase() {
        int oldVersion = databaseHelper.getWritableDatabase().getVersion();
        databaseHelper.onUpgrade(databaseHelper.getWritableDatabase(), oldVersion, oldVersion + 1);
        assertEquals(databaseHelper.getReadableDatabase().getVersion(), oldVersion + 1);
    }
}
