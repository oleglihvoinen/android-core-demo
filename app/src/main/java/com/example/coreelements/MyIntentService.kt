package com.example.coreelements

import android.app.IntentService
import android.content.Intent

@Suppress("DEPRECATION")
class MyIntentService : IntentService("MyIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        val name = intent?.getStringExtra("animal_name").orEmpty()
        try { Thread.sleep(2000) } catch (_: InterruptedException) {}
        val b = Intent("com.example.ACTION_DONE").apply {
            putExtra("message", "$name info processed!")
        }
        sendBroadcast(b)
    }
}
