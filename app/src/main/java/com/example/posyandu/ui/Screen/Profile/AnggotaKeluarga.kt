package com.example.posyandu.ui.Screen.Profile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.posyandu.Data.Local.UserPreferences
import com.example.posyandu.R
import com.example.posyandu.ui.Screen.PortalPeriksa.PersonCard
import com.example.posyandu.ui.Screen.PortalPeriksa.PortalPeriksaViewModel

class FamilyInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            FamilyInfoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyInfoScreen(
    navController: NavController,
    viewModel: PortalPeriksaViewModel
) {
    val anggota by viewModel.anggota.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    val context = LocalContext.current

    val token by UserPreferences.getToken(context).collectAsState(initial = "")
    val no_kk by UserPreferences.getNoKK(context).collectAsState(initial = "")

    LaunchedEffect(token, no_kk) {
        if (!token.isNullOrEmpty() && !no_kk.isNullOrEmpty()) {
            val bearerToken = "Bearer $token"
            viewModel.fetchAnggota(bearerToken, no_kk!!)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Informasi Anggota Keluarga") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
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
                if (anggota.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Belum ada pemeriksaan untuk anggota yang dipilih.",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                } else {
                    LazyColumn {
                        items(anggota) { item ->
                            FamilyMemberCard(
                                name = item.nama_anggota_keluarga,
                                role = item.posisi_keluarga,
                                nik = item.anggota_keluarga_nik,
                                onClick = { nik ->
                                    navController.navigate("profil-anggota/$nik")
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FamilyMemberCard(name:
                     String,
                     role: String,
                     nik: String,
                     onClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(nik) },
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFFFFFF), // warna ungu muda
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00597A) // warna biru teks
                )
                Text(
                    text = role,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Lihat Detail",
                tint = Color(0xFF00597A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFamilyInfoScreen() {
//    FamilyInfoScreen()
}
