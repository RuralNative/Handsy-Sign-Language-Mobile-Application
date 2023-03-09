package com.ruralnative.handsy_sign_language_tutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.ruralnative.handsy_sign_language_tutorial.database.DatabaseHelper;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private DatabaseHelper helper;
    private final String tableName = "user_information_table";
    private final String idColumn = "id";
    private final String userNameColumn = "user_name";
    private final String ageColumn = "age";
    private final String isPersonWithDisabilityColumn = "is_person_with_disability";
    private final String isNewUserColumn = "is_new_user";
    private final String lessonLevelColumn = "lesson_level";
    private final String totalNumberOfTestTakenColumn = "total_number_of_test_taken";
    private final String numberOfCorrectTestAnswersColumn = "number_of_correct_test_answers";
    private final String numberOfWrongTestAnswerColumn = "number_of_wrong_test_answer";
    private final String accuracyPercentageColumn = "accuracy_percentage";


    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Log.d("TEST", "Context: " + context);
        helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
    }

    private void insertSampleData() {
        helper.insertID(1, tableName);
        helper.insertTextColumnDataByID("John Berlin", tableName, userNameColumn, 1);
        helper.insertIntegerColumnDataByID(20, tableName, ageColumn, 1);
        helper.insertIntegerColumnDataByID(0, tableName, isPersonWithDisabilityColumn, 1);
        helper.insertIntegerColumnDataByID(0, tableName, isNewUserColumn, 1);
        helper.insertIntegerColumnDataByID(1, tableName, lessonLevelColumn, 1);
        helper.insertIntegerColumnDataByID(100, tableName, totalNumberOfTestTakenColumn, 1);
        helper.insertIntegerColumnDataByID(60, tableName, numberOfCorrectTestAnswersColumn, 1);
        helper.insertIntegerColumnDataByID(40, tableName, numberOfWrongTestAnswerColumn, 1);
        helper.insertTextColumnDataByID("60%", tableName, accuracyPercentageColumn, 1);
    }

    @Test
    public void testIfIDInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[0], "1");
    }
    @Test
    public void testIfUserNameInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[1], "John Berlin");
    }
    @Test
    public void testIfAgeInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[2], "20");
    }
    @Test
    public void testIfPWDBooleanInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[3], "0");
    }
    @Test
    public void testIfNewUserBooleanInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[4], "0");
    }
    @Test
    public void testIfLessonLevelInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[5], "1");
    }
    @Test
    public void testIfTotalTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[6], "100");
    }
    @Test
    public void testIfCorrectTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[7], "60");
    }
    @Test
    public void testIfIncorrectTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[8], "40");
    }
    @Test
    public void testIfAccuracyPercentageInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = helper.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[9], "60%");
    }
}
