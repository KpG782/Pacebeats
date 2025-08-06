package com.samsung.health.mobile.data

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.samsung.health.mobile.presentation.MainActivity

private const val TAG = "DataListenerService"
private const val MESSAGE_PATH = "/msg"

class DataListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)

        val value = messageEvent.data.decodeToString()
        Log.i(TAG, "onMessageReceived(): $value")
        when (messageEvent.path) {
            MESSAGE_PATH -> {
                Log.i(TAG, "Service: message (/msg) received: $value")

                if (value != "") {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("message", value)
                    )
                } else {
                    Log.i(TAG, "value is an empty string")
                }
            }
        }
    }
}