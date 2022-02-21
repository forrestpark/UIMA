package com.uima.joanne.timediff;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    private boolean learnHard;

    private int start_hour;
    private int start_minute;
    private int end_hour;
    private int end_minute;

    private String answer;
    private String guess;

    private Button learn_check;
    private Button learn_retry;
    private Button learn_next;
    private Button three_buttons;
    private ImageButton hg_small;
    private ImageButton hg_medium;
    private ImageButton hg_large;

    private ImageView hg_small_incorrect;
    private ImageView hg_small_correct;
    private ImageView hg_medium_incorrect;
    private ImageView hg_medium_correct;
    private ImageView hg_large_incorrect;
    private ImageView hg_large_correct;

    // learn hard
    private TextView hrs_text;
    private TextView mins_text;
    private TextView learnhard_display;
    private TextView hg_small_text;
    private TextView hg_medium_text;
    private TextView hg_large_text;
    private EditText learnhard_hrs_edittext;
    private EditText learnhard_mins_edittext;

    private Context context;
    private SharedPreferences myPrefs;
    private boolean clockModeIsChecked;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        context = getApplicationContext(); // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        clockModeIsChecked = myPrefs.getBoolean("clockModeIsChecked", true);

        learnHard = false;

        setRandomTime();
        setTimeTextView();
        hideLearnHard();

        // set retry and next buttons to invisible at first
        learn_retry = (Button) findViewById(R.id.learn_retry);
        learn_retry.setVisibility(View.INVISIBLE);

        learn_next = (Button) findViewById(R.id.learn_next);
        learn_next.setVisibility(View.INVISIBLE);

        learn_check = (Button) findViewById(R.id.learn_check);

        // correct/incorrect image views
        hg_small_incorrect = (ImageView) findViewById(R.id.hg_small_incorrect);
        hg_small_correct = (ImageView) findViewById(R.id.hg_small_correct);
        hg_medium_incorrect = (ImageView) findViewById(R.id.hg_medium_incorrect);
        hg_medium_correct = (ImageView) findViewById(R.id.hg_medium_correct);
        hg_large_incorrect = (ImageView) findViewById(R.id.hg_large_incorrect);
        hg_large_correct = (ImageView) findViewById(R.id.hg_large_correct);

        // hourglass textviews
        hg_small_text = (TextView) findViewById(R.id.hg_small_text);
        hg_medium_text = (TextView) findViewById(R.id.hg_medium_text);
        hg_large_text = (TextView) findViewById(R.id.hg_large_text);

        // make all correct/incorrect images invisible at first
        setCorrectIncorrectToInvisible();
        setHourglassTextVisible();

    }

    private void setHourglassTextVisible() {
        hg_small_text = (TextView) findViewById(R.id.hg_small_text);
        hg_medium_text = (TextView) findViewById(R.id.hg_medium_text);
        hg_large_text = (TextView) findViewById(R.id.hg_large_text);

        hg_small_text.setVisibility(View.VISIBLE);
        hg_medium_text.setVisibility(View.VISIBLE);
        hg_large_text.setVisibility(View.VISIBLE);
    }

    private void setHourglassTextInvisible() {
        hg_small_text.setVisibility(View.INVISIBLE);
        hg_medium_text.setVisibility(View.INVISIBLE);
        hg_large_text.setVisibility(View.INVISIBLE);
    }

    private void setCorrectIncorrectToInvisible() {
        hg_small_incorrect.setVisibility(View.INVISIBLE);
        hg_small_correct.setVisibility(View.INVISIBLE);
        hg_medium_incorrect.setVisibility(View.INVISIBLE);
        hg_medium_correct.setVisibility(View.INVISIBLE);
        hg_large_incorrect.setVisibility(View.INVISIBLE);
        hg_large_correct.setVisibility(View.INVISIBLE);
    }

    public void onClickSmall(View view) {
        guess = "SMALL";
        Log.d("current mode: ", String.valueOf(clockModeIsChecked));
    }

    public void onClickMedium(View view) {
        guess = "MEDIUM";
    }

    public void onClickLarge(View view) {
        guess = "LARGE";
    }

    public void onClickCheck(View view) {
        if (learnHard) {
            // learn hard mode
            int[] hard_result = calculateDifference(start_hour, start_minute, end_hour, end_minute);

            int learnhard_hrs = Integer.parseInt(learnhard_hrs_edittext.getText().toString());
            int learnhard_mins = Integer.parseInt(learnhard_mins_edittext.getText().toString());

            Log.d("learn hard input", String.format("%d:%d", learnhard_hrs, learnhard_mins));
            Log.d("learn hard answer", String.format("%d:%d", hard_result[0], hard_result[1]));

            // hide check button
            learn_check.setVisibility(View.INVISIBLE);

            // show learnhard_display
            learnhard_display.setVisibility(View.VISIBLE);


            if (hard_result[0] == learnhard_hrs && hard_result[1] == learnhard_mins) {
                // correct
                learnhard_display.setText("CORRECT!");
            } else {
                learnhard_display.setText("INCORRECT");
            }

        } else {
            // learn easy mode

            boolean correct = checkAnswer(guess, answer);
            // common
            // hide: check button
            // show: correct sign, retry button, next button

            // hide check button
            learn_check.setVisibility(View.INVISIBLE);

            // show retry and next buttons
            learn_retry.setVisibility(View.VISIBLE);
            learn_next.setVisibility(View.VISIBLE);

            if (correct) {
                // if correct, show correct sign
                if (guess == "SMALL") {
                    hg_small_correct.setVisibility(View.VISIBLE);
                } else if (guess == "MEDIUM") {
                    hg_medium_correct.setVisibility(View.VISIBLE);
                } else {
                    hg_large_correct.setVisibility(View.VISIBLE);
                }
            } else {
                // if incorrect, show incorrect sign
                if (guess == "SMALL") {
                    hg_small_incorrect.setVisibility(View.VISIBLE);
                } else if (guess == "MEDIUM") {
                    hg_medium_incorrect.setVisibility(View.VISIBLE);
                } else {
                    hg_large_incorrect.setVisibility(View.VISIBLE);
                }
            }

            Log.d("answer? ", String.valueOf(correct));
        }
    }

    public void onClickNext(View view) {
        // set learnHard to true
        learnHard = true;

        backToLearn();

        // HIDE: all three hourglass buttons
        hideHourglasses();

        // regenerate time
        setRandomTime();
        setTimeTextView();

        // SHOW: EditTexts
        showLearnHard();

        setCorrectIncorrectToInvisible();
    }

    public void onClickRetry(View view) {
        // regenerate time?
//        setRandomTime();
//        setTimeTextView();
        // revert
        backToLearn();
        setCorrectIncorrectToInvisible();
    }

    private void hideLearnHard() {
        hrs_text = (TextView) findViewById(R.id.learn_hard_hrs_text);
        mins_text = (TextView) findViewById(R.id.learn_hard_mins_text);
        learnhard_display = (TextView) findViewById(R.id.learnhard_display);
        learnhard_hrs_edittext = (EditText) findViewById(R.id.learnhard_hrs_edittext);
        learnhard_mins_edittext = (EditText) findViewById(R.id.learnhard_mins_edittext);


        hrs_text.setVisibility(View.INVISIBLE);
        mins_text.setVisibility(View.INVISIBLE);
        learnhard_hrs_edittext.setVisibility(View.INVISIBLE);
        learnhard_mins_edittext.setVisibility(View.INVISIBLE);
        learnhard_display.setVisibility(View.INVISIBLE);
    }

    private void hideHourglasses() {
        hg_small = (ImageButton) findViewById(R.id.hg_small);
        hg_medium = (ImageButton) findViewById(R.id.hg_medium);
        hg_large = (ImageButton) findViewById(R.id.hg_large);

        hg_small.setVisibility(View.GONE);
        hg_medium.setVisibility(View.GONE);
        hg_large.setVisibility(View.GONE);

        setHourglassTextInvisible();
    }

    private void showLearnHard() {
        hrs_text = (TextView) findViewById(R.id.learn_hard_hrs_text);
        mins_text = (TextView) findViewById(R.id.learn_hard_mins_text);
//        learnhard_display = (TextView) findViewById(R.id.learnhard_display);
        learnhard_hrs_edittext = (EditText) findViewById(R.id.learnhard_hrs_edittext);
        learnhard_mins_edittext = (EditText) findViewById(R.id.learnhard_mins_edittext);

        hrs_text.setVisibility(View.VISIBLE);
        mins_text.setVisibility(View.VISIBLE);
//        learnhard_display.setVisibility(View.VISIBLE);
        learnhard_hrs_edittext.setVisibility(View.VISIBLE);
        learnhard_mins_edittext.setVisibility(View.VISIBLE);

    }


    private void backToLearn() {
        // HIDE: 1) retry button, 2) next button, 3) correct/incorrect signs
        learn_retry.setVisibility(View.INVISIBLE);
        learn_next.setVisibility(View.INVISIBLE);

        // SHOW: 1) check button
        learn_check.setVisibility(View.VISIBLE);
    }

    private boolean checkAnswer(String guess, String answer) {
        return guess == answer;
    }

    private void setRandomTime() {
        start_hour = generateHour();
        start_minute = generateMinute();
        end_hour = generateHour();
        end_minute = generateMinute();
        int[] result = calculateDifference(start_hour, start_minute, end_hour, end_minute);

        Log.d("actual answer:", String.valueOf(result[0]) + ":" + String.valueOf(result[1]));

        if (result[0] < 8) {
            answer = "SMALL";
        } else if (result[0] > 16) {
            answer = "LARGE";
        } else {
            answer = "MEDIUM";
        }
    }

    private void setTimeTextView() {
        TextView start_time_view = (TextView) findViewById(R.id.learn_start_time);
        TextView end_time_view = (TextView) findViewById(R.id.learn_end_time);

        // if 24 hour mode
        if (clockModeIsChecked) {
            Log.d("24 hour mode", "set time text view");
            start_time_view.setText(String.format("%02d:%02d", start_hour, start_minute));
            end_time_view.setText(String.format("%02d:%02d", end_hour, end_minute));
        } else {
            Log.d("12 hr mode on", "set time text view");
            Log.d("start/end hour:", String.valueOf(start_hour) + String.valueOf(end_hour));
            if (start_hour > 12) {
                Log.d("huh?", "pm");
                start_time_view.setText(String.format("%02d:%02d pm", start_hour % 12, start_minute));
            } else {
                Log.d("huh?", "am");
                start_time_view.setText(String.format("%02d:%02d am", start_hour, start_minute));
            }

            if (end_hour > 12) {
                end_time_view.setText(String.format("%02d:%02d pm", end_hour % 12, end_minute));
            } else {
                end_time_view.setText(String.format("%02d:%02d am", end_hour, end_minute));
            }
        }

        // if 12 hour mode
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