package com.example.aria.a0920.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.aria.a0920.Data.OkHttp
import com.example.aria.a0920.Data.Preference
import com.example.aria.a0920.R
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val pref = Preference(this)
        val token = pref.getData()
        if(token != null) OkHttp().logined(token, this)

        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

//        login.setOnClickListener {
//            var account = account.text.toString()
//            val password = password.text.toString()
//           OkHttp().login(account,password,this)
//
//        }

        login.setOnClickListener {
            login()
        }
    }

    fun login(){
        val intent = Intent(this, ShowActivity::class.java)
        startActivity(intent)
    }
}
