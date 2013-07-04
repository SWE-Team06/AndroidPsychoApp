package de.team06.psychoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Andi on 30.06.13.
 */

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context ourAppScope, Intent intentFromAlarmManager)
    {
        /*
            creates an intent to start our "SocialInteractionActivity"
            you have to add the "FLAG_ACTIVITY_NEW_TASK" flag, otherwise the app will be collapse!
         */

        Intent startSocialInteractionActivity = new Intent(ourAppScope, SocialInteractionActivity.class);
        startSocialInteractionActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ourAppScope.getApplicationContext().startActivity(startSocialInteractionActivity);
    }
};
