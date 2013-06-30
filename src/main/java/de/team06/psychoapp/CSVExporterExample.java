package de.team06.psychoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kevin on 29.06.13.
 */
public class CSVExporterExample extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.csv_example);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.csv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.export : click(null);
        }

        return super.onOptionsItemSelected(item);
    }

    public void click (View view) {

        List<SocialInteraction> interactions = new ArrayList<SocialInteraction>();
        SocialInteraction dataset = new SocialInteraction();
        dataset.setCode("1234");
        dataset.setNumberOfContacts(2);
        interactions.add(dataset);

        CSVExporter exporter = new CSVExporter(getApplicationContext());

        Toast.makeText(view.getContext(),"" + exporter.exportCSV(interactions),Toast.LENGTH_SHORT).show();
    }
}