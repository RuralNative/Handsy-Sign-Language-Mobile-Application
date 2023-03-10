package com.ruralnative.handsy_sign_language_tutorial.database;

public class IdentificationTestInformationModel {
    private int id;
    private int lessonKey;
    private String question;
    private String correctAnswer;

    public IdentificationTestInformationModel() {}
    public IdentificationTestInformationModel(int id, int lessonKey, String question, String correctAnswer) {
        this.id = id;
        this.lessonKey = lessonKey;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public IdentificationTestInformationModel(int lessonKey, String question, String correctAnswer) {
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
