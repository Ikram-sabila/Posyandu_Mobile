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
import com.example.posyandu.ui.Screen.Dashboard.DashboardScreen
import com.example.posyandu.ui.Screen.EKMS.EKMSBalitaScreen
import com.example.posyandu.ui.Screen.EKMS.EKMSRepository
import com.example.posyandu.ui.Screen.EKMS.EKMSScreen
import com.example.posyandu.ui.Screen.EKMS.EKMSViewModel
import com.example.posyandu.ui.Screen.EKMS.EKMSViewModelFactory
import com.example.posyandu.ui.Screen.EKMS.InformasiPerkembanganBalitaScreen
import com.example.posyandu.ui.Screen.Login.LoginScreen
import com.example.posyandu.ui.Screen.Login.LoginViewModel
import com.example.posyandu.ui.Screen.PortalPeriksa.PemeriksaanBalitaScreen
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaScreen
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaViewModel
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaViewModelFactory
import com.example.posyandu.ui.Screen.PortalPeriksa.RiwayatPemeriksaanScreen
import com.example.posyandu.ui.Screen.Profile.EditProfileScreen
import com.example.posyandu.ui.Screen.Profile.FamilyInfoScreen
import com.example.posyandu.ui.Screen.Profile.PengaturanScreen
import com.example.posyandu.ui.Screen.Profile.ProfilScreen
import com.example.posyandu.ui.Screen.Profile.ProfilViewModel
import com.example.posyandu.ui.Screen.Profile.ProfileRepository
import com.example.posyandu.ui.Screen.Profile.ProfileViewModelFactory
import com.example.posyandu.ui.Screen.Profile.UbahEmailScreen
import com.example.posyandu.ui.Screen.Profile.UbahPasswordScreen
import com.example.posyandu.ui.Screen.Register.CompleteDataScreen
import com.example.posyandu.ui.Screen.Register.PasswordScreen
import com.example.posyandu.ui.Screen.Register.RegisterScreen
import com.example.posyandu.ui.Screen.Register.RegisterViewModel

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    val registerViewModel: RegisterViewModel = viewModel(LocalContext.current as ComponentActivity)
    val anggotaViewModel: AnggotaKeluargaViewModel = viewModel(LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = "email") {
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
        composable("dashboard") {
            val beritaRepository = PortalBeritaRepository()
            val beritaFactory = PortalBeritaViewModelFactory(beritaRepository)
            val beritaViewModel: PortalBeritaViewModel = viewModel(factory = beritaFactory)

            val repository = ProfileRepository()
            val factory = ProfileViewModelFactory(repository)
            val viewModel: ProfilViewModel = viewModel(factory = factory)

            DashboardScreen(navController = navController, portalBeritaViewModel = beritaViewModel, profilViewModel = viewModel)
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
        composable("profil") {
            ProfilScreen(navController)
        }
        composable("edit-profile") {
            val repository = ProfileRepository()
            val factory = ProfileViewModelFactory(repository)
            val viewModel: ProfilViewModel = viewModel(factory = factory)
            EditProfileScreen(viewModel = viewModel)
        }
        composable("pengaturan") {
            PengaturanScreen(navController)
        }
        composable("edit-email") {
            val repository = ProfileRepository()
            val factory = ProfileViewModelFactory(repository)
            val viewModel: ProfilViewModel = viewModel(factory = factory)
            UbahEmailScreen(navController, viewModel = viewModel)
        }
        composable("Update-Password") {
            val repository = ProfileRepository()
            val factory = ProfileViewModelFactory(repository)
            val viewModel: ProfilViewModel = viewModel(factory = factory)
            UbahPasswordScreen(navController, viewModel)
        }
        composable("riwayat-ekms") {
            val repository = PortalPeriksaRepository()
            val factory = PortalPeriksaViewModelFactory(repository)
            val viewModel: PortalPeriksaViewModel = viewModel(factory = factory)
            EKMSScreen(navController, viewModel)
        }
        composable(
            "ekms/{nik}",
            arguments = listOf(navArgument("nik") { type = NavType.StringType })
            ) {backStackEntry ->
            val nik = backStackEntry.arguments?.getString("nik") ?: ""

            val repository = EKMSRepository()
            val factory = EKMSViewModelFactory(repository)
            val viewModel: EKMSViewModel = viewModel(factory = factory)
            EKMSBalitaScreen(navController, viewModel, nik)
        }
        composable(
            "riwayat-ekms/{nik}/{option}",
            arguments = listOf(
                navArgument("nik") { type = NavType.StringType },
                navArgument("option") { type = NavType.IntType }
            )
        ) {backStackEntry ->
            val nik = backStackEntry.arguments?.getString("nik") ?: ""
            val option = backStackEntry.arguments?.getInt("option") ?: 0

            val repository = EKMSRepository()
            val factory = EKMSViewModelFactory(repository)
            val viewModel: EKMSViewModel = viewModel(factory = factory)
            InformasiPerkembanganBalitaScreen(navController, viewModel, nik, option)
        }
        composable("profil-anggota") {
            val repository = PortalPeriksaRepository()
            val factory = PortalPeriksaViewModelFactory(repository)
            val viewModel: PortalPeriksaViewModel = viewModel(factory = factory)

            FamilyInfoScreen(navController, viewModel = viewModel)
        }
    }
}
