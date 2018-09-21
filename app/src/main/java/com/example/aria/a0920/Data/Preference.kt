package com.example.aria.a0920.Data

import android.content.Context

class Preference(context: Context) {
    private val pref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)

    fun setData(string: String) {
        pref.edit().putString("token", string).apply()
    }

    fun getData():String?{
        return  pref.getString("token", null)
    }

    fun deleteData():Boolean{
            pref.edit().remove("token").apply()
            return true
    }
}