package com.example.posyandu.ui.Screen.Dashboard

//import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.R
import com.example.posyandu.ui.Screen.Berita.JadwalMingguIniSection
import com.example.posyandu.ui.Screen.EKMS.TabelPemeriksaanBalita
//import com.example.posyandu.ui.Screen.EKMS.TabelPemeriksaanIbu



@Composable
fun DashboardScreen() {
    HeaderBackground()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        HelpDeskIcon(onClick = {})

        HeaderContent()
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
        TabelPemeriksaanBalita(tableData = ekmsData)

    }
}


@Composable
fun HeaderBackground() {
    val context = LocalContext.current
    val imageBitmap = ImageBitmap.imageResource(context.resources, R.drawable.headertekstur)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF08607A),
                        Color(0xFF84BBD1)
                    )
                )
            )
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

//icon helpdesk
@Composable
fun HelpDeskIcon(
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.SupportAgent,
            contentDescription = "Helpdesk",
            modifier = Modifier
                .size(iconSize)
                .clickable {
                    // aksi klik di sini, misal show toast, dialog, dsb
                    // sekarang kosong karena backend/fungsi tidak diperlukan
                },
            tint = Color.White // warna biru sesuai tema
        )
    }
}

@Composable
fun HeaderContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Bagian atas: Avatar dan sapaan
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_woman),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.White, CircleShape)
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
            }
        }

        // Teks tambahan di bawah Row
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Pantau kesehatan keluarga & info penting seputar ibu & anak!",
            fontSize = 12.sp,
            color = Color.White
        )
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
