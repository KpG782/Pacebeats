package com.samsung.health.mobile.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Homepage(
    navController: NavHostController,       // ← add this!
){
    Column(){
        Text(
            text = "Homepage",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF003C94)
        )
    }
}