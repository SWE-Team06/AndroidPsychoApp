package de.team06.psychoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Andi on 30.06.13.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ourAppScope, Intent startSocialInteractionActivity) {
        Intent start = new Intent(ourAppScope.getApplicationContext(), SocialInteractionActivity.class);
        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //damnd shit! I spent 2-3h of my time for this one line of code!!!
        ourAppScope.getApplicationContext().startActivity(start);
    }
};
