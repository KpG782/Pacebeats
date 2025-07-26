// MainActivity.kt
package com.samsung.health.mobile.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.samsung.health.mobile.presentation.navigation.AppNavigation
import com.samsung.health.mobile.presentation.theme.PaceBeatsTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaceBeatsTheme {
                AppNavigation()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // If you need to handle incoming Intents (e.g. from Health SDK), do so here
        Log.i(TAG, "onNewIntent: $intent")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }
}
