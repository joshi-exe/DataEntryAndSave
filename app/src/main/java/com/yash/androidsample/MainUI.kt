package com.yash.androidsample

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_ui.*

class MainUI : AppCompatActivity() {
    lateinit var mainusername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui)

        val alertButton = findViewById<Button>(R.id.alertbutton)
        val alertStatus = findViewById<Button>(R.id.alertstatus)
        val loggedUser = Utility.Instance.loggedinuser


        mainusername = findViewById(R.id.mainusername)

        val checkpref = Utility.alertPref
        val checkstatuspref = Utility.statusPref

        if (loggedUser != null && !checkpref.contains(loggedUser.username.toString()) && !checkpref.contains(loggedUser.username.toString())) {
            Utility.alertPref = "switch_pref_" + loggedUser.username.toString()
            Utility.statusPref = "status_pref_" + loggedUser.username.toString()
        }

        mainusername.text = "Hello, " + loggedUser!!.username.toString() + "!"

        alertButton.setOnClickListener {
            val intent = Intent(this, AlertEmail::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isAlertEnable: Boolean = preferences.getBoolean(Utility.alertPref, false)
        val isEmailFound: Boolean = preferences.getBoolean(Utility.statusPref, false)
        if (isAlertEnable && isEmailFound) {
            alertstatus.setBackgroundColor(Color.GREEN)
            alertstatus.text = "Alerts - ON"
        } else {
            alertstatus.setBackgroundColor(Color.RED)
            alertstatus.text = "Alerts - OFF"
        }

    }
}
