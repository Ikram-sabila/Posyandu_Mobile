package com.example.posyandu.ui.Screen.EKMS

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EKMSBalitaScreen(tableData: List<List<String>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("E-KMS Balita", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Informasi Balita
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F2F7)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Adik Rama", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRowWithIconBalita(Icons.Default.Cake, "Usia Balita", "18 Bulan")
                InfoRowWithIconBalita(Icons.Default.Favorite, "Berat Badan", "11 Kg")
                InfoRowWithIconBalita(Icons.Default.Star, "Kategori", "Balita", isBadge = true)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabel perkembangan balita
        TabelPemeriksaanBalita(tableData = tableData)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Imunisasi dan Suplemen", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        SuplemenCardBalita("Imunisasi", Color(0xFFE8E5FF))
        Spacer(modifier = Modifier.height(8.dp))
        SuplemenCardBalita("Suplemen Tambahan", Color(0xFFFFF2CF))
        Spacer(modifier = Modifier.height(8.dp))
        SuplemenCardBalita("Vitamin", Color(0xFFFFE0E5))
    }
}

@Composable
fun TabelPemeriksaanBalita(tableData: List<List<String>>) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Tabel Perkembangan",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { /* TODO: aksi tombol selengkapnya */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(32.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Selengkapnya",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(15.dp))
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCCE5FF))
                    .height(44.dp)
            ) {
                listOf("Tanggal", "Umur", "BB", "TB", "Lingkar Kepala").forEach {
                    TableCellBalita(text = it, isHeader = true, modifier = Modifier.weight(1f))
                }
            }

            tableData.forEachIndexed { index, row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (index % 2 == 0) Color(0xFFF9F9F9) else Color.White)
                        .height(40.dp)
                ) {
                    row.forEach {
                        TableCellBalita(text = it, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRowWithIconBalita(
    icon: ImageVector,
    label: String,
    value: String,
    isBadge: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(18.dp)
                .padding(end = 8.dp),
            tint = Color.DarkGray
        )
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(150.dp)
        )
        Text(text = ":", modifier = Modifier.padding(horizontal = 4.dp))
        if (isBadge) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFD966), RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(value, fontSize = 12.sp, color = Color.White)
            }
        } else {
            Text(value)
        }
    }
}

@Composable
fun TableCellBalita(
    text: String,
    isHeader: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = if (isHeader) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1
        )
    }
}

fun getSuplemenIconColorBalita(label: String): Color {
    return when (label) {
        "Imunisasi" -> Color(0xFF7C4DFF)
        "Suplemen Tambahan" -> Color(0xFFF7B801)
        "Vitamin" -> Color(0xFFFF4081)
        else -> Color.DarkGray
    }
}

@Composable
fun SuplemenCardBalita(label: String, backgroundColor: Color) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = getSuplemenIconColorBalita(label)
                )
            }
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = label,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEKMSBalitaScreen() {
    val sampleData = listOf(
        listOf("01-01-2025", "12 Bulan", "10 Kg", "75 cm", "46 cm"),
        listOf("01-02-2025", "13 Bulan", "10.5 Kg", "76 cm", "47 cm"),
        listOf("01-03-2025", "14 Bulan", "11 Kg", "77 cm", "47.5 cm"),
        listOf("01-04-2025", "15 Bulan", "11.2 Kg", "78 cm", "48 cm"),
        listOf("01-05-2025", "16 Bulan", "11.5 Kg", "79 cm", "48.5 cm"),
        listOf("01-06-2025", "17 Bulan", "11.8 Kg", "80 cm", "49 cm")
    )
    EKMSBalitaScreen(tableData = sampleData)
}
