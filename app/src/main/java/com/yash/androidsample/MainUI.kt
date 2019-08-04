package com.yash.androidsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainUI : AppCompatActivity() {
    lateinit var mainusername: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui)

//        if (status) {
//            alertstatus.setBackgroundColor(Color.GREEN)
//            alertstatus.text = "Alerts - ON"
//        }
//        else {
//            alertstatus.setBackgroundColor(Color.RED)
//            alertstatus.text = "Alerts - OFF"
//        }

        val alertButton = findViewById<Button>(R.id.alertbutton)
        val alertStatus = findViewById<Button>(R.id.alertstatus)
        mainusername = findViewById(R.id.mainusername)
        val loggedUser = Utility.Instance.loggedinuser
        if (loggedUser != null) {
            mainusername.text = loggedUser.username.toString()
        }

        alertButton.setOnClickListener {
            val intent = Intent(this, AlertEmail::class.java)
            startActivity(intent)
        }
    }
}
