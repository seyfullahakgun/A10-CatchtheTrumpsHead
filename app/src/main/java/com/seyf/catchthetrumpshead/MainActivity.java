package com.seyf.catchthetrumpshead;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView topScoreText;
    TextView timer;
    Button button;
    int score;
    int topScore;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView[] imageArray;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        topScoreText = findViewById(R.id.topText);
        timer = findViewById(R.id.timer);
        button = findViewById(R.id.button);
        score = 0;

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12= findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16};

        for(ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }

        sharedPreferences = this.getSharedPreferences("com.seyf.catchthetrumpshead", Context.MODE_PRIVATE);
        topScore = sharedPreferences.getInt("topScore",0);
        topScoreText.setText("Top Score: " + topScore);
    }

    public void startGame(View view){
        for(ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Time: " + millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(MainActivity.this,"Time is Over!",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Congratulation!");
                alert.setMessage("You've earned " + score + " Points");
                alert.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score=0;
                        scoreText.setText("Score: " + score);
                        button.setEnabled(true);
                        for(ImageView image: imageArray){
                            image.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                alert.show();
            }
        }.start();
        Random random = new Random();
        int i = random.nextInt(11);
        imageArray[i].setVisibility(View.VISIBLE);

        button.setEnabled(false);
    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score " + score);

        for(ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }
        Random random = new Random();
        int i = random.nextInt(15);
        imageArray[i].setVisibility(View.VISIBLE);
        if (score>topScore){
            topScore=score;
            topScoreText.setText("Top Score: " + topScore);
        }
        sharedPreferences.edit().putInt("topScore",topScore).apply();
    }
}