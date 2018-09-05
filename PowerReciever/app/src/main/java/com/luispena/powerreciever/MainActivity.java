package com.luispena.powerreciever;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PackageManager mPackageManager;
    private ComponentName mComponentName;

    private static final String ACTION_CUSTOM_BROADCAST = "com.luispena.powerreciever.ACTION_CUSTOM_BROADCAST";
    private CustomReceiver mReceiver = new CustomReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mComponentName = new ComponentName(this, CustomReceiver.class);
        mPackageManager = getPackageManager();


        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onStart() {

        mPackageManager.setComponentEnabledSetting
                (mComponentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
        super.onStart();

    }
    @Override
    protected void onStop() {

        mPackageManager.setComponentEnabledSetting
                (mComponentName,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();

    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}
