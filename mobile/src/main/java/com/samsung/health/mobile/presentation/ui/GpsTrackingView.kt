package com.samsung.health.mobile.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GpsTrackingView() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GPS Tracking",
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Distance: 0.00 km",
            fontSize = 32.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Start moving to track your distance!",
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
}
