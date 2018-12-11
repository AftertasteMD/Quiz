package com.honorsmobileapps.jakegerega.quiz;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private Button trueOptionButton;
    private Button falseOptionButton;
    private Button cheatOptionButton;
    private ImageButton nextOptionButton;
    private ImageButton previousOptionButton;
    private TextView questionTextView;
    private int indexOfQuestion;
    private static final String TAG = "QuizActivity";
    private static final String KEY_QUESTIONINDEX = "indexOfQuestion";
    private static final int CHEAT_STATE_REQUEST_CODE = 0;
    private boolean isCheater = false;

    private Question[] questionArray = {
            new Question(R.string.question_numberone, true),
            new Question(R.string.question_numbertwo, true),
            new Question(R.string.question_numberthree, false),
            new Question(R.string.question_numberfour, true),
            new Question(R.string.question_numberfive, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate() called");

        trueOptionButton = findViewById(R.id.truebutton);
        falseOptionButton = findViewById(R.id.falsebutton);
        nextOptionButton = findViewById(R.id.nextbutton);
        previousOptionButton = findViewById(R.id.previousbutton);
        questionTextView = findViewById(R.id.question);
        cheatOptionButton = findViewById(R.id.cheatbutton);

        updateQuestion();
        if (savedInstanceState != null) {
            indexOfQuestion = savedInstanceState.getInt(KEY_QUESTIONINDEX);
            updateQuestion();
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.truebutton) {
                    checkAnswer(true);
                }
                if (v.getId() == R.id.falsebutton) {
                    checkAnswer(false);
                }
                if (v.getId() == R.id.nextbutton) {
                    if (indexOfQuestion == 4) {
                        indexOfQuestion = 0;
                        updateQuestion();
                    } else {
                        indexOfQuestion++;
                        updateQuestion();
                    }
                }
                if (v.getId() == R.id.previousbutton) {
                    if (indexOfQuestion == 0) {
                        indexOfQuestion = 4;
                        updateQuestion();
                    } else {
                        indexOfQuestion--;
                        updateQuestion();
                    }
                }
                if (v.getId() == R.id.question) {
                    if (indexOfQuestion == 4) {
                        indexOfQuestion = 0;
                        updateQuestion();
                    } else {
                        indexOfQuestion++;
                        updateQuestion();
                    }
                }
                if (v.getId() == R.id.cheatbutton) {
                    Intent i = CheatActivity.newIntent(QuizActivity.this, questionArray[indexOfQuestion].getAnswerState());
                    startActivityForResult(i, CHEAT_STATE_REQUEST_CODE);
                }
            }
        };
        trueOptionButton.setOnClickListener(listener);
        falseOptionButton.setOnClickListener(listener);
        nextOptionButton.setOnClickListener(listener);
        previousOptionButton.setOnClickListener(listener);
        questionTextView.setOnClickListener(listener);
        cheatOptionButton.setOnClickListener(listener);
    }

    public void updateQuestion() {
        questionTextView.setText(questionArray[indexOfQuestion].getIDOfStringResource());
    }

    public void checkAnswer(boolean userChoice) {
        if (isCheater) {
            Toast.makeText(QuizActivity.this, "Cheating is wrong.", Toast.LENGTH_LONG).show();
        } else if (userChoice == questionArray[indexOfQuestion].getAnswerState()) {
            Toast.makeText(QuizActivity.this, R.string.correct_answer, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(QuizActivity.this, R.string.wrong_answer, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == CHEAT_STATE_REQUEST_CODE) {
            if (data != null) {
                boolean didCheat = CheatActivity.wasAnswerShown(data);
                if (didCheat) {
                    isCheater = true;
                }
            }
        }
    }
}
