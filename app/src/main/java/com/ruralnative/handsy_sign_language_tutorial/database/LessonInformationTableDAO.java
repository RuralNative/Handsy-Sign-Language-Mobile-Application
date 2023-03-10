package com.ruralnative.handsy_sign_language_tutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LessonInformationTableDAO {
    private final DatabaseHelper databaseHelper;
    private final String lessonInformationTable = "lesson_information_table";
    private final String[] lessonInformationColumns = {"id", "lesson_name", "lesson_media_source", "lesson_description"};
    private final String id = "id";
    private final String lessonName = "lesson_name";
    private final String lessonMediaSource = "lesson_media_source";
    private final String lessonDescription = "lesson_description";

    public LessonInformationTableDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //Add lesson object to the table with the method running the INSERT function determining the column ID
    public int addLesson(LessonInformationModel lesson) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonName, lesson.getLessonName());
        values.put(lessonMediaSource, lesson.getLessonMediaSource());
        values.put(lessonDescription, lesson.getLessonDescription());
        long columnID = database.insert(lessonInformationTable, null, values);
        lesson.setId((int) columnID);
        database.close();
        return (int) columnID;
    }

    public LessonInformationModel getLessonById(int id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                lessonInformationTable,
                lessonInformationColumns,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        LessonInformationModel lesson = null;
        if (cursor != null && cursor.moveToFirst()) {
            lesson = new LessonInformationModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
        }
        database.close();
        return lesson;
    }

    public List<LessonInformationModel> getAllLessons() {
        List<LessonInformationModel> lessons = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + lessonInformationTable, null);

        if (cursor.moveToFirst()) {
            do {
                LessonInformationModel user = new LessonInformationModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                lessons.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return lessons;
    }

    public int updateLesson(LessonInformationModel lesson) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(lessonName, lesson.getLessonName());
        values.put(lessonMediaSource, lesson.getLessonMediaSource());
        values.put(lessonDescription, lesson.getLessonDescription());
        long rowsUpdated = database.update(lessonInformationTable, values, "id = ?", new String[]{String.valueOf(lesson.getId())});
        database.close();
        return (int) rowsUpdated;
    }

    public int deleteLesson(int id) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsDeleted = database.delete(lessonInformationTable, "id = ?", new String[] {String.valueOf(id)});
        database.close();
        return rowsDeleted;
    }

    public void deleteAllLessons() {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(lessonInformationTable, null, null);
        database.close();
    }
}
