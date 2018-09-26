package com.example.luispena.helloconstraint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set
        mShowCount = (TextView) findViewById(R.id.show_count);

    }

    public void showToast(View view) {

        Toast toast = Toast.makeText(this, R.string.button_label_toast, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void countUp(View view) {

        mCount++;
        if (mShowCount != null) {

            mShowCount.setText(Integer.toString(mCount));

        }


    }
}