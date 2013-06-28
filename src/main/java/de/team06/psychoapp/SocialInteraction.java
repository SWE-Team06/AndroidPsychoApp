package de.team06.psychoapp;

/**
 * Created by Malte on 28.06.13.
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SocialInteraction {

    private long id;
    private String code;
    private int alarmTime;
    private int responseTime;
    private boolean skipped;
    private int numberOfContacts;
    private int hours;
    private int minutes;

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getAlarmTime() {
        return alarmTime;
    }

    public String getAlarmTimeCSV() {
        Date date = new Date();
        date.setTime((long)this.getAlarmTime()*1000);

        DateFormat dformat = new SimpleDateFormat( "hh:mm" );

        return dformat.format(date);
    }

    public int getResponseTime() {
        return responseTime;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAlarmTime(int alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public void setNumberOfContacts(int numberOfContacts) {
        this.numberOfContacts = numberOfContacts;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
