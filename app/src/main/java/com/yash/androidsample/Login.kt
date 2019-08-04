package com.yash.androidsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

class Login : AppCompatActivity() {
    lateinit var logusername: TextView
    lateinit var loguserpassword: EditText
    lateinit var DBobject: DBHandler
    lateinit var users: ArrayList<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logusername = findViewById(R.id.username)
        loguserpassword = findViewById(R.id.userpassword)

        val logbutton = findViewById<Button>(R.id.logbutton)
        val regpage = findViewById<Button>(R.id.regpage)
        val dropdown = findViewById<Spinner>(R.id.userlist)

        DBobject = DBHandler(this, null, null, 1)
        users = DBobject.GetUsers()

        if (users.count() > 0) {
            val userlist = ArrayList<String>()
            for (i in 0..users.count() - 1) {
                userlist.add(users[i].username.toString())
            }
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userlist)
            dropdown.adapter = arrayAdapter
        } else {
            logbutton.isEnabled = false
        }

        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Utility.Instance.loggedinuser = users[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        logbutton.setOnClickListener {
            val checkpassword = loguserpassword.text.toString()
            val loggedUser = Utility.Instance.loggedinuser
            if (checkpassword.length > 0 && loggedUser != null) {
                val storedpassword = loggedUser.userpassword.toString()
                if (checkpassword == storedpassword) {
                    val intent = Intent(this, MainUI::class.java)
                    startActivity(intent)
                } else Toast.makeText(this, "Please enter correct password!", Toast.LENGTH_LONG).show()
            } else Toast.makeText(this, "Please enter the password!", Toast.LENGTH_LONG).show()
        }

        regpage.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
}
