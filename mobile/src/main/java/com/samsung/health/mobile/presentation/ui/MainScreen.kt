package com.samsung.health.mobile.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.samsung.health.data.TrackedData
import com.samsung.health.mobile.presentation.navigation.Routes

@Composable
fun MainScreen(
    results: List<TrackedData>,
    navController: NavController
) {
    // Get the latest heartbeat value using the correct property name 'hr'
    val latestHeartbeat by remember(results) {
        derivedStateOf {
            results.lastOrNull()?.hr ?: 0  // Changed from heartRate to hr
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )
        
        // Live updating heartbeat display
        Text(
            text = if (latestHeartbeat > 0) "Heart Rate: $latestHeartbeat BPM" else "Waiting for heartbeat data...",
            color = if (latestHeartbeat > 0) Color.Red else Color.Gray,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        // Real-time data count
        Text(
            text = "Live Data: ${results.size} records",
            color = Color.Cyan,
            fontSize = 14.sp
        )
        
        // Show timestamp of last update
        if (results.isNotEmpty()) {
            Text(
                text = "Last Update: ${System.currentTimeMillis()}",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        
        // Navigation buttons
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = { navController.navigate(Routes.StepCounter) }) {
            Text("Go to Step Counter")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = { navController.navigate(Routes.GpsTracking) }) {
            Text("Go to GPS Tracking")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ListView(results)
    }
}