package com.ruralnative.handsy_sign_language_tutorial;

import static org.junit.Assert.assertEquals;

import com.ruralnative.handsy_sign_language_tutorial.database.DatabaseMethodHelper;

import org.junit.Test;

public class UserInformationTableTest {
    private final DatabaseMethodHelper database = new DatabaseMethodHelper();
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

    private void insertSampleData() {
        database.insertID(1, tableName);
        database.insertTextColumnDataByID("John Berlin", tableName, userNameColumn, "1");
        database.insertIntegerColumnDataByID(20, tableName, ageColumn, "1");
        database.insertIntegerColumnDataByID(0, tableName, isPersonWithDisabilityColumn, "1");
        database.insertIntegerColumnDataByID(0, tableName, isNewUserColumn, "1");
        database.insertIntegerColumnDataByID(1, tableName, lessonLevelColumn, "1");
        database.insertIntegerColumnDataByID(100, tableName, totalNumberOfTestTakenColumn, "1");
        database.insertIntegerColumnDataByID(60, tableName, numberOfCorrectTestAnswersColumn, "1");
        database.insertIntegerColumnDataByID(40, tableName, numberOfWrongTestAnswerColumn, "1");
        database.insertTextColumnDataByID("60%", tableName, accuracyPercentageColumn, "1");
    }

    @Test
    public void testIfIDInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[0], "1");
    }
    @Test
    public void testIfUserNameInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[1], "John Berlin");
    }
    @Test
    public void testIfAgeInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[2], "20");
    }
    @Test
    public void testIfPWDBooleanInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[3], "0");
    }
    @Test
    public void testIfNewUserBooleanInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[4], "0");
    }
    @Test
    public void testIfLessonLevelInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[5], "1");
    }
    @Test
    public void testIfTotalTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[6], "100");
    }
    @Test
    public void testIfCorrectTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[7], "60");
    }
    @Test
    public void testIfIncorrectTestInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[8], "40");
    }
    @Test
    public void testIfAccuracyPercentageInsertionSuccessful() {
        insertSampleData();
        String[] dataRowArray = database.selectRowDataWithID(tableName, 1);
        assertEquals(dataRowArray[9], "60%");
    }
}
