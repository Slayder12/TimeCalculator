package com.example.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar
    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText
    private lateinit var plusBTN: Button
    private lateinit var minusBTN: Button
    private lateinit var resultTextTV: TextView

    private  var defaultTextColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = getString(R.string.title_text)
        toolbarMain.subtitle = getString(R.string.version_text)
        toolbarMain.setLogo(R.drawable.calculate)
        toolbarMain.titleMarginStart = 80

        firstTimeET = findViewById(R.id.first_timeET)
        secondTimeET = findViewById(R.id.second_timeET)
        resultTextTV = findViewById(R.id.result_textTV)

        defaultTextColor = resultTextTV.currentTextColor

        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)

        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                resultTextTV.text = getString(R.string.text_result)
                resultTextTV.setTextColor(defaultTextColor)
                Toast.makeText(
                    applicationContext,
                    getString(R.string.data_cleared_text),
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.app_closed_text),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onClick(view: View?) {
        if (firstTimeET.text.isEmpty() ||  secondTimeET.text.isEmpty()) {
            return
        }
        val firstTime = firstTimeET.text.toString().lowercase()
        val secondTime = secondTimeET.text.toString().lowercase()

        val result = when (view?.id) {
            R.id.plusBTN -> Operation(firstTime, secondTime).addTimes()
            R.id.minusBTN -> Operation(firstTime, secondTime).subtractTimes()
            else -> ""
        }
        resultTextTV.text = result

        resultTextTV.setTextColor(Color.RED)

        Toast.makeText(
            this,
            "${getString(R.string.text_result)}: ${resultTextTV.text}",
            Toast.LENGTH_SHORT
        ).show()
    }
}