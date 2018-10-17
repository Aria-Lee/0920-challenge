package com.example.aria.a0920.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.aria.a0920.Data.Data
import com.example.aria.a0920.Data.OkHttp
import com.example.aria.a0920.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.edit.*

class EditActivity : AppCompatActivity() {
    lateinit var dataString : String
    lateinit var originData : Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        dataString = intent.getStringExtra("DataString")
        originData = Gson().fromJson(dataString, Data::class.java)

        origin()
        edit_save.setOnClickListener {
            save()
        }
    }

    fun origin(){
        edit_username.setText(originData.username)
        edit_email.setText(originData.email)
        edit_phone.setText(originData.phone)
    }


    fun save(){
        val data = originData
        data.username = edit_username.text.toString()
        data.email = edit_email.text.toString()
        data.phone = edit_phone.text.toString()
        OkHttp().update(data, this)
    }
}
