package com.example.arithmeticgame;

import static java.lang.Math.floorDiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class questions extends AppCompatActivity {

    TextView questionNewGame;
    TextView questionText;
    EditText answerText;
    Operator answer;
    String question;
    Boolean newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        questionNewGame = findViewById(R.id.questions_new_game_text);
        newGame =intent.getBooleanExtra("newGame", true);
        if (newGame) {
            questionNewGame.setVisibility(View.VISIBLE);
        } else{
            questionNewGame.setVisibility(View.INVISIBLE);
        }
        getQuestion();
    }

    private void getQuestion() {
        Random r = new Random();
        Integer a = r.nextInt(100) + 1;
        Integer b = r.nextInt(100) + 1;
        answer = getOperator(a, b);
        questionText = findViewById(R.id.question_text);
        question = a + answer.operator + b + "=";
        questionText.setText(question + "?");
    }

    private Operator getOperator(Integer a, Integer b) {

        Operator op = new Operator();
        Random operatorChoice = new Random();
        int operator = operatorChoice.nextInt(5);
        String operatorValue;
        switch (operator) {
            case 0:
                operatorValue = "+";
                op.operator = operatorValue;
                op.answer = a + b;
                return op;
            case 1:
                operatorValue = "-";
                op.operator = operatorValue;
                op.answer = a - b;
                return op;
            case 2:
                operatorValue = "*";
                op.operator = operatorValue;
                op.answer = a * b;
                return op;
            case 3:
                operatorValue = "//";
                op.operator = operatorValue;
                op.answer = floorDiv(a, b);
                return op;
            case 4:
                operatorValue = "%";
                op.operator = operatorValue;
                op.answer = a % b;
                return op;
        }
        return op;
    }

    public void onNext(View view) {
        answerText = findViewById(R.id.answer_entry);
        Integer input = 0;
        try {
            input = Integer.valueOf(answerText.getText().toString());
        } catch(Exception e) {
            Toast.makeText(this, "Bad input", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, Result.class);
        intent.putExtra("question", question);
        intent.putExtra("answer", answer.answer);
        intent.putExtra("input", input);
        startActivity(intent);
    }
}
