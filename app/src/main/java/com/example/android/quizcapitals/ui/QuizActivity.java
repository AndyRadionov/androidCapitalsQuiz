package com.example.android.quizcapitals.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quizcapitals.R;
import com.example.android.quizcapitals.data.Question;
import com.example.android.quizcapitals.data.QuestionsHelper;
import com.example.android.quizcapitals.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {

    public static final String USER_NAME_EXTRA = "user_name_extra";

    private ActivityQuizBinding mBinder;
    private QuestionsHelper mQuestionsHelper;

    private String mUserName;

    private Question mCurrentQuestion;
    private int mQuestionAsked;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        Intent startIntent = getIntent();
        mUserName = startIntent.getStringExtra(USER_NAME_EXTRA);

        mQuestionsHelper = new QuestionsHelper(this);
        restartQuiz();
        showNextQuestion();
    }

    public void onClickAnswer(View v) {
        checkAnswer(v);
        checkResult();
    }

    private void showNextQuestion() {
        mCurrentQuestion = mQuestionsHelper.nextQuestion();
        int questionNumber = mQuestionAsked + 1;

        mBinder.tvQuestion.setText(mCurrentQuestion.getCountryQuestion());
        mBinder.btnAnswer1.setText(mCurrentQuestion.getCapitalsAnswer1());
        mBinder.btnAnswer2.setText(mCurrentQuestion.getCapitalsAnswer2());
        mBinder.btnAnswer3.setText(mCurrentQuestion.getCapitalsAnswer3());
        mBinder.btnAnswer4.setText(mCurrentQuestion.getCapitalsAnswer4());
        mBinder.tvQuestionNumber.setText(getString(R.string.question_number_display, questionNumber));
    }

    private void checkAnswer(View v) {
        mQuestionAsked++;
        TextView answer = (TextView) v;

        if (answer.getText().equals(mCurrentQuestion.getCorrectAnswer())) {
            mScore++;
        }
        mBinder.tvScore.setText(getString(R.string.score_display, mScore));
    }

    private void checkResult() {
        if (mQuestionAsked < 10) {
            showNextQuestion();
        } else {
            showScoreDialog();
        }
    }

    private void showScoreDialog() {
        String message = getString(R.string.result_message, mUserName, mScore, getReactionString());

        new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle(R.string.result_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.exit_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizActivity.this.finish();
                    }
                })
                .setNeutralButton(R.string.restart_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartQuiz();
                    }
                })
                .setNegativeButton(R.string.change_player_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent changePlayerIntent = new Intent(QuizActivity.this, MainActivity.class);
                        changePlayerIntent.setFlags(
                                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(changePlayerIntent);
                    }
                })
                .create()
                .show();

    }

    private void restartQuiz() {
        Toast.makeText(this, R.string.game_started_msg, Toast.LENGTH_SHORT).show();
        mQuestionsHelper.resetQuestions();
        mQuestionAsked = 0;
        mScore = 0;
        mBinder.tvScore.setText(getString(R.string.score_display, mScore));
        showNextQuestion();
    }

    private String getReactionString() {
        if (mScore == 10) {
            return getString(R.string.first_score_msg);
        } else if (mScore > 7 && mScore < 10) {
            return getString(R.string.second_score_msg);
        } else if (mScore > 5 && mScore < 8) {
            return getString(R.string.third_score_msg);
        } else if (mScore > 2 && mScore < 6) {
            return getString(R.string.fourth_score_msg);
        } else {
            return getString(R.string.fifth_score_msg);
        }
    }
}
