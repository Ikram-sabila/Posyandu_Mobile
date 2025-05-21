package com.example.posyandu.ui.Screen.Profil

import ads_mobile_sdk.h6
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.posyandu.R

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import com.example.posyandu.Data.Local.UserPreferences


@Composable
fun ProfilScreen() {
    val context = LocalContext.current

    val nama by UserPreferences.getNama(context = context).collectAsState(initial = "")
    val email by UserPreferences.getEmail(context = context).collectAsState(initial = "")
    val no_telp by UserPreferences.getNoTelp(context = context).collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            // Load bitmap from drawable resource
            val imageBitmap = ImageBitmap.imageResource(context.resources, R.drawable.headertekstur)

            Canvas(modifier = Modifier.matchParentSize()) {
                // Buat path gelombang
                val path = Path().apply {
                    moveTo(0f, size.height * 0.6f)
                    quadraticBezierTo(
                        size.width * 0.5f, size.height,
                        size.width, size.height * 0.6f
                    )
                    lineTo(size.width, 0f)
                    lineTo(0f, 0f)
                    close()
                }

                // Clip canvas ke path gelombang
                clipPath(path) {
                    // Draw the image mengisi seluruh canvas
                    drawImage(
                        image = imageBitmap,
                        dstSize = IntSize(size.width.toInt(), size.height.toInt())
                    )
                }

                // Gambarkan gelombang warna semi transparan di atas gambar biar kontras
                drawPath(
                    path = path,
                    color = Color(0x80006D77), // warna semi transparan
                    style = Fill
                )
            }

            // Profile image + edit icon
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_woman),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.White, CircleShape)
                            .padding(3.dp)
                            .offset(x = 0.dp, y = (-1).dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nama dan info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            nama?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF003049)
                    )
                )
            }
            Text(
                text = email + " | " + no_telp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu list
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            ProfileMenuItem(
                icon = Icons.Default.Person,
                text = "Edit informasi profil"
            )
            Spacer(modifier = Modifier.height(12.dp))
            ProfileMenuItem(
                icon = Icons.Default.Lock,
                text = "Pengaturan Akun"
            )
            Spacer(modifier = Modifier.height(24.dp))
            ProfileMenuItem(
                icon = Icons.Default.ExitToApp,
                text = "Keluar"
            )
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, text: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF8F9FA))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF003049),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF003049)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfilScreen() {
    ProfilScreen()
}
