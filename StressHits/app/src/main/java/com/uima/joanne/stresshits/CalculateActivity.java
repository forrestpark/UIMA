package com.uima.joanne.stresshits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculateActivity extends AppCompatActivity {

    private int startHour, startMin, endHour, endMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
    }

    public void onButtonClick(View view) {
        EditText editStartHour = (EditText) findViewById(R.id.startHour);
        startHour = Integer.parseInt(editStartHour.getText().toString());
        EditText editStartMin = (EditText) findViewById(R.id.startMin);
        startHour = Integer.parseInt(editStartMin.getText().toString());
        EditText editEndHour = (EditText) findViewById(R.id.endHour);
        startHour = Integer.parseInt(editEndHour.getText().toString());
        EditText editEndMin = (EditText) findViewById(R.id.endMin);
        startHour = Integer.parseInt(editEndMin.getText().toString());

        if (isLegalHour(startHour) && isLegalMin(startMin) &&
            isLegalHour(endHour) && isLegalMin(endMin)) {
            // calculate time diff
        }
    }

    private boolean isLegalMin(int min) {
        return min >= 0 && min <= 59;
    }
    private boolean isLegalHour(int hour) {
        return hour >= 0 && hour <= 23;
    }
}