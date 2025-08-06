// mobile/src/main/java/com/samsung/health/mobile/presentation/ui/auth/LoginPage.kt
package com.samsung.health.mobile.presentation.ui.auth.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation    // ← add this
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import com.samsung.health.mobile.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samsung.health.mobile.presentation.navigation.Routes

@Composable

fun LoginPage(
    navController: NavHostController,
    onSignedUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    emailVerification: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ){
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(44.dp))
                {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.size(44.dp),
                        tint = Color(0xFF0050C7)
                    )
                }
            }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "PaceBeats Logo",
            modifier = Modifier
                .size(60.dp)
        )

        Text(
            text = "Sign up",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF003C94)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text("Email") },
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()  // now resolves
        )

        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text("Confirm Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()  // and here
        )

        Button(
            onClick = emailVerification,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                "Sign up",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Text("or continue with")

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
