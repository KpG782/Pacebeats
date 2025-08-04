// mobile/src/main/java/com/samsung/health/mobile/presentation/navigation/AppNavigation.kt
package com.samsung.health.mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samsung.health.mobile.presentation.ui.Slide
import com.samsung.health.mobile.presentation.ui.WelcomeScreen
import com.samsung.health.mobile.presentation.ui.MainScreen
import com.samsung.health.data.TrackedData
import com.samsung.health.mobile.presentation.ui.auth.signIn.LoginPage
import com.samsung.health.mobile.presentation.ui.auth.signUp.LoginPage
import com.samsung.health.mobile.presentation.ui.auth.signUp.EmailVerification
import com.samsung.health.mobile.presentation.ui.auth.signUp.EmailVerification2
import com.samsung.health.mobile.presentation.ui.accountCreation.ProfileSetup


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // The three onboarding slides
    val slides = listOf(
        Slide(1, "Turn every step into progress"),
        Slide(2, "Music that moves with you"),
        Slide(3, "Feel better, run farther")
    )

    NavHost(
        navController    = navController,
        startDestination = Routes.Welcome
    ) {
        composable(Routes.Welcome) {
            WelcomeScreen(
                navController = navController,
                slides        = slides
            )
        }
        composable(Routes.SignIn) {
            LoginPage(
                navController      = navController,
                onSignedIn         = { navController.navigate(Routes.Heartbeat) },
                onNavigateToSignUp = { navController.navigate(Routes.SignUp) }
            )
        }

        composable(Routes.SignUp) {
            LoginPage(
                navController        = navController,
                onSignedUp           = { navController.navigate(Routes.Heartbeat) },
                onNavigateToSignIn   = { navController.navigate(Routes.SignIn) },
                emailVerification    = { navController.navigate(Routes.SignUp2)}
            )
        }

        composable(Routes.SignUp2) {
            EmailVerification(
                navController = navController,
                emailVerification2   = {navController.navigate(Routes.SignUp3)}
            )
        }

        composable(Routes.SignUp3) {
            EmailVerification2(
                navController = navController,
                profileSetup   = {navController.navigate(Routes.AccountCreation)}
            )
        }

        composable(Routes.AccountCreation) {
            ProfileSetup(
                navController = navController
            )
        }

        composable(Routes.Heartbeat) {
            MainScreen(
                results = emptyList<TrackedData>()
            )
        }
    }
}
