package com.ruralnative.handsy_sign_language_tutorial.database;

import androidx.annotation.Nullable;

public class UserInformationTableModel {
        @Nullable private int id;
        private String userName;
        private int age;
        private int isPersonWithDisability;
        private int isNewUser;
        private int lessonLevel;
        private int totalNumberOfTestTaken;
        private int numberOfCorrectTestAnswers;
        private int numberOfWrongTestAnswer;
        private String accuracyPercentage;

        public UserInformationTableModel() {
        }

        public UserInformationTableModel(int id, String userName, int age, int isPersonWithDisability, int isNewUser, int lessonLevel, int totalNumberOfTestTaken, int numberOfCorrectTestAnswers, int numberOfWrongTestAnswer, String accuracyPercentage) {
            this.id = id;
            this.userName = userName;
            this.age = age;
            this.isPersonWithDisability = isPersonWithDisability;
            this.isNewUser = isNewUser;
            this.lessonLevel = lessonLevel;
            this.totalNumberOfTestTaken = totalNumberOfTestTaken;
            this.numberOfCorrectTestAnswers = numberOfCorrectTestAnswers;
            this.numberOfWrongTestAnswer = numberOfWrongTestAnswer;
            this.accuracyPercentage = accuracyPercentage;
        }

    public UserInformationTableModel(String userName, int age, int isPersonWithDisability, int isNewUser, int lessonLevel, int totalNumberOfTestTaken, int numberOfCorrectTestAnswers, int numberOfWrongTestAnswer, String accuracyPercentage) {
        this.userName = userName;
        this.age = age;
        this.isPersonWithDisability = isPersonWithDisability;
        this.isNewUser = isNewUser;
        this.lessonLevel = lessonLevel;
        this.totalNumberOfTestTaken = totalNumberOfTestTaken;
        this.numberOfCorrectTestAnswers = numberOfCorrectTestAnswers;
        this.numberOfWrongTestAnswer = numberOfWrongTestAnswer;
        this.accuracyPercentage = accuracyPercentage;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsPersonWithDisability() {
        return isPersonWithDisability;
    }

    public void setIsPersonWithDisability(int isPersonWithDisability) {
        this.isPersonWithDisability = isPersonWithDisability;
    }

    public int getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public int getLessonLevel() {
        return lessonLevel;
    }

    public void setLessonLevel(int lessonLevel) {
        this.lessonLevel = lessonLevel;
    }

    public int getTotalNumberOfTestTaken() {
        return totalNumberOfTestTaken;
    }

    public void setTotalNumberOfTestTaken(int totalNumberOfTestTaken) {
        this.totalNumberOfTestTaken = totalNumberOfTestTaken;
    }

    public int getNumberOfCorrectTestAnswers() {
        return numberOfCorrectTestAnswers;
    }

    public void setNumberOfCorrectTestAnswers(int numberOfCorrectTestAnswers) {
        this.numberOfCorrectTestAnswers = numberOfCorrectTestAnswers;
    }

    public int getNumberOfWrongTestAnswer() {
        return numberOfWrongTestAnswer;
    }

    public void setNumberOfWrongTestAnswer(int numberOfWrongTestAnswer) {
        this.numberOfWrongTestAnswer = numberOfWrongTestAnswer;
    }

    public String getAccuracyPercentage() {
        return accuracyPercentage;
    }

    public void setAccuracyPercentage(String accuracyPercentage) {
        this.accuracyPercentage = accuracyPercentage;
    }
}
