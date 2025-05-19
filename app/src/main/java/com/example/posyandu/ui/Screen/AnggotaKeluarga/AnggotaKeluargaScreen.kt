package com.example.posyandu.ui.Screen.AnggotaKeluarga

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.example.posyandu.Data.Model.Request.PosisiKeluarga

@Composable
fun AnggotaKeluargaScreen(
    navController: NavController,
    viewModel: AnggotaKeluargaViewModel,
) {
    var posisiKeluarga by remember { mutableStateOf<PosisiKeluarga?>(null) }

    val coroutineScope = rememberCoroutineScope()

    RegisterContent(
        posisiKeluarga = posisiKeluarga,
        onPosisiKeluagaChange = { posisiKeluarga = it },
        onNext = {
            coroutineScope.launch {
                println("PosisiKeluarga: $posisiKeluarga")
                if (posisiKeluarga != null) {
                    viewModel.savePosisiKeluarga(posisiKeluarga!!)
                    delay(100)
                    navController.navigate("Completed-Anggota")
                } else {
                    println("Posisi keluarga belum dipilih")
                }
            }
        }
    )
}

@Composable
fun RegisterContent(
    posisiKeluarga: PosisiKeluarga?,
    onPosisiKeluagaChange: (PosisiKeluarga) -> Unit,
    onNext: () -> Unit = {}
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFEAF6FF), Color(0xFFCCE9FF)),
                    startY = 0f,
                    endY = 400f
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 15.dp)
            ){
                Text(
                    text = "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66\uD83D\uDC9A Lengkapi data keluarga yuk!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF013B6D)
                )
                Spacer(modifier= Modifier.height(8.dp))
                Text(
                    text = "Sekarang kamu bisa tambah data keluarga yang akan ikut layanan Posyandu, seperti ibu, anak, atau kepala keluarga. Bisa tambah nanti juga kok~ \uD83D\uDE09",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier= Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )            ) {
                Column (
                    modifier = Modifier.padding(24.dp)
                ){
                    val options = listOf(PosisiKeluarga.ISTRI, PosisiKeluarga.ANAK)

                    Column {
                        options.forEach { posisi ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clickable { onPosisiKeluagaChange(posisi) }
                            ) {
                                RadioButton(
                                    selected = posisi == posisiKeluarga,
                                    onClick = { onPosisiKeluagaChange(posisi) }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = posisi.value)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                                )
                            )
                            .clickable { onNext() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Selanjutnya", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent(
        posisiKeluarga = null,
        onPosisiKeluagaChange = {},
        onNext = {}
    )
}
