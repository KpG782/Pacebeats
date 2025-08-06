// LoginPage.kt
package com.samsung.health.mobile.presentation.ui.auth.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.samsung.health.mobile.R

@Composable
fun LoginPage(
    navController: NavHostController,       // ← add this!
    onSignedIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    passwordSuccessful: () -> Unit,
    homePage: () -> Unit,
    forgetPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.Start)
                .size(44.dp))
        {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier.size(44.dp),
                tint = Color(0xFF0050C7)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "PaceBeats Logo",
            modifier = Modifier
                .size(60.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sign in",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF003C94)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ← Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ← Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
        )

        // ← Forgot password
        TextButton(
            onClick = forgetPassword,
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 26.dp),
        ) {
            Text(
                text = "Forgot password?",
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color(0xFF7B7B7B),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // ← Sign in button
        Button(
            onClick = homePage,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Sign in", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("or continue with")

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        ) {
            Button(
                onClick = { /* */ },
                modifier = Modifier
                    .height(56.dp)
                    .width(86.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021026))
            ){
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(32.dp)
                )
            }

            Button(
                onClick = { /* */ },
                modifier = Modifier
                    .height(56.dp)
                    .width(86.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021026))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spotify),
                    contentDescription = "Spotify",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}