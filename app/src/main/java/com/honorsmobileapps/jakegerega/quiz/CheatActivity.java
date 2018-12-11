package com.honorsmobileapps.jakegerega.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String ANSWER_STATE = "com.honorsmobileapps.jakegerega.quiz.answer_state";
    private static final String CHEAT_STATE = "com.honorsmobileapps.jakegerega.quiz.cheat_state";

    private TextView correctAnswer;
    private Button cheatConfirm;
    private boolean cheatState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        correctAnswer = findViewById(R.id.answer_text_view);
        cheatConfirm = findViewById(R.id.confirmcheat);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.confirmcheat){
                    correctAnswer.setText(getIntent().getBooleanExtra(ANSWER_STATE, false) + "");
                    cheatState = true;
                    cheatConfirm.setVisibility(View.INVISIBLE);
                }
            }
        };

        cheatConfirm.setOnClickListener(listener);
    }

    public static Intent newIntent(Context packageContext, boolean correctAnswer) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(ANSWER_STATE, correctAnswer);
        return i;
    }

    public void setAnswerShownResult(){
        Intent i = new Intent();
        i.putExtra(CHEAT_STATE, cheatState);
        setResult(RESULT_OK, i);
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(CHEAT_STATE, false);
    }
}
