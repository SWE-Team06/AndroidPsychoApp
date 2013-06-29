package de.team06.psychoapp;

/**
 * Created by Malte on 28.06.13.
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SocialInteraction {

    // ID - Unique Identifer in Database
    private long id;
    // Probandencode as String
    private String code;
    // AlarmTime (int) as unix timestamp
    private int alarmTime;
    // ResponseTime (int) as unix timestamp
    private int responseTime;
    // User skipped Poll (boolean)
    private boolean skipped;
    // number of social Interactions since last alarm
    private int numberOfContacts;
    // hours of social interference since last alarm
    private int hours;
    // minutes of social interference since last alarm
    private int minutes;

    public SocialInteraction() {
        // init here
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getAlarmTime() {
        return alarmTime;
    }

    /**
     * Get the Alarm Time formatted hh:mm as string
     * @return String alarmTime
     */
    public String getAlarmTimeCSV() {
        Date date = new Date();
        date.setTime((long)alarmTime*1000);

        DateFormat dformat = new SimpleDateFormat( "hh:mm" );

        return dformat.format(date);
    }

    public int getResponseTime() {
        return responseTime;
    }
    /**
     * Get the Response Time formatted hh:mm as string
     * @return String ResponseTime
     */
    public String getResponseTimeCSV() {
        Date date = new Date();
        date.setTime((long)responseTime*1000);

        DateFormat dformat = new SimpleDateFormat( "hh:mm" );

        return dformat.format(date);
    }

    public boolean isSkipped() {
        return skipped;
    }

    /**
     * Get status whether poll was skipped
     * @return int skipped (0|1)
     */
    public int isSkippedCSV() {
        if (isSkipped()) return 0;
        else return 1;
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

    public void setSkipped(int skipped) {
        if(skipped == 0)
            this.skipped = false;
        else
            this.skipped = true;
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

    public void save(){
        // save socialInteraction to db for example
    }
}
