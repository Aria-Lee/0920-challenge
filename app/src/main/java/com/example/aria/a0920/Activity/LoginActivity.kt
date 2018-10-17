package com.example.aria.a0920.Activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.aria.a0920.Data.OkHttp
import com.example.aria.a0920.Data.Preference
import com.example.aria.a0920.R
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        init()

        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            var account = account.text.toString()
            val password = password.text.toString()
            OkHttp().login(account, password, this)
//           selfLogin()
        }
    }

    fun init() {
        Glide.with(this).load(R.drawable.giphy).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView)
        val pref = Preference(this)
        val token = pref.getData()

        supportActionBar?.hide()
        lately.visibility=View.INVISIBLE


        if (token != null) OkHttp().logined(token, this)
        else
            Thread(Runnable {
                Thread.sleep(3000)
                imageGone()
            }).start()

    }

    fun imageGone(){
        this@LoginActivity.runOnUiThread {
            imageView.visibility = View.GONE
            supportActionBar?.show()
            lately.visibility= View.VISIBLE
        }
    }

//    fun selfLogin(){
//        val intent = Intent(this, ShowActivity::class.java)
//                startActivity(intent)
//    }

}
