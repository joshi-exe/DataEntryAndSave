package com.yash.androidsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AlertEmail : AppCompatActivity() {
    lateinit var inputemail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_email)

        inputemail = findViewById(R.id.email)
        val emailsubmit = findViewById<Button>(R.id.emailsubmit)

        emailsubmit.setOnClickListener {
            val emailobject = inputemail.text.toString()
            if (emailobject.isNotEmpty() && emailobject.contains(".com") && emailobject.contains("@")) {
                Toast.makeText(this, "Alerts turned on!", Toast.LENGTH_LONG).show()
//                AlertModel(Utility.Instance.loggedinuser.username,emailobject)
            }
            else
                Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_LONG).show()
        }
    }
}
