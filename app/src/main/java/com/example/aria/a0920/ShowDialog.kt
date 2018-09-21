package com.example.aria.a0920

import android.app.Activity
import android.support.v7.app.AlertDialog

class ShowDialog {
    fun showAlertDialog(string: String, activity:Activity){
        AlertDialog.Builder(activity)
                .setTitle("提醒")
                .setMessage(string)
                .setPositiveButton("OK"){dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
    }
}