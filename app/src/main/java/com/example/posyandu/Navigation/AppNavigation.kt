package com.example.posyandu.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posyandu.ui.Screen.Register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "register"){
        composable("register") {
            RegisterScreen {
                navController.navigate("home") {
                    popUpTo("register") { inclusive = true }
                }
            }
        }

        composable("home"){
            //
        }
    }
    
}


