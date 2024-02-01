package com.vaultspay.emallcomposeproject.utils

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object NavigationUtil {

    fun navigateSingleTopTo(
        route: String,
        navController: NavHostController
    ) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

    /* fun navigateToCountryScreen(
         countryId: String,
         navController: NavHostController
     ) {
         navController.navigate(Screens.TopNews.routeWithoutArgs + "/${countryId}/{language}/{source}") {
             launchSingleTop = true
         }
     }*/

}