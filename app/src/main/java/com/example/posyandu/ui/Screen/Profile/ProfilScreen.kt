package com.example.posyandu.ui.Screen.Profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import com.example.posyandu.Data.Local.UserPreferences
import com.example.posyandu.R
import com.example.posyandu.ui.Screen.components.MainScaffold
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.HelpOutline


@Composable
fun ProfilScreen(navController: NavController) {
    val context = LocalContext.current

    val nama by UserPreferences.getNama(context = context).collectAsState(initial = "Lidia")
    val email by UserPreferences.getEmail(context = context).collectAsState(initial = "Sola")
    val no_telp by UserPreferences.getNoTelp(context = context).collectAsState(initial = "123")

    MainScaffold(
        navController = navController,
        currentRoute = "profil"
    ) {
        paddingValues ->

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                val imageBitmap = ImageBitmap.imageResource(context.resources, R.drawable.headertekstur)

                Canvas(modifier = Modifier.matchParentSize()) {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.7f)
                        quadraticBezierTo(
                            size.width * 0.5f, size.height * 1.1f,
                            size.width, size.height * 0.7f
                        )
                        lineTo(size.width, 0f)
                        lineTo(0f, 0f)
                        close()
                    }

                    // 1. Gradient background
                    drawPath(
                        path = path,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF08607A),
                                Color(0xFF84BBD1)
                            ),
                            startY = 0f,
                            endY = size.height
                        ),
                        style = Fill
                    )

                    // 2. Overlay tekstur di atas gradient
                    clipPath(path) {
                        drawImage(
                            image = imageBitmap,
                            dstSize = IntSize(size.width.toInt(), size.height.toInt())
                        )
                    }
                }

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
                    text = "$email | $no_telp",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                ProfileMenuSection(
                    items = listOf(
                        Icons.Default.Person to "Edit informasi profil",
                        Icons.Default.Group to "Informasi Anggota Keluarga"
                    ),
                    onClick = {
                        when (it) {
                            "Edit informasi profil" -> navController.navigate("edit-profile")
                            "Informasi Anggota Keluarga" -> navController.navigate("profil-anggota")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileMenuSection(
                    items = listOf(
                        Icons.Default.Lock to "Pengaturan Akun",
                        Icons.AutoMirrored.Filled.HelpOutline to "FAQ",
                        Icons.Default.Headset to "Help Desk"
                    ),
                    onClick = {
                        when (it) {
                            "Pengaturan Akun" -> navController.navigate("pengaturan")
                            "FAQ" -> navController.navigate("FAQ")
                            "Help Desk" -> navController.navigate("Help-Desk")
                        }
                    }
                )

//                Spacer(modifier = Modifier.height(16.dp))
//                ProfileMenuItem(
//                    icon = Icons.Default.Person,
//                    text = "Edit informasi profil",
//                    onClick = {
//                        navController.navigate("edit-profile")
//                    }
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                ProfileMenuItem(
//                    icon = Icons.Default.Lock,
//                    text = "Pengaturan Akun",
//                    onClick = {
//                        navController.navigate("pengaturan")
//                    }
//                )
                Spacer(modifier = Modifier.height(24.dp))
                ProfileMenuItem(
                    icon = Icons.Default.ExitToApp,
                    text = "Keluar",
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(34.dp))
            }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF8F9FA))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF08607A),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun ProfileMenuSection(items: List<Pair<ImageVector, String>>, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(vertical = 8.dp)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
    ) {
        items.forEachIndexed { index, item ->
            ProfileMenuItem(
                icon = item.first,
                text = item.second,
                onClick = { onClick(item.second) }
            )

            if (index != items.lastIndex) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewProfilScreen() {
//    ProfilScreen()
}
