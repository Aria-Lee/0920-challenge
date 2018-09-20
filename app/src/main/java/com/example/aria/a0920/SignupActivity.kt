package com.example.aria.a0920

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        new_save.setOnClickListener {
            save()
        }
    }

    fun save() {

        val username = setUsername.text.toString()
        val account = setAccount.text.toString()
        val password = setPassword.text.toString()
        val email = setEmail.text.toString()
        val phone = setPhone.text.toString()
        val signupData = Data(username, account, password, email, phone)
        OkHttp().post(signupData,this)
    }

    fun showAlertDialog(string: String){
        AlertDialog.Builder(this@SignupActivity)
                .setTitle("提醒")
                .setMessage(string)
                .setPositiveButton("OK"){dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
    }
}