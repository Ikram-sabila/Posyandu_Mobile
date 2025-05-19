package com.example.posyandu.ui.Screen.PortalPeriksa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card


// Item Card Riwayat
@Composable
fun CardRiwayatItem(
    date: String,
    tempat: String,
    alamat: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Pengaturan Tanggal
            Text(text = date, fontWeight = FontWeight.Bold, color = Color(0xFF1A3C40))
            Spacer(modifier = Modifier.height(8.dp))
            // Kolom Data dan Button
            Row(verticalAlignment = Alignment.CenterVertically) {
                //Kolom Data
                Column {
                    // Baris Nama Posko
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = tempat, fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    //Baris Alamat
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = alamat, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.width(25.dp))
                //Kolom Button
                Column {
                    // Button Selengkapnya
                    Button(
                        onClick = onClick,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Selengkapnya", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

// Daftar Riwayat Pemeriksaan
@Composable
fun RiwayatPemeriksaanScreen() {
    val riwayatList = listOf(
        Triple("18 Mei 2024", "Posyandu Melati", "Jl. Merdeka No. 10, Blitar"),
        Triple("11 Mei 2024", "Posyandu Melati", "Jl. Merdeka No. 10, Blitar"),
        Triple("4 Mei 2024", "Posyandu Melati", "Jl. Merdeka No. 10, Blitar"),
        Triple("27 April 2024", "Posyandu Melati", "Jl. Merdeka No. 10, Blitar"),
        Triple("20 April 2024", "Posyandu Melati", "Jl. Merdeka No. 10, Blitar"),
    )

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFF0F0F0))
        .padding(vertical = 16.dp)) {

        items(riwayatList) { (tanggal, tempat, alamat) ->
            CardRiwayatItem(
                date = tanggal,
                tempat = tempat,
                alamat = alamat,
                onClick = {
                    // Aksi ketika tombol "Selengkapnya" ditekan
                }
            )
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewRiwayatPemeriksaanScreen() {
    RiwayatPemeriksaanScreen()
}
