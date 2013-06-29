package de.team06.psychoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Kevin on 29.06.13. It´s fucking fantastic.
 */


public class CSVExporter extends Activity {

    Context context = getApplicationContext();
    DatabaseModel database = null;
    List<SocialInteraction> interactions = null;

    public void onCreate(Bundle savedInstanceState) {
        database = new DatabaseModel(context);
        interactions = database.getAllSocialInteractions();
        super.onCreate(savedInstanceState);
    }

    public boolean saveCSV(List<SocialInteraction> list) {

        List <SocialInteraction> interactions = list;
        String csvHeader = "Code;Datum;Alarmzeit;Antwortzeit;Abbruch;Kontakte;Stunden;Minuten \n";
        String csvValues = "";

        for (SocialInteraction s : interactions) {
            csvValues = s.getCode() + ";" + s.getAlarmTimeCSV() + ";" + s.getResponseTimeCSV() + ";" + s.isSkippedCSV() + ";" + s.getNumberOfContacts() + ";" + s.getHours() + ";" + s.getMinutes() + "\n";
        }

        if (isExternalStorageWritable()) {

            try {
            String filename = interactions.get(0).getCode() + ".csv";
            String values = csvHeader + csvValues;

            File csv = new File(context.getExternalFilesDir(null), filename);
            FileWriter fileWriter = new FileWriter(csv);

            fileWriter.write(values);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
       return false;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


}