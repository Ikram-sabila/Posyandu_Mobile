package com.example.posyandu.Navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.posyandu.Data.Model.Request.PosisiKeluarga
import com.example.posyandu.ui.Screen.AnggotaKeluarga.AnggotaKeluargaScreen
import com.example.posyandu.ui.Screen.AnggotaKeluarga.AnggotaKeluargaViewModel
import com.example.posyandu.ui.Screen.AnggotaKeluarga.CompleteAnggotaKeluargaScreen
import com.example.posyandu.ui.Screen.Login.LoginScreen
import com.example.posyandu.ui.Screen.Login.LoginViewModel
import com.example.posyandu.ui.Screen.Register.CompleteDataScreen
import com.example.posyandu.ui.Screen.Register.PasswordScreen
import com.example.posyandu.ui.Screen.Register.RegisterScreen
import com.example.posyandu.ui.Screen.Register.RegisterViewModel

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    val registerViewModel: RegisterViewModel = viewModel(LocalContext.current as ComponentActivity)
    val anggotaViewModel: AnggotaKeluargaViewModel = viewModel(LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = "Login") {
        composable("email") {
            RegisterScreen(navController, registerViewModel)
        }
        composable("password") {
            PasswordScreen(navController, registerViewModel)
        }
        composable("complete_data") {
            CompleteDataScreen(
                navController,
                registerViewModel,
                onSuccessRegister = {}
            )
        }
        composable(
            "anggota/{no_kk}",
            arguments = listOf(navArgument("no_kk") { type = NavType.StringType })
        ) { backStackEntry ->
            val noKk = backStackEntry.arguments?.getString("no_kk") ?: ""
            anggotaViewModel.saveNoKK(noKk)

            AnggotaKeluargaScreen(navController, anggotaViewModel)
        }
        composable("Completed-Anggota") {
            CompleteAnggotaKeluargaScreen(
                navController = navController,
                viewModel = anggotaViewModel,
                onSuccessAnggotaKeluargaState = {}
            )
        }
        composable("Login") {
            LoginScreen(navController = navController, viewModel = LoginViewModel())
        }
    }
}
