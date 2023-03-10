package com.ruralnative.handsy_sign_language_tutorial.database;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;

import com.ruralnative.handsy_sign_language_tutorial.database.UserInformationTableDAO;
import com.ruralnative.handsy_sign_language_tutorial.database.UserInformationTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class UserInformationTest {
    private UserInformationTableDAO userDAO;

    @Before
    public void setUp() {
        Context context = getApplicationContext();
        userDAO = new UserInformationTableDAO(context);
    }

    @After
    public void tearDown() {
        userDAO = null;
    }

    @Test
    public void testAddUser() {
        UserInformationTableModel user = new UserInformationTableModel("John Berlin", 0, 1, 1, 0, 0, 0, 0, "0%");
        long id = userDAO.addUser(user);
        assertTrue(id > 0);
    }

    @Test
    public void checkIfIDColumnAutoincrement() {
        UserInformationTableModel userOne = new UserInformationTableModel("John Berlin", 20, 0, 1, 10, 100, 60, 40, "60%");
        UserInformationTableModel userTwo = new UserInformationTableModel("John Berlin", 20, 0, 1, 10, 100, 60, 40, "60%");

        long idOne = userDAO.addUser(userOne);
        long idTwo = userDAO.addUser(userTwo);
        assertEquals(userOne.getId(), (int) idOne);
        assertEquals(userTwo.getId(), (int) idTwo);
    }

    @Test
    public void testGetUserByID() {
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

    @Test
    public void testGetAllUsers() {
        List<UserInformationTableModel> userList = userDAO.getAllUsers();
        assertNotNull(userList);
    }

    @Test
    public void testUpdateUser() {
        UserInformationTableModel user = new UserInformationTableModel("John Berlin", 1, 0, 1, 0, 0, 0, 0, "0%");
        long id = userDAO.addUser(user);

        UserInformationTableModel retrievedUser = userDAO.getUserById((int) id);
        retrievedUser.setUserName("Jackelyn Hernaez");
        userDAO.updateUser(retrievedUser);

        UserInformationTableModel updatedUser = userDAO.getUserById((int) id);
        assertEquals("Jackelyn Hernaez", updatedUser.getUserName());
    }

    @Test
    public void testDeleteUser() {
        UserInformationTableModel user = new UserInformationTableModel("John Berlin", 1, 0, 1, 0, 0, 0, 0, "0%");
        long id = userDAO.addUser(user);

        UserInformationTableModel retrievedUser = userDAO.getUserById((int) id);
        userDAO.deleteUser(retrievedUser);

        UserInformationTableModel deletedUser = userDAO.getUserById((int) id);
        assertNull(deletedUser);
    }
}
