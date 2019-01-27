package com.example.lu.startsignpicker;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.lu.startsignpicker.Adapters.StarSignPickerAdapter;

import static com.example.lu.startsignpicker.StarSignPickerTester.PICK_STARSIGN;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_SIGN_NAME = "SIGN_NAME";
    private static final String TAG = "Main Activity: " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up adapater
        StarSignPickerAdapter mAdapter= new StarSignPickerAdapter();
        mAdapter.setOnAdapaterItemclick(new StarSignPickerAdapter.IAdapterItemClick() {
            @Override
            public void onItemClicked(String mselectedItem) {
                Intent outData = new Intent();
                outData.putExtra(EXTRA_SIGN_NAME, mselectedItem);
                Log.d(TAG, "Item Shoved into Extras:" + mselectedItem);
                setResult(Activity.RESULT_OK, outData);
                finish();
            }
        });

        RecyclerView rv = findViewById(R.id.recyler_view);
        rv.setAdapter(mAdapter);
    }


}
