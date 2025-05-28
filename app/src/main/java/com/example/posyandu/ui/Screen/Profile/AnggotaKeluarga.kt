package com.example.posyandu.ui.Screen.Profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class FamilyInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyInfoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyInfoScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Informasi Anggota Keluarga") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigasi kembali */ }) {
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
            FamilyMemberCard(name = "Bunga Citra", role = "Anak")
            Spacer(modifier = Modifier.height(20.dp))
            FamilyMemberCard(name = "Atharie Hermawan", role = "Anak")
        }
    }
}

@Composable
fun FamilyMemberCard(name: String, role: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigasi ke detail anggota */ },
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
    FamilyInfoScreen()
}
