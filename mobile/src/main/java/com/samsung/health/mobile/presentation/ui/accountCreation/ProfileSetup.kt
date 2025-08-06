package com.samsung.health.mobile.presentation.ui.accountCreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable

fun ProfileSetup(
    navController: NavHostController,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile Setup",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color(0xFF003C94)
        )

        Text(
            text = "What's your gender?",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color(0xFF003C94),
            textAlign = TextAlign.Center
        )

        Row(){
            Column(){
                Card() {

                }

                Text(
                    text = "Male",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }

            Column(){
                Card() {

                }

                Text(
                    text = "Female",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
        }

        TextButton(
            onClick = {},
        ) {
            Text(
                text = "Skip for now",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF0050C7),
                textAlign = TextAlign.Center
            )

        }
    }
}