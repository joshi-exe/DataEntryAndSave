package com.yash.androidsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registration : AppCompatActivity() {
    lateinit var regname: EditText
    lateinit var regpassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        regname = findViewById(R.id.name)
        regpassword = findViewById(R.id.password)

        val regbutton = findViewById<Button>(R.id.regbutton)
        val loginpage = findViewById<Button>(R.id.loginpage)

        regbutton.setOnClickListener {
            val nameobject: String = regname.text.toString()
            val passwordobject: String = regpassword.text.toString()

            if (nameobject.isNotEmpty() && passwordobject.isNotEmpty()) {
                val modelobject = UserModel(nameobject, passwordobject)

                val DBobject = DBHandler(this, null, null, 1)
                val success: Boolean = DBobject.AddUser(modelobject)

                if(success) {
                    Utility.Instance.loggedinuser = modelobject

                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainUI::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User already exist!", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this, "Please enter a valid username and password!", Toast.LENGTH_LONG).show()
            }
        }

        loginpage.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}
