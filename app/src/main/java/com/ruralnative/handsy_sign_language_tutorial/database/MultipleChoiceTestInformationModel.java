package com.ruralnative.handsy_sign_language_tutorial.database;

public class MultipleChoiceTestInformationModel {
    private int id;
    private int lessonKey;
    private String question;
    private String correctChoice;
    private String firstIncorrectChoice;
    private String secondIncorrectChoice;
    private String thirdIncorrectChoice;

    public MultipleChoiceTestInformationModel() {}
    public MultipleChoiceTestInformationModel(int id, int lessonKey, String question, String correctChoice, String firstIncorrectChoice, String secondIncorrectChoice, String thirdIncorrectChoice) {
        this.id = id;
        this.lessonKey = lessonKey;
        this.question = question;
        this.correctChoice = correctChoice;
        this.firstIncorrectChoice = firstIncorrectChoice;
        this.secondIncorrectChoice = secondIncorrectChoice;
        this.thirdIncorrectChoice = thirdIncorrectChoice;
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

    public String getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(String correctChoice) {
        this.correctChoice = correctChoice;
    }

    public String getFirstIncorrectChoice() {
        return firstIncorrectChoice;
    }

    public void setFirstIncorrectChoice(String firstIncorrectChoice) {
        this.firstIncorrectChoice = firstIncorrectChoice;
    }

    public String getSecondIncorrectChoice() {
        return secondIncorrectChoice;
    }

    public void setSecondIncorrectChoice(String secondIncorrectChoice) {
        this.secondIncorrectChoice = secondIncorrectChoice;
    }

    public String getThirdIncorrectChoice() {
        return thirdIncorrectChoice;
    }

    public void setThirdIncorrectChoice(String thirdIncorrectChoice) {
        this.thirdIncorrectChoice = thirdIncorrectChoice;
    }
}
