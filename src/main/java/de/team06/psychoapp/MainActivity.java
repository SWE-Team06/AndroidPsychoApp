package de.team06.psychoapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DatabaseModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(de.team06.psychoapp.R.layout.input);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_settings: startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            case R.id.action_setAlarm: setDemoAlarm();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setDemoAlarm() {
                /*
            test alarm maker
         */
        AlarmMaker testAlarm = new AlarmMaker(this);
        testAlarm.addAlarm(System.currentTimeMillis()+10000, TimeSection.FOURTH_QUARTER);

        dbModel = new DatabaseModel(this);
        dbModel.open();
    }

    public void click (View view) {
        EditText anzahlEditText = (EditText) findViewById(R.id.anzahl);
        String anzahl = anzahlEditText.getText().toString();

        EditText hoursEditText = (EditText) findViewById(R.id.hours);
        String hours = hoursEditText.getText().toString();

        EditText minutesEditText = (EditText) findViewById(R.id.minutes);
        String minutes = minutesEditText.getText().toString();

        Toast.makeText(this, anzahl + " SocialContacts and "+hours+"h "+minutes+" min", Toast.LENGTH_SHORT).show();

        dbModel = new DatabaseModel(this);
        dbModel.open();
        SocialInteraction socialInteraction = dbModel.createSocialInteraction(118800, "asdfg");

        //Toast.makeText(this, socialInteraction.toString(), Toast.LENGTH_SHORT).show();
        /*
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        cal.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
        Toast.makeText(this, text + " wurde eingegeben", Toast.LENGTH_SHORT).show();

        MainActivity.todolist.add(aufgabe);
        finish();
        */
    }
    
}
