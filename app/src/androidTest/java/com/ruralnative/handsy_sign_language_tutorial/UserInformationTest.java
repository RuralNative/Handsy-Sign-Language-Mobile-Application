package com.ruralnative.handsy_sign_language_tutorial;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.ruralnative.handsy_sign_language_tutorial.database.UserInformationTableDAO;
import com.ruralnative.handsy_sign_language_tutorial.database.UserInformationTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserInformationTest {
    private UserInformationTableDAO userDAO;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        userDAO = new UserInformationTableDAO(context);
    }

    @After
    public void testAddAndGetUserByID() {
        UserInformationTableModel user = new UserInformationTableModel("John Berlin", 20, 0, 1, 10, 100, 60, 40, "60%");
        userDAO.addUser(user);

        UserInformationTableModel retrievedUser = userDAO.getUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getUserName(), retrievedUser.getUserName());
        assertEquals(user.getAge(), retrievedUser.getAge());
        assertEquals(user.getIsPersonWithDisability(), retrievedUser.getIsPersonWithDisability());
        assertEquals(user.getIsNewUser(), retrievedUser.getIsNewUser());
        assertEquals(user.getLessonLevel(), retrievedUser.getLessonLevel());
        assertEquals(user.getTotalNumberOfTestTaken(), retrievedUser.getTotalNumberOfTestTaken());
        assertEquals(user.getNumberOfCorrectTestAnswers(), retrievedUser.getNumberOfCorrectTestAnswers());
        assertEquals(user.getNumberOfWrongTestAnswer(), retrievedUser.getNumberOfWrongTestAnswer());
        assertEquals(user.getAccuracyPercentage(), retrievedUser.getAccuracyPercentage());
    }

}
