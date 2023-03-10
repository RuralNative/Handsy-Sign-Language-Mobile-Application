package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class IdentificationTestTableDAO {
    private final DatabaseHelper databaseHelper;
    private final String IdentificationTestInformationModel = "identification_test_table";
    private final String[] identificationTestColumns = {"id", "lesson_key", "question", "correct_answer"};
    private final String id = "id";
    private final String lessonKey = "lesson_key";
    private final String question = "question";
    private final String correctAnswer = "correct_answer";

    public IdentificationTestTableDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public int addIdentificationTest(IdentificationTestInformationModel test) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonKey, test.getLessonKey());
        values.put(question, test.getQuestion());
        values.put(correctAnswer, test.getCorrectAnswer());
        long columnID = database.insert(IdentificationTestInformationModel, null, values);
        test.setId((int) columnID);
        database.close();
        return (int) columnID;
    }

    public IdentificationTestInformationModel getIdentificationTestByID(int id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                IdentificationTestInformationModel,
                identificationTestColumns,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        IdentificationTestInformationModel test = null;
        if (cursor != null && cursor.moveToFirst()) {
            test = new IdentificationTestInformationModel(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
        }
        database.close();
        return test;
    }

    public List<IdentificationTestInformationModel> getAllIdentificationTests() {
        List<IdentificationTestInformationModel> tests = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + IdentificationTestInformationModel, null);

        if (cursor.moveToFirst()) {
            do {
                IdentificationTestInformationModel user = new IdentificationTestInformationModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                tests.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return tests;
    }

    public int updateIdentificationTest(IdentificationTestInformationModel test) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonKey, test.getLessonKey());
        values.put(question, test.getQuestion());
        values.put(correctAnswer, test.getCorrectAnswer());
        long rowsUpdated = database.update(IdentificationTestInformationModel, values, "id = ?", new String[]{String.valueOf(test.getId())});
        database.close();
        return (int) rowsUpdated;
    }

    public int deleteIdentificationTest(int id) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsDeleted = database.delete(IdentificationTestInformationModel, "id = ?", new String[] {String.valueOf(id)});
        database.close();
        return rowsDeleted;
    }

    public void deleteAllIdentificationTests() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(IdentificationTestInformationModel, null, null);
        database.close();
    }
}
