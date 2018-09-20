package com.example.aria.a0920

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.show.*
import android.view.KeyEvent.KEYCODE_BACK



class ShowActivity : AppCompatActivity() {
    var dataString: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show)
        dataString = intent.getStringExtra("DataString")

        edit.setOnClickListener {  }
        delete.setOnClickListener {  }
//        getData()
        showData()
    }


//    val dataString = intent.getStringExtra("DataString")

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            showDeletAlertDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDeletAlertDialog(){
        AlertDialog.Builder(this@ShowActivity)
                .setTitle("提醒")
                .setMessage("確定要登出嗎?")
                .setPositiveButton("確定"){dialog, which ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }
                .setNegativeButton("取消"){dialog, which ->
                    dialog.cancel()
                }
                .create()
                .show()
    }

    fun showData(){
        val Data = Gson().fromJson(dataString, Data::class.java)
        username.setText(Data.username)
        email.setText(Data.email)
        phone.setText(Data.phone)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {   //確定按下退出鍵

            Toast.makeText(this, "無可返回頁面", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

//    fun getData(){
//        OkHttp().get(this)
//    }


    fun edit(){
    val editIntent = Intent(this, EditActivity::class.java)
    editIntent.putExtra("DataString", dataString)
    startActivity(editIntent)
}

}
