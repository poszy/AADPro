package com.luispena.notificationschedualer;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private JobScheduler mScheduler;
    private static final int JOB_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleJob(View view) {


        mRadioGroup = (RadioGroup) findViewById(R.id.netWorkOptions);
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getSimpleName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);



        int selectNetworkID = mRadioGroup.getCheckedRadioButtonId();

        // Create a selected network option integer variable and set it equal to the default network option (no network required
        int selectNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        switch (selectNetworkID){
            case R.id.noNetwork:

                selectNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;

            case R.id.anyNetwork:

                selectNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;

            case R.id.wifiNetwork:

                selectNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;


        }

        builder.setRequiredNetworkType(selectNetworkOption);



    }

    public void cancelJob(View view) {
    }
}
