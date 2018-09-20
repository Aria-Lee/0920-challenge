package com.example.aria.a0920

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.show.*
import okhttp3.*
import java.io.IOException
import java.nio.file.attribute.DosFileAttributes

class OkHttp {

    val client = OkHttpClient().newBuilder().build()

        // 建立Request，設置連線資訊


    fun get(activity: Activity){
        val  request = Request.Builder()
                .url("http://172.20.10.7:8888/api/")
                .build()
        // 建立Call
        val call = client.newCall(request)
        call.enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
             Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.wtf("aaaaa", "get" + result)
                if(!result .equals("")){
                    activity.runOnUiThread {
                    Toast.makeText(activity, result, Toast.LENGTH_LONG).show()}
//                    activity.runOnUiThread {
//                    (activity as LoginActivity).logined(result)}
                }
            }
        })
    }

    fun post(Data: Data,activity:Activity){
        val  formBody = FormBody
                .Builder()
                .add("username", Data.username)
                .add("account", Data.account)
                .add("password", Data.password)
                .add("email", Data.email)
                .add("phone",Data.phone)
                .build()
        val request =  Request.Builder()
                .url("http://172.20.10.7:8888/api/create")
                .post(formBody)
                .build()
        val call = client.newCall(request);
        call.enqueue(object :Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?){
                val responseStr = response!!.body()!!.string()
                if(responseStr == "create success!") activity.finish()
                else{
                activity.runOnUiThread {
                    (activity as SignupActivity).showAlertDialog(responseStr)
                }
                Log.wtf("aaaaa", responseStr)}
            }
        })

    }

    fun login(account: String, password: String, activity: Activity){
        Log.wtf("aaaaa", account +"   "+password)
        val  formBody = FormBody
                .Builder()
                .add("account", account)
                .add("password", password)
                .build()
        val request =  Request.Builder()
                .url("http://172.20.10.7:8888/api/login")
                .post(formBody)
                .build()
        val call = client.newCall(request);
        call.enqueue(object :Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaaa", "Log fail")
            }

            override fun onResponse(call: Call?, response: Response?){
                val responseStr = response!!.body()!!.string()
                if(responseStr == "") activity.finish()
                else{
                    activity.runOnUiThread {
                        (activity as LoginActivity).login(responseStr)
                    }
                    Log.wtf("aaaaa", responseStr)}
            }
        })
    }

    fun logout(){
        val  request = Request.Builder()
                .url("http://172.20.10.7:8888/api/logout")
                .build()
        // 建立Call
        val call = client.newCall(request)
        call.enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.wtf("aaaa", result)
//                val Data = Gson().fromJson(result, Data::class.java)
//                Log.wtf("aaaa", Data.username)
//                activity.runOnUiThread {
//                    activity.username.setText(Data.username)
//                    activity.email.setText(Data.email)
//                    activity.phone.setText(Data.phone)
//                }
            }
        })
    }
}