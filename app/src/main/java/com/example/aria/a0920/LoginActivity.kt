package com.example.aria.a0920

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        OkHttp().get(this)

        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            var account = account.text.toString()
            val password = password.text.toString()
           OkHttp().login(account,password,this)

        }
    }

    fun login(string:String){
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("DataString", string)
        Log.wtf("aaaaa", string)
        startActivity(intent)
    }

    fun logined(string: String){
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("DataString", string)
        Log.wtf("aaaaa", string)
        startActivity(intent)}
}
