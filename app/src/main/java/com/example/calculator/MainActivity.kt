package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText
    private lateinit var plusBTN: Button
    private lateinit var minusBTN: Button
    private lateinit var resultTextTV: TextView
    private lateinit var resetBTN: Button
    private lateinit var exitBTN: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firstTimeET = findViewById(R.id.first_timeET)
        secondTimeET = findViewById(R.id.second_timeET)
        resultTextTV = findViewById(R.id.result_textTV)

        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)
        resetBTN = findViewById(R.id.resetBTN)
        exitBTN = findViewById(R.id.exitBTN)

        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)
        resetBTN.setOnClickListener(this)
        exitBTN.setOnClickListener {
            finish()
        }
    }

    override fun onClick(view: View?) {
        var check = true
        if (firstTimeET.text.isEmpty() ||  secondTimeET.text.isEmpty()) {
            return
        }
        val firstTime = firstTimeET.text.toString()
        val secondTime = secondTimeET.text.toString()

        var result = when (view?.id) {
            R.id.plusBTN -> Operation(firstTime, secondTime).addTimes()
            R.id.minusBTN -> Operation(firstTime, secondTime).subtractTimes()
            R.id.resetBTN -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                check = false
            }
            else -> ""
        }
        if (!check) resultTextTV.text = "Результат" else resultTextTV.text = result.toString()
    }
}