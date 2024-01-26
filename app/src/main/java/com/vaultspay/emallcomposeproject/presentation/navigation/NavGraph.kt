package com.vaultspay.emallcomposeproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vaultspay.emallcomposeproject.presentation.screens.SigninScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignIn.route
    ) {
        composable(route = Screens.SignIn.route) {
            SigninScreen(navController = navController)
        }
    }
}