package com.uima.joanne.stresshits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    private int start_hour;
    private int start_minute;
    private int end_hour;
    private int end_minute;

    private String answer;
    private String guess;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        setRandomTime();
        setTimeTextView();

    }

    private void onClickSmall() {
        guess = "SMALL";
    }

    private void onClickMedium() {
        guess = "MEDIUM";
    }

    private void onClickLarge() {
        guess = "LARGE";
    }

    private void setRandomTime() {
        start_hour = generateHour();
        start_minute = generateMinute();
        end_hour = generateHour();
        end_minute = generateMinute();
        int[] result = calculateDifference(start_hour, start_minute, end_hour, end_minute);
        if (result[0] < 8) {
            answer = "SMALL";
        } else if (result[0] > 16) {
            answer = "LARGE";
        } else {
            answer = "MEDIUM";
        }


    }

    private void setTimeTextView() {
        TextView start_hour_view = (TextView) findViewById(R.id.learn_start_time);
        start_hour_view.setText(String.format("%02d", start_hour));
    }

    private int[] calculateDifference(int startHour, int startMin, int endHour, int endMin) {
        int[] diff = new int[2];

        if (endMin >= startMin) {
            diff[1] = endMin - startMin;
        } else {
            endHour -= 1;
            diff[1] = endMin + 60 - startMin;
        }

        if (endHour >= startHour) {
            diff[0] = endHour - startHour;
        } else {
            diff[0] = endHour + 24 - startHour;
        }

        return diff;
    }

    private int generateHour() {
        return rand.nextInt(24);
    }

    private int generateMinute() {
        return rand.nextInt(60);
    }

//    private String convertAndFormat(int num) {
//        String result = String.valueOf(num);
//        if (num < 10) {
//            result = "0" + result;
//        }
//    }
}