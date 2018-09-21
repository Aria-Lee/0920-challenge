package com.example.aria.a0920.Data

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.aria.a0920.API_Response
import com.example.aria.a0920.Activity.LoginActivity
import com.example.aria.a0920.Activity.SignupActivity
import com.example.aria.a0920.Login
import com.example.aria.a0920.ShowDialog
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class OkHttp {

    val client = OkHttpClient().newBuilder().build()
    val login = Login()
    val dialog = ShowDialog()
    lateinit var api_Response: API_Response
    // 建立Request，設置連線資訊


    fun logined(token: String, activity: Activity) {
        val formBody = FormBody
                .Builder()
                .add("api_token", token)
                .build()
        val request = Request.Builder()
                .url("http://172.20.10.7:8888/api/read")
                .post(formBody)
                .build()
        // 建立Call

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                api_Response = API_Response(response)
                if (api_Response.status() == "success") {
                    activity.runOnUiThread {
                        //                        Toast.makeText(activity, result, Toast.LENGTH_LONG).show()}
                        login.login(api_Response.data(), activity)
                    }
                }
// else {
//                    Toast.makeText(activity, api_Response.error_message(), Toast.LENGTH_LONG).show()
//                }
            }
        })
    }

    fun post(Data: Data, activity: Activity) {
        val formBody = FormBody
                .Builder()
                .add("username", Data.username)
                .add("account", Data.account)
                .add("password", Data.password)
                .add("email", Data.email)
                .add("phone", Data.phone)
                .build()
        val request = Request.Builder()
                .url("http://172.20.10.7:8888/api/create")
                .post(formBody)
                .build()
        val call = client.newCall(request);
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                api_Response = API_Response(response)
                if (api_Response.status() == "success") {
                    activity.runOnUiThread {
                        login.login(api_Response.data(), activity)
                    }
                } else {
                    activity.runOnUiThread {
                        (activity as SignupActivity).showAlertDialog(api_Response.error_message())
                    }
                }
            }
        })

    }

    fun login(account: String, password: String, activity: Activity) {
        Log.wtf("aaaaa", account + "   " + password)
        val formBody = FormBody
                .Builder()
                .add("account", account)
                .add("password", password)
                .build()
        val request = Request.Builder()
                .url("http://172.20.10.7:8888/api/login")
                .post(formBody)
                .build()
        val call = client.newCall(request);
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaaa", "Log fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                api_Response = API_Response(response)

                if (api_Response.status() == "fail") {
                    dialog.showAlertDialog(api_Response.error_message(), activity)
                } else {
                    activity.runOnUiThread {
                        login.login(api_Response.data(), activity)
                    }
                }
            }
        })
    }

    fun logout(token: String,activity: Activity) {
        val formBody = FormBody
                .Builder()
                .add("api_token", token)
                .build()
        val request = Request.Builder()
                .url("http://172.20.10.7:8888/api/logout")
                .post(formBody)
                .build()
        // 建立Call
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.wtf("aaaa", "fail")
            }

            override fun onResponse(call: Call?, response: Response?) {
                api_Response = API_Response(response)
                if(api_Response.status() == "fail"){
                    dialog.showAlertDialog(api_Response.error_message(),activity)
                }

            }
        })
    }
}