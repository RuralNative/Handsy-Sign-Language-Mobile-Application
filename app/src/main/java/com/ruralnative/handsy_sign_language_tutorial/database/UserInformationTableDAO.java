package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserInformationTableDAO {
    private final DatabaseHelper databaseHelper;
    private final String userInformationTable = "user_information_table";
    private final String[] userInformationColumns = {"id", "user_name", "age", "is_person_with_disability", "is_new_user", "lesson_level", "total_number_of_test_taken", "number_of_correct_test_answers", "number_of_wrong_test_answer", "accuracy_percentage"};
    private final String userName = "user_name";
    private final String age = "age";
    private final String isPersonWithDisability = "is_person_with_disability";
    private final String isNewUser = "is_new_user";
    private final String lessonLevel = "lesson_level";
    private final String totalNumberOfTestTaken = "total_number_of_test_taken";
    private final String numberOfCorrectTestAnswers = "number_of_correct_test_answers";
    private final String numberOfWrongTestAnswer = "number_of_wrong_test_answer";
    private final String accuracyPercentage = "accuracy_percentage";

    public UserInformationTableDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Add a new user to the database
    public long addUser(UserInformationTableModel user) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userName, user.getUserName());
        values.put(age, user.getAge());
        values.put(isPersonWithDisability, user.getIsPersonWithDisability());
        values.put(isNewUser, user.getIsNewUser());
        values.put(lessonLevel, user.getLessonLevel());
        values.put(totalNumberOfTestTaken, user.getTotalNumberOfTestTaken());
        values.put(numberOfCorrectTestAnswers, user.getNumberOfCorrectTestAnswers());
        values.put(numberOfWrongTestAnswer, user.getNumberOfWrongTestAnswer());
        values.put(accuracyPercentage, user.getAccuracyPercentage());
        long columnID = database.insert(userInformationTable, null, values);
        user.setId((int) columnID);
        return columnID;
    }

    // Get a single user by their ID
    public UserInformationTableModel getUserById(int id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(userInformationTable, userInformationColumns,
                "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        UserInformationTableModel user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new UserInformationTableModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getString(9));
        }
        if (cursor != null) {
            cursor.close();
        }
        return user;
    }

    // Get all users in the database
    public List<UserInformationTableModel> getAllUsers() {
        List<UserInformationTableModel> userList = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + userInformationTable, null);
        if (cursor.moveToFirst()) {
            do {
                UserInformationTableModel user = new UserInformationTableModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getString(9));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return userList;
    }

    // Update an existing user in the database
    public void updateUser(UserInformationTableModel user) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userName, user.getUserName());
        values.put(age, user.getAge());
        values.put(isPersonWithDisability, user.getIsPersonWithDisability());
        values.put(isNewUser, user.getIsNewUser());
        values.put(lessonLevel, user.getLessonLevel());
        values.put(totalNumberOfTestTaken, user.getTotalNumberOfTestTaken());
        values.put(numberOfCorrectTestAnswers, user.getNumberOfCorrectTestAnswers());
        values.put(numberOfWrongTestAnswer, user.getNumberOfWrongTestAnswer());
        values.put(accuracyPercentage, user.getAccuracyPercentage());
        database.update(userInformationTable, values, "id = ?", new String[]{String.valueOf(user.getId())});
        database.close();
    }

    // Delete a user from the database
    public void deleteUser(UserInformationTableModel user) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(userInformationTable, "id = ?", new String[]{String.valueOf(user.getId())});
        database.close();
    }
}
