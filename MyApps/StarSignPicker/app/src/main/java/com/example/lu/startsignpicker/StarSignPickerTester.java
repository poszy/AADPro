package com.example.lu.startsignpicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.lu.startsignpicker.MainActivity.EXTRA_SIGN_NAME;

public class StarSignPickerTester extends AppCompatActivity {

    public static final int PICK_STARSIGN = 1;
    private static final String TAG = "StarSignPicker: " ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_sign_picker_tester);

        Button button = findViewById(R.id.pick_starsign_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        Uri.parse("starsigns://"));
                startActivityForResult(intent, PICK_STARSIGN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case(PICK_STARSIGN):{
                if(resultCode == Activity.RESULT_OK){
                    String selectedSign = data.getStringExtra(EXTRA_SIGN_NAME);
                    Log.d(TAG, "Item retrieved from Extras:" + EXTRA_SIGN_NAME);

                    TextView textView = findViewById(R.id.selected_starsign_textView);
                    textView.setText(selectedSign);
                }
                break;
            }
            default:break;
        }
    }
}
