package com.samsung.health.mobile.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import com.samsung.health.mobile.presentation.navigation.AppNavigation
import com.samsung.health.mobile.presentation.theme.PaceBeatsTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var trackedDataList = mutableStateOf(emptyList<com.samsung.health.data.TrackedData>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateTrackedDataFromIntent(intent)
        setContent {
            PaceBeatsTheme {
                AppNavigation(trackedDataList.value)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent: $intent")
        updateTrackedDataFromIntent(intent)
    }

    private fun updateTrackedDataFromIntent(intent: Intent) {
        val message = intent.getStringExtra("message")
        trackedDataList.value = if (!message.isNullOrEmpty()) {
            try {
                HelpFunctions.decodeMessage(message)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to decode message: $message", e)
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }
}