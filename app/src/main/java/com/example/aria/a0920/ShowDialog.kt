package com.example.aria.a0920

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.example.aria.a0920.Data.OkHttp
import kotlin.reflect.KFunction3

class ShowDialog {


    fun showResponseAlertDialog(string: String, activity: Activity) {
        AlertDialog.Builder(activity)
                .setTitle("提醒")
                .setMessage(string)
                .setPositiveButton("OK") { dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
    }

    fun showTwoChoiceAlerDialog(title: String, message: String, token: String, func: KFunction3<OkHttp, @ParameterName(name = "token") String, @ParameterName(name = "activity") Activity, Unit>, activity: Activity) {
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("確定") { dialog, which ->
                    func.invoke(OkHttp(),token, activity)
//                    val pref = Preference(this)
//                    OkHttp().logout(data.api_token, this)
//                    pref.deleteData()
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
                }
                .setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
    }
}