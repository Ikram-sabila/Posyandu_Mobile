package com.example.posyandu.ui.Screen.PortalPeriksa

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posyandu.Data.Local.UserPreferences
import com.example.posyandu.R

@Composable
fun PortalPeriksaScreen(
    navController: NavController,
    viewModel: PortalPeriksaViewModel
) {
    val anggota by viewModel.anggota.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    val context = LocalContext.current

    val token by UserPreferences.getToken(context).collectAsState(initial = "")
    val no_kk by UserPreferences.getNoKK(context).collectAsState(initial = "")
    val userId by UserPreferences.getUserId(context).collectAsState(initial = 0)


    LaunchedEffect(token, no_kk) {
        if (!token.isNullOrEmpty() && !no_kk.isNullOrEmpty()) {
            val bearerToken = "Bearer $token"
            viewModel.fetchAnggota(bearerToken, no_kk!!)
        }
    }

    Scaffold(
        topBar = {
            // Bisa tambahkan TopAppBar jika diperlukan
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (error != null) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Terjadi kesalahan: $error")
                }
            } else {
                LazyColumn{
                    items(anggota) { item ->
                        val (iconRes, bgRes) = when (item.posisi_keluarga.lowercase()) {
                            "Anak" -> R.drawable.iconanak to R.drawable.cardanak
                            "Istri", "Kepala Keluarga" -> R.drawable.iconibu to R.drawable.cardibu
                            else -> R.drawable.iconibu to R.drawable.cardibu // default
                        }

                        PersonCard(
                            title = item.posisi_keluarga,
                            name = item.nama_anggota_keluarga,
                            imageRes = iconRes,
                            backgroundImage = bgRes,
                            onClick = {
                                val nik = item.anggota_keluarga_nik

                                if (!nik.isNullOrEmpty()) {
                                    navController.navigate("riwayat-pemeriksaan/${userId}/$nik/${item.posisi_keluarga}")
                                } else {
                                    Toast.makeText(context, "NIK tidak tersedia untuk ${item.nama_anggota_keluarga}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                PersonCard(
//                    title = "Ibu",
//                    name = "Bunga Citra",
//                    imageRes = R.drawable.iconibu,
//                    backgroundImage = R.drawable.cardibu
//                )
//                PersonCard(
//                    title = "Anak",
//                    name = "Atharafie",
//                    imageRes = R.drawable.iconanak,
//                    backgroundImage = R.drawable.cardanak
//                )
//            }
        }
    }
}

@Composable
fun PersonCard(
    title: String,
    name: String,
    imageRes: Int,
    backgroundImage: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Background image
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopEnd)
                    .clickable { onClick() }
            )

            // Ikon profil di pojok kanan atas
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp)
                    .align(Alignment.TopEnd),
            )

            // Teks title di pojok kiri atas
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            )

            // Teks nama di pojok kiri bawah
            Text(
                text = name,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPortalPeriksaScreen() {
//    PortalPeriksaScreen()
}
