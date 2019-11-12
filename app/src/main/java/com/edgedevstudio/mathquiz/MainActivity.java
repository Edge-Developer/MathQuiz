package com.edgedevstudio.mathquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView numOneTextView;
    TextView numTwoTextView;

    TextView highScoreTextView;
    TextView scoreTextView;

    Button ansBtn1;
    Button ansBtn2;
    Button ansBtn3;

    int scorePoints = 0;
    int highScorePoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highScoreTextView = findViewById(R.id.highscore_textview);
        scoreTextView = findViewById(R.id.score_textview);

        numOneTextView = findViewById(R.id.num_one_textview);
        numTwoTextView = findViewById(R.id.num_two_textview);


        ansBtn1 = findViewById(R.id.ans_btn1);
        ansBtn2 = findViewById(R.id.ans_btn2);
        ansBtn3 = findViewById(R.id.ans_btn3);

        ansBtn1.setOnClickListener(this);
        ansBtn2.setOnClickListener(this);
        ansBtn3.setOnClickListener(this);

        generateNewQuestion();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        int num1 = Integer.parseInt(numOneTextView.getText().toString());
        int num2 = Integer.parseInt(numTwoTextView.getText().toString());

        int rightAns = num1 * num2;
        int selectedAns = 0;


        if (id == R.id.ans_btn1)
            selectedAns = Integer.parseInt(ansBtn1.getText().toString());
        else if (id == R.id.ans_btn2)
            selectedAns = Integer.parseInt(ansBtn2.getText().toString());
        else if (id == R.id.ans_btn3)
            selectedAns = Integer.parseInt(ansBtn3.getText().toString());


        String toastMsg = "";

        if (selectedAns == rightAns){
            scorePoints +=2;
            toastMsg = "Right Answer! Keep it Up";
        } else {
            scorePoints = 0;
            toastMsg = "Wrong Answer! Try Harder";
        }

        scoreTextView.setText("Your Score: "+scorePoints);


        if (scorePoints > highScorePoints){
            highScorePoints = scorePoints;
            highScoreTextView.setText("High Score: "+highScorePoints);
        }

        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();

        generateNewQuestion();
    }


    public void generateNewQuestion(){
        Random random = new Random();

        int num1 = random.nextInt(11) + 2; //  numbers between 2 and 12
        int num2 = random.nextInt(11) + 2; //  numbers between 2 and 12

        numOneTextView.setText(""+num1);
        numTwoTextView.setText(""+num2);

        int rightAns = num1 * num2;

        int wrongAns1 = rightAns - random.nextInt(12);
        int wrongAns2 = rightAns - random.nextInt(12);


        int rightAnsIndex = random.nextInt(3); // this will generate number between 0 and 3. i.e 0, 1, 2

        if (rightAnsIndex == 0){
            ansBtn1.setText(""+rightAns);
            ansBtn2.setText(""+wrongAns1);
            ansBtn3.setText(""+wrongAns2);
        }else if (rightAnsIndex == 1){
            ansBtn1.setText(""+wrongAns1);
            ansBtn2.setText(""+rightAns);
            ansBtn3.setText(""+wrongAns2);
        }else if (rightAnsIndex == 2){
            ansBtn1.setText(""+wrongAns1);
            ansBtn2.setText(""+wrongAns2);
            ansBtn3.setText(""+rightAns);
        }
    }


}
