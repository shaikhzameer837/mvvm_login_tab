package com.app.myapplication.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast


fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}
fun loge(message: String) {
    Log.e("THIS ERROR", "ERROR -> $message")
}