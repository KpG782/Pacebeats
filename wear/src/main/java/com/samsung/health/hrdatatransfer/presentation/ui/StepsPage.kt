package com.samsung.health.hrdatatransfer.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import androidx.compose.ui.text.font.FontWeight
import com.samsung.health.hrdatatransfer.presentation.MainViewModel

@Composable
fun StepsPage(viewModel: MainViewModel) {
    val tracking by viewModel.stepTracking.collectAsState()
    val steps by viewModel.stepCount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.DirectionsWalk,
            contentDescription = "Steps",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Steps",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = steps.toString(),
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { viewModel.toggleStepTracking() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (tracking) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary
            ),
            modifier = Modifier.size(60.dp)
        ) {
            Text(
                text = if (tracking) "Stop" else "Start",
                color = MaterialTheme.colors.onSecondary,
                fontSize = 14.sp
            )
        }
    }
}