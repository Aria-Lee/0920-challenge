package com.example.aria.a0920

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        origin()
        edit_save.setOnClickListener {
            save()
        }
    }


    val dataString = intent.getStringExtra("DataString")
    val originData = Gson().fromJson(dataString, Data::class.java)
    fun origin(){
        edit_username.setText(originData.username)
        edit_email.setText(originData.email)
        edit_phone.setText(originData.phone)
    }


    fun save(){
        val Data = originData
        Data.username = edit_username.text.toString()
        Data.email = edit_email.text.toString()
        Data.phone = edit_phone.text.toString()
    }
}
