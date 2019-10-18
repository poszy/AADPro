package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        roll_button.setOnClickListener{ roll()}
        count_button.setOnClickListener{ roll()}



    }

    private fun roll(){
        val resultText: TextView = findViewById(R.id.result_text)
        val randomInt = Random().nextInt(6) + 1
        Toast.makeText(this,"Button Clicked", Toast.LENGTH_SHORT).show()
        resultText.text = randomInt.toString()

        count(randomInt)
    }

    private fun count(arg: Int){

        val countButton : Button = findViewById(R.id.count_button)
        val resultCount: TextView = findViewById(R.id.result_count)

        if (arg == 6){

            val countResult: Int = arg
            resultCount.text = countResult.toString()


        }else {
            val countResult: Int = arg + 1
            resultCount.text = countResult.toString()


        }

        Toast.makeText(this,"Button Clicked", Toast.LENGTH_SHORT).show()

    }



}
