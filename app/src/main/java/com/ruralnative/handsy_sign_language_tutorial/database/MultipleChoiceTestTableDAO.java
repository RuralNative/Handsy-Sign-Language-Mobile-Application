package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceTestTableDAO {
    private final DatabaseHelper databaseHelper;
    private final String multipleChoiceTestTable = "multiple_choice_test_table";
    private final String[] multipleChoiceTestColumns = {"id", "lesson_key", "question", "correct_choice", "first_incorrect_choice", "second_incorrect_choice", "third_incorrect_choice"};
    private final String id = "id";
    private final String lessonKey = "lesson_key";
    private final String question = "question";
    private final String correctChoice = "correct_choice";
    private final String firstIncorrectChoice = "first_incorrect_choice";
    private final String second_incorrect_choice = "second_incorrect_choice";
    private final String third_incorrect_choice = "third_incorrect_choice";

    public MultipleChoiceTestTableDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public int addMultipleChoiceTest(MultipleChoiceTestInformationModel test) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonKey, test.getLessonKey());
        values.put(question, test.getQuestion());
        values.put(correctChoice, test.getCorrectChoice());
        values.put(correctChoice, test.getFirstIncorrectChoice());
        values.put(correctChoice, test.getSecondIncorrectChoice());
        values.put(correctChoice, test.getThirdIncorrectChoice());
        long columnID = database.insert(multipleChoiceTestTable, null, values);
        test.setId((int) columnID);
        database.close();
        return (int) columnID;
    }

    public MultipleChoiceTestInformationModel getIdentificationTestByID(int id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                multipleChoiceTestTable,
                multipleChoiceTestColumns,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        MultipleChoiceTestInformationModel test = null;
        if (cursor != null && cursor.moveToFirst()) {
            test = new MultipleChoiceTestInformationModel(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
        }
        database.close();
        return test;
    }

    public List<MultipleChoiceTestInformationModel> getAllMultipleChoiceTests() {
        List<MultipleChoiceTestInformationModel> tests = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + multipleChoiceTestTable, null);

        if (cursor.moveToFirst()) {
            do {
                MultipleChoiceTestInformationModel test = new MultipleChoiceTestInformationModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                tests.add(test);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return tests;
    }

    public int updateMultipleChoiceTest(MultipleChoiceTestInformationModel test) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonKey, test.getLessonKey());
        values.put(question, test.getQuestion());
        values.put(correctChoice, test.getCorrectChoice());
        values.put(correctChoice, test.getFirstIncorrectChoice());
        values.put(correctChoice, test.getSecondIncorrectChoice());
        values.put(correctChoice, test.getThirdIncorrectChoice());
        long rowsUpdated = database.update(multipleChoiceTestTable, values, "id = ?", new String[]{String.valueOf(test.getId())});
        database.close();
        return (int) rowsUpdated;
    }

    public int deleteMultipleChoiceTest(int id) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsDeleted = database.delete(multipleChoiceTestTable, "id = ?", new String[] {String.valueOf(id)});
        database.close();
        return rowsDeleted;
    }

    public void deleteAllMultipleChoiceTest() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(multipleChoiceTestTable, null, null);
        database.close();
    }
}

