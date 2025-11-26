package com.example.coreelements

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val msg = intent.getStringExtra("message").orEmpty()
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}
