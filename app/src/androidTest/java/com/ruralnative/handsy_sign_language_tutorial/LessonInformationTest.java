package com.ruralnative.handsy_sign_language_tutorial;

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

import com.ruralnative.handsy_sign_language_tutorial.database.DatabaseHelper;
import com.ruralnative.handsy_sign_language_tutorial.database.LessonInformationModel;
import com.ruralnative.handsy_sign_language_tutorial.database.LessonInformationTableDAO;

@RunWith(AndroidJUnit4.class)
public class LessonInformationTest {

    private Context context;
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase database;
    private LessonInformationTableDAO lessonInformationTableDAO;

    @Before
    public void createDb() {
        this.context = ApplicationProvider.getApplicationContext();
        this.databaseHelper = new DatabaseHelper(context);
        this.database = databaseHelper.getWritableDatabase();
        lessonInformationTableDAO = new LessonInformationTableDAO(context);
    }

    @After
    public void closeDb() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.close();
    }

    @Test
    public void testAddLesson() {
        LessonInformationModel lesson = new LessonInformationModel("Lesson 1", "media/source", "Description 1");
        long lessonID = lessonInformationTableDAO.addLesson(lesson);
        assertNotNull(lesson);
        assertEquals(lessonID, lesson.getId());
    }

    @Test
    public void testGetLessonByID() {
        LessonInformationModel lesson = new LessonInformationModel("Lesson 1", "media/source", "Description 1");
        lessonInformationTableDAO.addLesson(lesson);

        LessonInformationModel retrievedLesson = lessonInformationTableDAO.getLessonById(lesson.getId());
        assertNotNull(retrievedLesson);
        assertEquals(lesson.getLessonName(), retrievedLesson.getLessonName());
        assertEquals(lesson.getLessonMediaSource(), retrievedLesson.getLessonMediaSource());
        assertEquals(lesson.getLessonDescription(), retrievedLesson.getLessonDescription());
    }

    /*
    // WARNING: This test is already passed. Rerunning the test would delete all lessons inside the database, as a prerequisite to test the size of the lessonArray, done by calling the deleteAllLessons() method. Run only if necessary
    @Test
    public void testGetAllLessons() {
        lessonInformationTableDAO.deleteAllLessons();

        LessonInformationModel lessonOne = new LessonInformationModel("Lesson 1", "media/source", "Description 1");
        lessonInformationTableDAO.addLesson(lessonOne);
        LessonInformationModel lessonTwo = new LessonInformationModel("Lesson 2", "media/source", "Description 2");
        lessonInformationTableDAO.addLesson(lessonTwo);

        List<LessonInformationModel> allLessons = lessonInformationTableDAO.getAllLessons();
        assertEquals(2, allLessons.size());
        assertEquals(lessonOne.getLessonName(), allLessons.get(0).getLessonName());
        assertEquals(lessonTwo.getLessonName(), allLessons.get(1).getLessonName());
    }
    */

    @Test
    public void testUpdateLesson() {
        LessonInformationModel lesson = new LessonInformationModel("Lesson 1", "media/source", "Description 1");
        lessonInformationTableDAO.addLesson(lesson);

        LessonInformationModel retrievedLesson = lessonInformationTableDAO.getLessonById(lesson.getId());
        retrievedLesson.setLessonName("New Lesson Name");

        lessonInformationTableDAO.updateLesson(retrievedLesson);
        LessonInformationModel updatedLesson = lessonInformationTableDAO.getLessonById(retrievedLesson.getId());
        assertEquals("New Lesson Name", updatedLesson.getLessonName());
    }

    @Test
    public void testDeleteLesson() {
        LessonInformationModel lesson = new LessonInformationModel("Lesson 1", "media/source", "Description 1");
        lessonInformationTableDAO.addLesson(lesson);
        LessonInformationModel retrievedLesson = lessonInformationTableDAO.getLessonById(lesson.getId());

        lessonInformationTableDAO.deleteLesson(retrievedLesson.getId());
        LessonInformationModel deletedLesson = lessonInformationTableDAO.getLessonById(retrievedLesson.getId());
        assertNull(deletedLesson);
    }

    @Test
    public void testDeleteAllLessons() {
        lessonInformationTableDAO.deleteAllLessons();
        List<LessonInformationModel> lessons = lessonInformationTableDAO.getAllLessons();
        assertEquals(0, lessons.size());
    }

}
