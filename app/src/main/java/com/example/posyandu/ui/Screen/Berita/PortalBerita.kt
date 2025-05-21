package com.example.posyandu.ui.Screen.Berita

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PortalBeritaScreen(
    onBackClick: () -> Unit = {},
    onDetailClick: () -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {

        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Kembali",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Portal Berita",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A1D2D)
            )
        }

        // Minggu Ini
        Text(
            text = "Minggu Ini",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        NewsCard(
            title = "Pemberian Obat Cacing dan PMT",
            location = "Posko Melati",
            date = "Minggu, 18 Mei 2025",
            backgroundColor = Color(0xFFE1F1F8),
            buttonColor = Color(0xFFFFA800),
            onDetailClick = onDetailClick
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()

        // Akan Datang
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Akan datang",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        NewsCard(
            title = "Pemberian Vitamin A",
            location = "Posko Melati",
            date = "Minggu, 18 Mei 2025",
            backgroundColor = Color(0xFFF0F2F2),
            buttonColor = Color.Gray,
            onDetailClick = onDetailClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        NewsCard(
            title = "Pemberian Vitamin D",
            location = "Posko Melati",
            date = "Minggu, 18 Mei 2025",
            backgroundColor = Color(0xFFF0F2F2),
            buttonColor = Color.Gray,
            onDetailClick = onDetailClick
        )
    }
}

@Composable
fun NewsCard(
    title: String,
    location: String,
    date: String,
    backgroundColor: Color,
    buttonColor: Color,
    onDetailClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF0A1D2D))
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Place,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = location, fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = date, fontSize = 14.sp, color = if (buttonColor == Color.Gray) Color.Gray else Color(0xFF2773E0))
                }
            }

            // Tombol Detail
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text("Detail", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPortalBerita() {
    PortalBeritaScreen()
}
