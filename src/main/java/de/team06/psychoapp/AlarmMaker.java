package de.team06.psychoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Andi on 29.06.13.
 */

enum TimeSection
{
    FIRST_QUARTER,  //from 9-12
    SECOND_QUARTER, //from 13-15
    THIRD_QUARTER,  //from 16-19
    FOURTH_QUARTER  //from 20-23
}

public class AlarmMaker
{
    final static private String uniqueAlarmIdentifier = "de.team06.psychoapp.ALARM.TimeToDisclosureYourSocialAffairs";
    final static private long dayInMilliseconds = 1000*60*60*24;    //need to make an alarm every day

    /*
        we have only four time sections and have to choose one time per section.
        we need a pending intent, because our action will be performed later!
        a normal intent will be performed immediately.
     */
    private PendingIntent forFirstTimeSection;
    private PendingIntent forSecondTimeSection;
    private PendingIntent forThirdTimeSection;
    private PendingIntent forFourthTimeSection;

    /*
        the alarm manager is able to determine the right moment in a high accuracy!
        we get this service from the android system, but it requires a pending intent.
        if the alarm time comes up, the alarm manager makes a broadcast message.
        we create a broadcast receiver in the manifest. at the startup of our app
        the android system creates a new instance of "AlarmReceiver", automatically.
        if the right broadcast message comes up the callback method "onReceive" of

        "AlarmReceiver" starts the new activity "SocialInteractionActivity"
     */
    AlarmManager am;

    public AlarmMaker(Context ourAppScope)
    {
        /*
            the broadcast receiver contains an intent-filter to distinct broadcasts (intents),
            therefore we must equip our intent with a unique identifier ("uniqueAlarmIdentifier").
         */
        Intent startSocialInteractionActivity = new Intent(uniqueAlarmIdentifier);

        /*
            first, an intent will be performed immediately, but we need a delayed intent
            so we build up a pending intent. second, the pending intent have to perform a
            broadcast so we invoke getBroadcast(...) to satisfy these.
            the flag "FLAG_UPDATE_CURRENT" overwrites the current preliminary intent.
         */
        forFirstTimeSection = PendingIntent.getBroadcast(ourAppScope, 8001, startSocialInteractionActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        forSecondTimeSection = PendingIntent.getBroadcast(ourAppScope,8002, startSocialInteractionActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        forThirdTimeSection = PendingIntent.getBroadcast(ourAppScope, 8003, startSocialInteractionActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        forFourthTimeSection = PendingIntent.getBroadcast(ourAppScope, 8004, startSocialInteractionActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        /*
            get the alarm service
         */
        am = (AlarmManager)(ourAppScope.getSystemService(Context.ALARM_SERVICE));
    }


    public boolean addAlarm(long alarmTimeInFuture, TimeSection mapTheTimeQuarter)
    {
        /*
        assumption that alarm time:
            - is in the future!
            - is measured in milliseconds since January 1, 1970 00:00:00 UTC
         */

        //some checking function!!!
        //return false;

        /*
            we have to find the right pending intent in according to the time section
         */
        PendingIntent theSuitableIntent = null;

        switch(mapTheTimeQuarter)
        {
            case FIRST_QUARTER:
                theSuitableIntent = forFirstTimeSection;
                break;
            case SECOND_QUARTER:
                theSuitableIntent = forSecondTimeSection;
                break;
            case THIRD_QUARTER:
                theSuitableIntent = forThirdTimeSection;
                break;
            case FOURTH_QUARTER:
                theSuitableIntent = forFourthTimeSection;
        }

        /*
            set the alarm repeatedly, the alarms can come up in sleep mode (RTC_WAKEUP)
         */
        am.setRepeating(AlarmManager.RTC_WAKEUP, alarmTimeInFuture, AlarmManager.INTERVAL_DAY, theSuitableIntent);

        return true;
    }

    public boolean deleteAllAlarms()
    {
        /*
        deletes all alarms that was added from this app
         */
        // am.cancel(pi);
        return false;
    }


}
