package com.example.android.quizcapitals.data;

import android.content.Context;

import com.example.android.quizcapitals.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Provides Quiz questions
 *
 * @author Andrey Radionov
 */

public class QuestionsHelper {
    private String[] countries;
    private String[] capitals;
    private Set<Integer> askedQuestionIds;
    private Random idGenerator;

    public QuestionsHelper(Context context) {
        countries = context.getResources().getStringArray(R.array.countries);
        capitals = context.getResources().getStringArray(R.array.capitals);
        askedQuestionIds = new HashSet<>(10);
        resetQuestions();
    }

    public void resetQuestions() {
        askedQuestionIds.clear();
        idGenerator = new Random();
    }

    public Question nextQuestion() {
        int countryId = getNextCountryId();

        return new Question(
                countryId,
                countries[countryId],
                capitals[countryId],
                getAnswers(countryId)
        );
    }

    private int getNextCountryId() {
        int countryId;

        do {
            countryId = idGenerator.nextInt(countries.length);
        } while (askedQuestionIds.contains(countryId));

        askedQuestionIds.add(countryId);
        return countryId;
    }

    private List<String> getAnswers(int correctAnswerId) {
        List<String> answers = new ArrayList<>(4);
        answers.add(capitals[correctAnswerId]);

        for (int i = 0; i < 3; i++) {

            int capitalId;
            do {
                capitalId = idGenerator.nextInt(capitals.length);
            } while (answers.contains(capitals[capitalId]));

            answers.add(capitals[capitalId]);
        }
        Collections.shuffle(answers);
        return answers;
    }
}
