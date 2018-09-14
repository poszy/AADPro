package com.luispena.notificationschedualer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


public class NotificationJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                this, 0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Job Service")
                .setContentText("Your Job is Currently Running!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        mNotificationManager.notify(0, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {



        return false;
    }
}

