package com.vaultspay.emallcomposeproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signin.SigninScreen
import com.vaultspay.emallcomposeproject.presentation.screens.auth_screens.signup.SignupScreen
import com.vaultspay.emallcomposeproject.presentation.screens.home_screens.HomeScreen
import com.vaultspay.emallcomposeproject.utils.NavigationUtil.navigateSingleTopTo

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SignIn.route
    ) {
        composable(route = Screens.SignIn.route) {
            SigninScreen(navController = navController)
        }
        composable(route = Screens.SignUp.route) {
            SignupScreen(
                navController = navController,
                backPressed = {
                    navigateSingleTopTo(Screens.SignIn.route, navController)
                }
            )
        }
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
    }
}
