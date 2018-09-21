package com.example.aria.a0920

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.aria.a0920.Activity.ShowActivity

class Login{
    fun login(string:String,activity:Activity){
        val intent = Intent(activity, ShowActivity::class.java)
        intent.putExtra("DataString", string)
        Log.wtf("aaaaa", string)
        activity.startActivity(intent)
    }
}

