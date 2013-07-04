package de.team06.psychoapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Andi on 30.06.13.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ourAppScope, Intent intentFromAlarmManager) {
        /*
            creates an intent to start our "SocialInteractionActivity"
            you have to add the "FLAG_ACTIVITY_NEW_TASK" flag, otherwise the app will be collapse!
         */

        /* ### durch Notification überflüssig #### */
        /* Intent startSocialInteractionActivity = new Intent(ourAppScope, MainActivity.class);
        startSocialInteractionActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ourAppScope.getApplicationContext().startActivity(startSocialInteractionActivity);*/

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ourAppScope)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Psycho-App")
                        .setContentText("Bitte soziale Kontakte eintragen.")
                        .setAutoCancel(true);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(ourAppScope, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ourAppScope);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) ourAppScope.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }
}
