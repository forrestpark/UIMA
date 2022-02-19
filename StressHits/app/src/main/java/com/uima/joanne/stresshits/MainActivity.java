package com.uima.joanne.stresshits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Integer numHits;
    private SharedPreferences myPrefs;
    private Switch clockMode;
    private boolean clockModeIsChecked;
    private SharedPreferences.Editor peditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext(); // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        peditor = myPrefs.edit();
        peditor.putBoolean("clockModeIsChecked", clockModeIsChecked);
        peditor.apply();

        clockMode = (Switch) findViewById(R.id.clock_switch);
        clockMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 24 hr mode
                    Log.d("onCreate", "24 hr mode!");
                } else {
                    // 12 hr mode
                    Log.d("onCreate", "12 hr mode!");
                }
                clockModeIsChecked = isChecked;
                Log.d("onCheckedChange", Boolean.toString(clockModeIsChecked));
                peditor.putBoolean("clockModeIsChecked", clockModeIsChecked);
                peditor.apply();
            }
        });

        Log.d("onCreate", "i'm creating");

    }

    @Override
    protected void onStart() {
        super.onStart();

        // get from bundle
        clockModeIsChecked = myPrefs.getBoolean("clockModeIsChecked", true);
        Log.d("onStart", Boolean.toString(clockModeIsChecked));
        clockMode.setChecked(clockModeIsChecked);
//        numHits = myPrefs.getInt("hitsValue", 0);
//        TextView hits = (TextView) findViewById(R.id.hits_value);
//        hits.setText(numHits.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putBoolean("clockModeIsChecked", clockModeIsChecked);
        // peditor.putInt("hitsValue", numHits);
        peditor.apply();

        super.onPause();
    }


    @Override
    protected void onStop() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putBoolean("clockModeIsChecked", clockModeIsChecked);
        // peditor.putInt("hitsValue", 10);
        peditor.apply();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // do stuff here
        Log.d("onDestroy", "exit 3");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        startActivity(intent);
    }

    public void learn(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, LearnActivity.class);
        startActivity(intent);
    }
}

