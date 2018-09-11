package com.luispena.standup;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton mToggleButton;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Myclass b = (MyClass) a
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mToggleButton = (ToggleButton) findViewById(R.id.alarmToggle);

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;

                if(isChecked){

                    toastMessage = getString(R.string.alarm_on_toast);

                } else {

                    toastMessage = getString(R.string.alarm_off_toast);

                }

                //Show a toast to say the alarm is turned on or off
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}
