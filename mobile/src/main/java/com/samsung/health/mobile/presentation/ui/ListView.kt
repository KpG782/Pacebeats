package com.samsung.health.mobile.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.health.data.TrackedData
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ListView(results: List<TrackedData>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(results) { data ->
            TrackedDataItem(data)
        }
    }
}

@Composable
fun TrackedDataItem(data: TrackedData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Heart Rate: ${data.hr} BPM",  // Changed from data.heartRate to data.hr
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "IBI: ${data.ibi.joinToString(", ")}",  // This works with ArrayList<Int>
                color = Color.LightGray,
                fontSize = 14.sp
            )
            Text(
                text = "Time: ${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())}",  // Use current time since no timestamp
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
    }
}