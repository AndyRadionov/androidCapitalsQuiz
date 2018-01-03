package com.example.android.quizcapitals.data;

import java.util.List;

/**
 * Data Question class
 *
 * @author Andrey Radionov
 */

public class Question {
    private int id;
    private String countryQuestion;
    private String correctAnswer;
    private List<String> capitalsAnswers;

    public Question(int id,
                    String countryQuestion,
                    String correctAnswer,
                    List<String> capitalsAnswers) {
        this.id = id;
        this.countryQuestion = countryQuestion;
        this.correctAnswer = correctAnswer;
        this.capitalsAnswers = capitalsAnswers;
    }

    public int getId() {
        return id;
    }

    public String getCountryQuestion() {
        return countryQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCapitalsAnswer1() {
        return capitalsAnswers.get(0);
    }

    public String getCapitalsAnswer2() {
        return capitalsAnswers.get(1);
    }

    public String getCapitalsAnswer3() {
        return capitalsAnswers.get(2);
    }

    public String getCapitalsAnswer4() {
        return capitalsAnswers.get(3);
    }
}
