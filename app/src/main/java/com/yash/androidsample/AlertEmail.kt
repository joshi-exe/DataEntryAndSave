package com.yash.androidsample

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

class AlertEmail : AppCompatActivity() {
    lateinit var inputemail: EditText
    lateinit var alertview: ConstraintLayout
    lateinit var alertmessage: TextView
    lateinit var switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_email)

        alertview = findViewById<ConstraintLayout>(R.id.setupemailview)
        alertmessage = findViewById<TextView>(R.id.descriptionlabel)
        switch = findViewById<Switch>(R.id.alertswitch)

        alertmessage.visibility = View.VISIBLE
        alertmessage.text = "Alerts are turned off. Enable to get alerts on your E-mail"
        alertview.visibility = View.INVISIBLE

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val isAlertEnable = sharedPreferences.getBoolean(Utility.alertPref, false)

        switch.isChecked = isAlertEnable
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(Utility.alertPref, true)
                alertmessage.visibility = View.INVISIBLE
                alertview.visibility = View.VISIBLE
                checkAlertEmailIsSet()
            } else {
                editor.putBoolean(Utility.alertPref, false)
                alertview.visibility = View.INVISIBLE
                alertmessage.visibility = View.VISIBLE
                alertmessage.text = "Alerts are turned off. Enable to get alerts on your E-mail"
            }
            editor.commit()
        }

        if (isAlertEnable) {
            alertmessage.visibility = View.INVISIBLE
            alertview.visibility = View.VISIBLE
            checkAlertEmailIsSet()
        } else {
            alertview.visibility = View.INVISIBLE
            alertmessage.visibility = View.VISIBLE
            alertmessage.text = "Alerts are turned off. Enable to get alerts on your E-mail"
        }

        inputemail = findViewById(R.id.email)
        val emailsubmit = findViewById<Button>(R.id.emailsubmit)

        emailsubmit.setOnClickListener {
            val emailobject = inputemail.text.toString()
            val usernameobj = Utility.Instance.loggedinuser
            if (emailobject.isNotEmpty() && emailobject.contains(".com") && emailobject.contains("@") && usernameobj != null) {
                Toast.makeText(this, "Alerts turned on!", Toast.LENGTH_LONG).show()
                val emailobj = AlertModel(usernameobj.username.toString(),emailobject)
                val DBobject = DBHandler(this, null, null, 1)
                DBobject.Addemail(emailobj)
                checkAlertEmailIsSet()
            } else
                Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_LONG).show()
        }
    }

    fun checkAlertEmailIsSet() {
        val DBobject = DBHandler(this, null, null, 1)
        val getusername = Utility.Instance.loggedinuser
        if(getusername != null)
        {
        val getEmail = DBobject.getemail(getusername.username.toString())
            if(getEmail != null) {
                alertview.visibility = View.INVISIBLE
                alertmessage.visibility = View.VISIBLE
                alertmessage.text = "Alerts have been setup for ${getEmail.userEmail}"
            }
            else
                alertmessage.text = "Alerts are turned off. Enable to get alerts on your E-mail"
        }
    }
}
