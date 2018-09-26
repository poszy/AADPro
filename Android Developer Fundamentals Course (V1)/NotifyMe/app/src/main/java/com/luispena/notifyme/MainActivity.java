package com.luispena.notifyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    // Store a ref to a notification manager
    private NotificationManager mNotifyManager;
    private static final int NOTICATION_ID = 0;
    private String URL = "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private NotificationReceiver mReceiver = new NotificationReceiver();

    private Button mNotifyButton;
    private Button mUpdateButton;
    private Button mCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate mNotifyManager
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyButton = (Button) findViewById(R.id.notify);
        mUpdateButton = (Button) findViewById(R.id.update);
        mCancelButton = (Button) findViewById(R.id.cancel);

        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(true);

        registerReceiver(mReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        //Set OnClick methods.
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancellNotfication();
            }
        });



    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendNotification(){

        Intent notificaitonIntent = new Intent (this, MainActivity.class);

        /*
        * A PendingIntent is a token that you give to a foreign application (e.g. NotificationManager, AlarmManager, Home Screen AppWidgetManager, or other 3rd party applications),
        * which allows the foreign application to use your application's permissions to execute a predefined piece of code.
        If you give the foreign application an Intent, it will execute your Intent with its own permissions. But if you give the foreign application a PendingIntent,
        that application will execute your Intent using your application's permission.
        * */

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTICATION_ID, notificaitonIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(this, NOTICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT );

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);


        // ON android API version 26, (Oreo)
        // NotificationCompat.Builder is deprecated and will not work with the following code.
        // set api to 25.
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("You have been notified fam")
                .setContentText("This is your notification Text")
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .addAction(R.drawable.ic_update, "Update", updatePendingIntent)
                .addAction(R.drawable.ic_learn_more,"Learn More", learnMorePendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name);

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTICATION_ID, myNotification);

        //Enables the update and cancel buttons but disables the "Notify Me!" button
        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(true);
        mCancelButton.setEnabled(true);


    }


    public void updateNotification() {

        Intent notificaitonIntent = new Intent (this, MainActivity.class);

        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTICATION_ID, notificaitonIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(this, NOTICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT );

        // ON android API version 26, (Oreo)
        // NotificationCompat.Builder is deprecated and will not work with the following code.
        // set api to 25.
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("You have been notified fam")
                .setContentText("This is your notification Text")
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_stat_name)
                .addAction(R.drawable.ic_learn_more,"Learn More", learnMorePendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage).setBigContentTitle("Notification Updated!"));

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTICATION_ID, myNotification);

        //Disable the update button, leaving only the option to cancel
        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(true);

        //Resets the buttons
        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(false);




    }

    public void cancellNotfication() {

        mNotifyManager.cancel(NOTICATION_ID);

    }


    public class NotificationReceiver extends BroadcastReceiver{

        public NotificationReceiver(){


        }

        @Override
        public void onReceive(Context context, Intent intent) {


            updateNotification();

        }
    }
}


