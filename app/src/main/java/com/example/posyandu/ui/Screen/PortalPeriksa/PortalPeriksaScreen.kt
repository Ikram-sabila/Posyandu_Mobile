package com.example.posyandu.ui.Screen.PortalPeriksa

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.R

@Composable
fun PortalPeriksaScreen() {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PersonCard(
                    title = "Ibu",
                    name = "Bunga Citra",
                    imageRes = R.drawable.iconibu,
                    backgroundImage = R.drawable.cardibu
                )
                PersonCard(
                    title = "Anak",
                    name = "Atharafie",
                    imageRes = R.drawable.iconanak,
                    backgroundImage = R.drawable.cardanak
                )
            }
        }
    }
}

@Composable
fun PersonCard(
    title: String,
    name: String,
    imageRes: Int,
    backgroundImage: Int
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
    PortalPeriksaScreen()
}
