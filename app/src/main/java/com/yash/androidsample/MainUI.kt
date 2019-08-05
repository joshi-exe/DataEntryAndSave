package com.yash.androidsample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_ui.*

class MainUI : AppCompatActivity() {
    lateinit var mainusername: TextView
    val status: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui)

        val alertButton = findViewById<Button>(R.id.alertbutton)
        val alertStatus = findViewById<Button>(R.id.alertstatus)
        val loggedUser = Utility.Instance.loggedinuser


        mainusername = findViewById(R.id.mainusername)
        if (loggedUser != null) {
            Utility.alertPref = Utility.alertPref + loggedUser.username.toString()
            mainusername.text = "Hello, " + loggedUser.username.toString() + "!"
        }

        alertButton.setOnClickListener {
            val intent = Intent(this, AlertEmail::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val isAlertEnable = sharedPreferences.getBoolean(Utility.alertPref, false)
        if (isAlertEnable) {
            alertstatus.setBackgroundColor(Color.GREEN)
            alertstatus.text = "Alerts - ON"
        } else {
            alertstatus.setBackgroundColor(Color.RED)
            alertstatus.text = "Alerts - OFF"
        }

    }
}
