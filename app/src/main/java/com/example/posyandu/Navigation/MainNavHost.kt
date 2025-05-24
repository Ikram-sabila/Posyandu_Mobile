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
import com.example.posyandu.Data.Remote.Repository.PortalPeriksa.PortalPeriksaRepository
import com.example.posyandu.ui.Screen.AnggotaKeluarga.AnggotaKeluargaScreen
import com.example.posyandu.ui.Screen.AnggotaKeluarga.AnggotaKeluargaViewModel
import com.example.posyandu.ui.Screen.AnggotaKeluarga.CompleteAnggotaKeluargaScreen
import com.example.posyandu.ui.Screen.Berita.DetailAntrianScreen
import com.example.posyandu.ui.Screen.Berita.DetailBeritaScreen
import com.example.posyandu.ui.Screen.Berita.KonfirmasiPendaftaranScreen
import com.example.posyandu.ui.Screen.Berita.PortalBeritaRepository
import com.example.posyandu.ui.Screen.Berita.PortalBeritaScreen
import com.example.posyandu.ui.Screen.Berita.PortalBeritaViewModel
import com.example.posyandu.ui.Screen.Berita.PortalBeritaViewModelFactory
import com.example.posyandu.ui.Screen.Login.LoginScreen
import com.example.posyandu.ui.Screen.Login.LoginViewModel
import com.example.posyandu.ui.Screen.PortalPeriksa.PemeriksaanBalitaScreen
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaScreen
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaViewModel
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaViewModelFactory
import com.example.posyandu.ui.Screen.PortalPeriksa.RiwayatPemeriksaanScreen
import com.example.posyandu.ui.Screen.Register.CompleteDataScreen
import com.example.posyandu.ui.Screen.Register.PasswordScreen
import com.example.posyandu.ui.Screen.Register.RegisterScreen
import com.example.posyandu.ui.Screen.Register.RegisterViewModel

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    val registerViewModel: RegisterViewModel = viewModel(LocalContext.current as ComponentActivity)
    val anggotaViewModel: AnggotaKeluargaViewModel = viewModel(LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = "login") {
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
        composable("portal-periksa") {backStackEntry ->
            val repository = PortalPeriksaRepository()
            val factory = PortalPeriksaViewModelFactory(repository)
            val viewModel: PortalPeriksaViewModel = viewModel(factory = factory)
            PortalPeriksaScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            "riwayat-pemeriksaan/{user_id}/{anggota_keluarga_nik}/{posisi}",
            arguments = listOf(
                navArgument("user_id") { type = NavType.IntType },
                navArgument("anggota_keluarga_nik") { type = NavType.StringType },
                navArgument("posisi") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val user_id = backStackEntry.arguments?.getInt("user-id") ?: 0
            val nik = backStackEntry.arguments?.getString("anggota_keluarga_nik") ?: ""
            val posisi = backStackEntry.arguments?.getString("posisi") ?: ""

            val repository = PortalPeriksaRepository()
            val factory = PortalPeriksaViewModelFactory(repository)
            val viewModel: PortalPeriksaViewModel = viewModel(factory = factory)

            RiwayatPemeriksaanScreen(navController, viewModel = viewModel, wargaId = user_id, nik = nik, tipe = posisi)
        }
        composable(
            "pemeriksaan/{id}",
            arguments = listOf( navArgument("id") { type = NavType.IntType })
            ) {
            backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val repository = PortalPeriksaRepository()
            val factory = PortalPeriksaViewModelFactory(repository)

            val viewModel: PortalPeriksaViewModel = viewModel(factory = factory)
            PemeriksaanBalitaScreen(viewModel =viewModel , pemeriksaanId = id)
        }
        composable("berita") {
            val repository = PortalBeritaRepository()
            val factory = PortalBeritaViewModelFactory(repository)
            val viewModel: PortalBeritaViewModel = viewModel(factory = factory)
            PortalBeritaScreen(navController, viewModel = viewModel)
        }
        composable(
            "berita-detail/{id}",
            arguments = listOf( navArgument("id") { type = NavType.IntType })
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val repository = PortalBeritaRepository()
            val factory = PortalBeritaViewModelFactory(repository)
            val viewModel: PortalBeritaViewModel = viewModel(factory = factory)
            DetailBeritaScreen(navController, viewModel = viewModel, id)
        }
        composable(
            "antrian/{id}",
            arguments = listOf( navArgument("id") { type = NavType.IntType } )
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val repository = PortalBeritaRepository()
            val factory = PortalBeritaViewModelFactory(repository)
            val viewModel: PortalBeritaViewModel = viewModel(factory = factory)
            KonfirmasiPendaftaranScreen(navController, viewModel = viewModel, id)
        }
        composable(
            "detail_antrian/{nomor-antrian}",
             arguments = listOf( navArgument("nomor-antrian") { type = NavType.IntType } )
        ) { backStackEntry ->
            val nomorAntrian = backStackEntry.arguments?.getInt("nomor-antrian") ?: 0

            DetailAntrianScreen(navController, nomorAntrian)
        }
    }
}
