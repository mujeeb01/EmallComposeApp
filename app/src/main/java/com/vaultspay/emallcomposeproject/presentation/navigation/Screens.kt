package com.vaultspay.emallcomposeproject.presentation.navigation

sealed class Screens(val route: String) {
    object SignIn : Screens("sign_in_screen")
    object SignUp : Screens("sign_up_screen")
    object Home : Screens("home_screen")
}