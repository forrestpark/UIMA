package com.uima.joanne.stresshits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity {

    private int startHour, startMin, endHour, endMin, diffHour, diffMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
    }

    public void onButtonClick(View view) {
        Log.d("onButtonClick", "pressed!");
        EditText editStartHour = (EditText) findViewById(R.id.startHour);
        startHour = Integer.parseInt(editStartHour.getText().toString());
        EditText editStartMin = (EditText) findViewById(R.id.startMin);
        startMin = Integer.parseInt(editStartMin.getText().toString());
        EditText editEndHour = (EditText) findViewById(R.id.endHour);
        endHour = Integer.parseInt(editEndHour.getText().toString());
        EditText editEndMin = (EditText) findViewById(R.id.endMin);
        endMin = Integer.parseInt(editEndMin.getText().toString());

        if (isLegalHour(startHour) && isLegalMin(startMin) &&
            isLegalHour(endHour) && isLegalMin(endMin)) {
            // calculate time diff
            Log.d("onButtonClick", "legal!");
            int[] result = calculateDifference(startHour, startMin, endHour, endMin);

            TextView diffHour = (TextView) findViewById(R.id.diffHour);
            diffHour.setText(String.format("%02d", result[0]));

            TextView diffMin = (TextView) findViewById(R.id.diffMin);
            diffMin.setText(String.format("%02d", result[1]));
            //set difference
        } else {
            // toast!
            Log.d("onButtonClick", "illegal!");
            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
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

    private boolean isLegalMin(int min) {
        return min >= 0 && min <= 59;
    }
    private boolean isLegalHour(int hour) {
        return hour >= 0 && hour <= 23;
    }
}