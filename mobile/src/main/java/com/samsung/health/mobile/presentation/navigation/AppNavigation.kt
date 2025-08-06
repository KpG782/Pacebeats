package com.samsung.health.mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samsung.health.mobile.presentation.ui.Slide
import com.samsung.health.mobile.presentation.ui.WelcomeScreen
import com.samsung.health.mobile.presentation.ui.auth.SignInScreen
import com.samsung.health.mobile.presentation.ui.auth.SignUpScreen
import com.samsung.health.mobile.presentation.ui.MainScreen
import com.samsung.health.data.TrackedData
import com.samsung.health.mobile.presentation.ui.StepCounterView
import com.samsung.health.mobile.presentation.ui.GpsTrackingView

@Composable
fun AppNavigation(results: List<TrackedData>) {
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
            SignInScreen(
                navController      = navController,
                onSignedIn         = { navController.navigate(Routes.Heartbeat) },
                onNavigateToSignUp = { navController.navigate(Routes.SignUp) }
            )
        }

        composable(Routes.SignUp) {
            SignUpScreen(
                navController        = navController,
                onSignedUp           = { navController.navigate(Routes.Heartbeat) },
                onNavigateToSignIn   = { navController.navigate(Routes.SignIn) }
            )
        }

        composable(Routes.Heartbeat) {
            MainScreen(
                results = results,
                navController = navController
            )
        }
        composable(Routes.StepCounter) {
            StepCounterView()
        }
        composable(Routes.GpsTracking) {
            GpsTrackingView()
        }
    }
}