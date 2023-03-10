package com.ruralnative.handsy_sign_language_tutorial.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

@RunWith(AndroidJUnit4.class)
public class MultipleChoiceTestInformationTest {

    private Context context;
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase database;
    private MultipleChoiceTestTableDAO multipleChoiceTestTableDAO;

    @Before
    public void createDb() {
        this.context = ApplicationProvider.getApplicationContext();
        this.databaseHelper = new DatabaseHelper(context);
        this.database = databaseHelper.getWritableDatabase();
        multipleChoiceTestTableDAO = new MultipleChoiceTestTableDAO(context);
    }

    @After
    public void closeDb() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.close();
    }

    @Test
    public void testAddTest() {
        MultipleChoiceTestInformationModel test = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        long testID = multipleChoiceTestTableDAO.addMultipleChoiceTest(test);
        assertNotNull(test);
        assertEquals(testID, test.getId());
    }

    @Test
    public void testGetTestByID() {
        MultipleChoiceTestInformationModel test = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        multipleChoiceTestTableDAO.addMultipleChoiceTest(test);

        MultipleChoiceTestInformationModel retrievedTest = multipleChoiceTestTableDAO.getMultipleChoiceTestByID(test.getId());
        assertNotNull(retrievedTest);
        assertEquals(test.getLessonKey(), retrievedTest.getLessonKey());
        assertEquals(test.getQuestion(), retrievedTest.getQuestion());
        assertEquals(test.getFirstIncorrectChoice(), retrievedTest.getFirstIncorrectChoice());
        assertEquals(test.getSecondIncorrectChoice(), retrievedTest.getSecondIncorrectChoice());
        assertEquals(test.getThirdIncorrectChoice(), retrievedTest.getThirdIncorrectChoice());
        assertEquals(test.getCorrectChoice(), retrievedTest.getCorrectChoice());
    }

    /*
    // WARNING: This test is already passed. Rerunning the test would delete all lessons inside the database, as a prerequisite to test the size of the lessonArray, done by calling the deleteAllLessons() method. Run only if necessary
    @Test
    public void testGetAllMultipleChoiceTest() {
        multipleChoiceTestTableDAO.deleteAllMultipleChoiceTest();

        MultipleChoiceTestInformationModel testOne = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        multipleChoiceTestTableDAO.addMultipleChoiceTest(testOne);
        MultipleChoiceTestInformationModel testTwo = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        multipleChoiceTestTableDAO.addMultipleChoiceTest(testTwo);

        List<MultipleChoiceTestInformationModel> allTests = multipleChoiceTestTableDAO.getAllMultipleChoiceTests();
        assertEquals(2, allTests.size());
        assertEquals(testOne.getLessonKey(), allTests.get(0).getLessonKey());
        assertEquals(testTwo.getLessonKey(), allTests.get(1).getLessonKey());
    }
     */


    @Test
    public void testUpdateLesson() {
        MultipleChoiceTestInformationModel test = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        multipleChoiceTestTableDAO.addMultipleChoiceTest(test);

        MultipleChoiceTestInformationModel retrievedTest = multipleChoiceTestTableDAO.getMultipleChoiceTestByID(test.getId());
        retrievedTest.setLessonKey(2);

        multipleChoiceTestTableDAO.updateMultipleChoiceTest(retrievedTest);
        MultipleChoiceTestInformationModel updateTest = multipleChoiceTestTableDAO.getMultipleChoiceTestByID(retrievedTest.getId());
        assertEquals(2, updateTest.getLessonKey());
    }

    @Test
    public void testDeleteLesson() {
        MultipleChoiceTestInformationModel test = new MultipleChoiceTestInformationModel(
                1,
                1,
                "What is your name?",
                "What is your name?",
                "A",
                "B",
                "C");
        multipleChoiceTestTableDAO.addMultipleChoiceTest(test);
        MultipleChoiceTestInformationModel retrievedTest = multipleChoiceTestTableDAO.getMultipleChoiceTestByID(test.getId());

        multipleChoiceTestTableDAO.deleteMultipleChoiceTest(retrievedTest.getId());
        MultipleChoiceTestInformationModel deletedTest = multipleChoiceTestTableDAO.getMultipleChoiceTestByID(retrievedTest.getId());
        assertNull(deletedTest);
    }

    @Test
    public void testDeleteAllLessons() {
        multipleChoiceTestTableDAO.deleteAllMultipleChoiceTest();
        List<MultipleChoiceTestInformationModel> tests = multipleChoiceTestTableDAO.getAllMultipleChoiceTests();
        assertEquals(0, tests.size());
    }

}
