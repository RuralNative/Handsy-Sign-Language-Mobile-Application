package com.ruralnative.handsy_sign_language_tutorial.database;

public class IdentificationTestInformationModel {
    private int id;
    private int lessonKey;
    private String question;
    private String correctQuestion;

    public IdentificationTestInformationModel() {}
    public IdentificationTestInformationModel(int id, int lessonKey, String question, String correctQuestion) {
        this.id = id;
        this.lessonKey = lessonKey;
        this.question = question;
        this.correctQuestion = correctQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonKey() {
        return lessonKey;
    }

    public void setLessonKey(int lessonKey) {
        this.lessonKey = lessonKey;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectQuestion() {
        return correctQuestion;
    }

    public void setCorrectQuestion(String correctQuestion) {
        this.correctQuestion = correctQuestion;
    }
}
