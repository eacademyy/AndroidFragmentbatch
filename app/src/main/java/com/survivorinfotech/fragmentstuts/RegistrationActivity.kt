package com.survivorinfotech.fragmentstuts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    lateinit var btnReg:Button
    lateinit var etUseremail:EditText
    lateinit var etUserpass:EditText

    lateinit var uemail:String
    lateinit var upass:String

    lateinit var regDataSp:SharedPreferences
    lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        regDataSp = getSharedPreferences("regdatasp", Context.MODE_PRIVATE)
        editor = regDataSp.edit()

        btnReg = findViewById(R.id.btnReg)
        etUseremail = findViewById(R.id.etUseremail)
        etUserpass = findViewById(R.id.etUserpass)

        btnReg.setOnClickListener {
            saveData()
        }


    }

    private fun saveData() {
        uemail = etUseremail.text.toString()
        upass = etUserpass.text.toString()

        editor.putString("useremail",uemail)
        editor.putString("userpass",upass)
        editor.apply()
        val i = Intent(this@RegistrationActivity,MainActivity::class.java)
        startActivity(i)
    }
}