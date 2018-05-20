package com.luispena.phonenumberspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public TextView mtextView;
    // Spinner label
    private String mSpinnerLabel = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Creating a Spinner
        Spinner spinner = (Spinner) findViewById(R.id.label_spinner);

        // Creating Array Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.label_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);

        }


    }

    public void showText(View view) {

        // show string from edittext

        EditText editText = (EditText) findViewById(R.id.editText_main);
        TextView mTextView = (TextView) findViewById(R.id.text_phonelabel);

        // Specify to the layout ot use when the list of choices appear
        if(editText != null){

            String showString = (editText.getText().toString() + " - " + mSpinnerLabel);
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
            mTextView.setText(showString);

        }

    }

    // For AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        showText(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Log.d(TAG, getString(R.string.nothing_selecte));
    }
}
