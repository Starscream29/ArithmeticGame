package com.example.arithmeticgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView resultText;
    TextView endOfGame;
    TextView questionAndAnswer;
    String question;
    Integer input;
    Integer answer;
    Boolean newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result_result_text);
        endOfGame = findViewById(R.id.result_end_text);
        questionAndAnswer = findViewById(R.id.result_answer_text);

        Intent intent = getIntent();
        question =intent.getStringExtra("question");
        input = intent.getIntExtra("input", 0);
        answer = intent.getIntExtra("answer", 0);

        if(input.equals(answer)){
            resultText.setText("Correct!");
            endOfGame.setVisibility(View.INVISIBLE);
            questionAndAnswer.setText(question + input);
            newGame = false;
        } else {
            resultText.setText("Incorrect. Your answer was: " + input);
            resultText.setTextColor(Color.parseColor("#FF0000"));
            endOfGame.setVisibility(View.VISIBLE);
            questionAndAnswer.setText(question + answer);
            newGame = true;
        }
    }

    public void onNext(View view){
        Intent intent = new Intent(this, questions.class);
        intent.putExtra("newGame", newGame);
        startActivity(intent);
    }
}