package com.example.posyandu.ui.Screen.Berita

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KonfirmasiPendaftaranScreen(
    nomorAntrian: Int = 30,
    onDaftar: () -> Unit,
    onBatal: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Kembali",
                modifier = Modifier.clickable { /* back navigation */ }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Konfirmasi Pendaftaran", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Card Nomor Antrian
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nomor Antrian", fontSize = 16.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF008CBA), Color(0xFF005F73)),
                            ),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = nomorAntrian.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Yakin melanjutkan pendaftaran?", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card Detail Kegiatan
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Pemberian Imunisasi Polio",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF006064),
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFD54F), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Balita", fontSize = 12.sp, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                DetailRow(Icons.Default.Place, "Tempat", "Semua Posko")
                DetailRow(Icons.Default.DateRange, "Tanggal", "19 Mei 2025")
                DetailRow(Icons.Default.DateRange, "Waktu", "09:00 - 12:00")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Dalam rangka mendukung program pemerintah untuk melindungi anak-anak dari penyakit polio, kami menginformasikan bahwa akan diadakan kegiatan Imunisasi Polio secara gratis.",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text("💉 Imunisasi ini ditujukan untuk anak usia 0–59 bulan.", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Mohon untuk membawa Buku KIA (Kesehatan Ibu dan Anak) saat hadir ke lokasi.",
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol
        Column {
            Button(
                onClick = onDaftar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00627C))
            ) {
                Text("Daftar Antrian", fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onBatal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                border = BorderStroke(1.dp, Color(0xFF00627C))
            ) {
                Text("Batal", fontWeight = FontWeight.Medium, color = Color(0xFF00627C))
            }
        }
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("$label : $value", fontSize = 14.sp)
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview(showBackground = true)
@Composable
fun KonfirmasiPendaftaranPreview() {
    KonfirmasiPendaftaranScreen(
        nomorAntrian = 30,
        onDaftar = {},
        onBatal = {}
    )
}
