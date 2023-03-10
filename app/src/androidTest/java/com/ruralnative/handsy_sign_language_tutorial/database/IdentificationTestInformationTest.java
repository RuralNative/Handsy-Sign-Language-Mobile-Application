package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class IdentificationTestInformationTest {

    private Context context;
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase database;
    private IdentificationTestTableDAO identificationTestTableDAO;

    @Before
    public void createDb() {
        this.context = ApplicationProvider.getApplicationContext();
        this.databaseHelper = new DatabaseHelper(context);
        this.database = databaseHelper.getWritableDatabase();
        identificationTestTableDAO = new IdentificationTestTableDAO(context);
    }

    @After
    public void closeDb() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.close();
    }

    @Test
    public void testAddTest() {
        IdentificationTestInformationModel test = new IdentificationTestInformationModel(1, "media/source", "Description 1");
        long testID = identificationTestTableDAO.addIdentificationTest(test);
        assertNotNull(test);
        assertEquals(testID, test.getId());
    }

    @Test
    public void testGetTestByID() {
        IdentificationTestInformationModel test = new IdentificationTestInformationModel(1, "media/source", "Description 1");
        identificationTestTableDAO.addIdentificationTest(test);

        IdentificationTestInformationModel retrievedTest = identificationTestTableDAO.getIdentificationTestByID(test.getId());
        assertNotNull(retrievedTest);
        assertEquals(test.getLessonKey(), retrievedTest.getLessonKey());
        assertEquals(test.getQuestion(), retrievedTest.getQuestion());
        assertEquals(test.getCorrectAnswer(), retrievedTest.getCorrectAnswer());
    }

    /*
    // WARNING: This test is already passed. Rerunning the test would delete all lessons inside the database, as a prerequisite to test the size of the lessonArray, done by calling the deleteAllLessons() method. Run only if necessary
    */
    @Test
    public void testGetAllIdentificationTests() {
        identificationTestTableDAO.deleteAllIdentificationTests();

        IdentificationTestInformationModel testOne = new IdentificationTestInformationModel(1, "media/source", "Description 1");
        identificationTestTableDAO.addIdentificationTest(testOne);
        IdentificationTestInformationModel testTwo = new IdentificationTestInformationModel(2, "media/source", "Description 2");
        identificationTestTableDAO.addIdentificationTest(testTwo);

        List<IdentificationTestInformationModel> allTests = identificationTestTableDAO.getAllIdentificationTests();
        assertEquals(2, allTests.size());
        assertEquals(testOne.getLessonKey(), allTests.get(0).getLessonKey());
        assertEquals(testTwo.getLessonKey(), allTests.get(1).getLessonKey());
    }


    @Test
    public void testUpdateLesson() {
        IdentificationTestInformationModel test = new IdentificationTestInformationModel(1, "media/source", "Description 1");
        identificationTestTableDAO.addIdentificationTest(test);

        IdentificationTestInformationModel retrievedTest = identificationTestTableDAO.getIdentificationTestByID(test.getId());
        retrievedTest.setLessonKey(2);

        identificationTestTableDAO.updateIdentificationTest(retrievedTest);
        IdentificationTestInformationModel updatedLesson = identificationTestTableDAO.getIdentificationTestByID(retrievedTest.getId());
        assertEquals(2, updatedLesson.getLessonKey());
    }

    @Test
    public void testDeleteLesson() {
        IdentificationTestInformationModel test = new IdentificationTestInformationModel(1, "media/source", "Description 1");
        identificationTestTableDAO.addIdentificationTest(test);
        IdentificationTestInformationModel retrievedTest = identificationTestTableDAO.getIdentificationTestByID(test.getId());

        identificationTestTableDAO.deleteIdentificationTest(retrievedTest.getId());
        IdentificationTestInformationModel deletedTest = identificationTestTableDAO.getIdentificationTestByID(retrievedTest.getId());
        assertNull(deletedTest);
    }

    @Test
    public void testDeleteAllLessons() {
        identificationTestTableDAO.deleteAllIdentificationTests();
        List<IdentificationTestInformationModel> tests = identificationTestTableDAO.getAllIdentificationTests();
        assertEquals(0, tests.size());
    }

}