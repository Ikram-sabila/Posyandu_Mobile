package com.example.posyandu.ui.Screen.PortalPeriksa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.ui.Screen.components.SectionTitle
import com.example.posyandu.ui.Screen.components.GridData
import com.example.posyandu.ui.Screen.components.InfoCard

@Composable
fun PemeriksaanBalitaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp)
    ) {
        // Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Atharie Hermawan", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("🏡 Posyandu Mnagga")
                Text("📍 Jalan Terusan Kanan No.45")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("Informasi Pemeriksaan")
        GridData(
            data = listOf(
                "Berat Badan" to "4,5 Kg",
                "Tinggi Badan" to "50 cm",
                "Lingkar Kepala" to "20 cm",
                "LILA" to "7 cm"
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle("Asupan Gizi")
        GridData(
            data = listOf(
                "PMT" to "4,5 Kg",
                "Jumlah Pemberian" to "50 cm",
                "Kurun" to "20 cm",
                "Waktu" to "7 cm"
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        GridData(
            data = listOf(
                "Asi" to "Ya",
                "Vit A" to "M",
                "Jenis" to "Merah"
            ),
            columns = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("Imunisasi dan Suplemen")

        InfoCard(
            title = "Imunisasi",
            value = "Polio",
            bgColor = Color(0xFFEDE7F6),
            icon = "✏️"
        )

        Spacer(modifier = Modifier.height(8.dp))

        InfoCard(
            title = "Suplemen Tambahan",
            value = "Obat Cacing",
            bgColor = Color(0xFFFFF3E0),
            icon = "💊"
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun GridData(data: List<Pair<String, String>>, columns: Int = 2) {
    Column {
        data.chunked(columns).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEach { (label, value) ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(label, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(value, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                // Jika kolom ganjil, beri spacer agar rata
                if (row.size < columns) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, value: String, bgColor: Color, icon: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(icon, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title, fontSize = 12.sp)
                Text(value, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPemeriksaanBalitaScreen() {
    PemeriksaanBalitaScreen()
}
