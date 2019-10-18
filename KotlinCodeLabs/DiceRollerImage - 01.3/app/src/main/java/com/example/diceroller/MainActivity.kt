package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    /*
    The lateinit keyword promises the Kotlin compiler that the variable will be initialized before the code calls any operations on it.
    Therefore we don't need to initialize the variable to null here, and we can treat it as a non-nullable variable when we use it.
    It is a best practice to use lateinit with fields that hold views in just this way. */
    lateinit var diceImage : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice_image)
        roll_button.setOnClickListener{ roll()}



    }

    private fun roll(){

        val randomInt = Random().nextInt(6) + 1

        // With android kotlin extensions I do not have to set FindViewByID I can just get the ID
        // through the xml.
        //val diceImage: ImageView = findViewById(R.id.dice_image)

        val drawableResource = when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else-> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)


    }





}
