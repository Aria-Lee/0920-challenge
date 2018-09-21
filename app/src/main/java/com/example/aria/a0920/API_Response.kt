package com.example.aria.a0920

import com.example.aria.a0920.Data.Data
import okhttp3.Response
import org.json.JSONObject

class API_Response(response: Response?){
    val responseStr = response!!.body()!!.string()
    val response = JSONObject(responseStr)

    fun status(): String{
        return response.get("status").toString()
    }

    fun error_message(): String{
        return response.get("error_message").toString()
    }

    fun data(): String{
        return response.get("data").toString()
    }

}