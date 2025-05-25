package com.example.posyandu.ui.Screen.EKMS

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.R

@Composable
fun EKMSScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // AppBar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable { /* navigateBack() */ }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("E-KMS", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Kartu Ibu
        CardEKMS(
            title = "Ibu",
            name = "Bunga Citra",
            gradient = Brush.horizontalGradient(
                listOf(Color(0xFFF76D95), Color(0xFFFF8DA1))
            ),
            imageRes = R.drawable.iconibu // Pastikan file ini ada di drawable
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kartu Anak
        CardEKMS(
            title = "Anak",
            name = "Atharafie Hermawan",
            gradient = Brush.horizontalGradient(
                listOf(Color(0xFFFFD54F), Color(0xFFFFF176))
            ),
            imageRes = R.drawable.iconanak // Pastikan file ini ada di drawable
        )
    }
}

@Composable
fun CardEKMS(
    title: String,
    name: String,
    gradient: Brush,
    imageRes: Int
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Aksi ketika kartu diklik */ }
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEKMSScreen() {
    EKMSScreen()
}
