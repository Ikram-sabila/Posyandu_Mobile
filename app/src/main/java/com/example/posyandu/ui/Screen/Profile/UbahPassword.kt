package com.example.posyandu.ui.Screen.Profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UbahPasswordScreen(
    onBackClick: () -> Unit = {},
    onSimpanClick: () -> Unit = {},
    onBatalClick: () -> Unit = {}
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
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
                text = "Ubah Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A1D2D)
            )
        }

        // Deskripsi
        Text(
            text = "Amankan akun kamu dengan mengganti password secara berkala.",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Kata Sandi Saat Ini
        Text("Kata sandi saat ini", fontWeight = FontWeight.Medium)
        TextField(
            value = oldPassword,
            onValueChange = { oldPassword = it },
            placeholder = { Text("Masukkan kata sandi saat ini") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFE5E5EA),
                focusedContainerColor = Color(0xFFE5E5EA)
            )
        )

        // Kata Sandi Baru
        Text("Kata sandi baru", fontWeight = FontWeight.Medium)
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            placeholder = { Text("Min. 8 karakter, masukkan huruf dan angka") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFE5E5EA),
                focusedContainerColor = Color(0xFFE5E5EA)
            )
        )

        // Ulangi Kata Sandi Baru
        Text("Ulangi kata sandi baru", fontWeight = FontWeight.Medium)
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Pastikan sama dengan di atas") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFE5E5EA),
                focusedContainerColor = Color(0xFFE5E5EA)
            )
        )

        Spacer(modifier = Modifier.height(200.dp))

        // Tombol Simpan
        Button(
            onClick = { onSimpanClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF005B71))
        ) {
            Text("Simpan Perubahan", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tombol Batal
        OutlinedButton(
            onClick = { onBatalClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF005B71))
        ) {
            Text("Batal", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UbahPasswordScreenPreview() {
    UbahPasswordScreen()
}
