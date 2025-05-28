package com.example.posyandu.ui.Screen.Dashboard

//import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.R
import com.example.posyandu.ui.Screen.Berita.JadwalMingguIniSection
import com.example.posyandu.ui.Screen.EKMS.TabelPemeriksaanBalita
//import com.example.posyandu.ui.Screen.EKMS.TabelPemeriksaanIbu


@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(16.dp))

        MenuSection()
        Spacer(modifier = Modifier.height(16.dp))

        JadwalMingguIniSection()
        Spacer(modifier = Modifier.height(16.dp))


        val ekmsData = listOf(
            listOf("01-01-2025", "1", "50 Kg", "25 cm", "120/80"),
            listOf("01-02-2025", "1", "51 Kg", "26 cm", "110/70"),
            listOf("01-03-2025", "2", "53 Kg", "27 cm", "115/75")
        )
//        // Hanya panggil TabelPemeriksaanIbu di Dashboard
//        TabelPemeriksaanIbu(tableData = ekmsData)
        Spacer(modifier = Modifier.height(16.dp))
//        TabelPemeriksaanBalita(tableData = ekmsData)

    }
}

//@Composable
//fun HeaderSection() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.avatar_woman),
//            contentDescription = "User Avatar",
//            modifier = Modifier
//                .size(60.dp)
//                .clip(CircleShape)
//        )
//        Spacer(modifier = Modifier.width(12.dp))
//        Column {
//            Text("Hallo Rose", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            Text("Selamat Datang di PosyanduCare!", fontSize = 14.sp)
//            Text(
//                "Pantau kesehatan keluarga & info penting seputar ibu & anak!",
//                fontSize = 12.sp,
//                color = Color.Gray
//            )
//        }
//    }
//}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(15.dp)) // rounded semua sisi
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.headertekstur),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxSize()
        )

        // Overlay gradasi
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xCC08607A), // lebih gelap & transparan
                            Color(0xFF84BBD1)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_woman),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Hallo Rose",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Selamat Datang di PosyanduCare!",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Pantau kesehatan keluarga & info penting seputar ibu & anak!",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}



@Composable
fun MenuSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MenuItem(
            R.drawable.portalperiksa,
            "Portal Periksa",
            modifier = Modifier.weight(1f),
            backgroundColor = Color(0xFFE3F2FD) // Biru muda
        )
        MenuItem(
            R.drawable.portalberita,
            "Portal Berita",
            modifier = Modifier.weight(1f),
            backgroundColor = Color(0xFFFFF9C4) // Kuning muda
        )
        MenuItem(
            R.drawable.ekms,
            "E-KMS",
            modifier = Modifier.weight(1f),
            backgroundColor = Color(0xFFFFE4E9) // Pink muda
        )
    }
}



@Composable
fun MenuItem(
    drawableResId: Int,
    label: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFF0F0F0) // Default color
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
