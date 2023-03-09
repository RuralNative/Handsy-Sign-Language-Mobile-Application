package com.ruralnative.handsy_sign_language_tutorial.database;

public class LessonInformationModel {
    private int id;
    private String lessonName;
    private String lessonMediaSource;
    private String lessonDescription;

    public LessonInformationModel() {}
    public LessonInformationModel(int id, String lessonName, String lessonMediaSource, String lessonDescription) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonMediaSource = lessonMediaSource;
        this.lessonDescription = lessonDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonMediaSource() {
        return lessonMediaSource;
    }

    public void setLessonMediaSource(String lessonMediaSource) {
        this.lessonMediaSource = lessonMediaSource;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }
}
