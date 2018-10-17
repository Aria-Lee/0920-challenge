package com.example.aria.a0920.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.show.*
import com.example.aria.a0920.Data.Data
import com.example.aria.a0920.Data.OkHttp
import com.example.aria.a0920.Data.Preference
import com.example.aria.a0920.R
import com.example.aria.a0920.ShowDialog
import com.google.gson.Gson


class ShowActivity : AppCompatActivity() {
    lateinit var dataString: String
    lateinit var data: Data
    val dialog = ShowDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show)
        dataString = intent.getStringExtra("DataString")
        data = Gson().fromJson(dataString, Data::class.java)

        saveToken()
        showData()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
//            showDeletAlertDialog()
            dialog.showTwoChoiceAlerDialog("提醒",
                    "確定要登出嗎?",
                    data.api_token,
                    OkHttp::logout,
                    this)
            return true
        }

        if (item.itemId == R.id.edit_value) {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("DataString", dataString)
            startActivity(intent)
        }

        if (item.itemId == R.id.edit_password) {
            return true
        }

        if (item.itemId == R.id.delete_acouunt) {
            dialog.showTwoChoiceAlerDialog("提醒",
                    "確定要登出嗎?",
                    data.api_token,
                    OkHttp::delete,
                    this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

//    fun showDeletAlertDialog(){
//        AlertDialog.Builder(this@ShowActivity)
//                .setTitle("提醒")
//                .setMessage("確定要登出嗎?")
//                .setPositiveButton("確定"){dialog, which ->
//                    val pref = Preference(this)
//                    OkHttp().logout(data.api_token, this)
//                    pref.deleteData()
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                }
//                .setNegativeButton("取消"){dialog, which ->
//                    dialog.cancel()
//                }
//                .create()
//                .show()
//    }

    fun logoutAndDelete(){
        val pref = Preference(this)
        pref.deleteData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showData() {
        username.setText(data.username)
        email.setText(data.email)
        phone.setText(data.phone)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {   //確定按下退出鍵

            Toast.makeText(this, "無可返回頁面", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun edit() {
        val editIntent = Intent(this, EditActivity::class.java)
        editIntent.putExtra("DataString", dataString)
        startActivity(editIntent)
    }

    fun saveToken() {
        val pref = Preference(this)
        pref.setData(data.api_token)
    }

    @Override
    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        if (menu != null) {
            if (menu.javaClass.getSimpleName().equals("MenuBuilder")) {
                try {
                    val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", Boolean::class.java)
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return super.onMenuOpened(featureId, menu)
    }


}
