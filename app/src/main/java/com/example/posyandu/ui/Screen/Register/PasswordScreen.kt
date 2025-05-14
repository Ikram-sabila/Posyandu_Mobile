package com.example.posyandu.ui.Screen.Register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PasswordScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
) {
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    PasswordContent(
        password = password,
        onPasswordChange = { password = it },
        repeatPassword = repeatPassword,
        onRepeatPasswordChange = { repeatPassword = it },
        onNext = {
            if (password == repeatPassword) {
                coroutineScope.launch {
                    Log.d("TAG", "Calling savePassword with: $password")
                    viewModel.savePassword(password)
                    delay(100)
                    navController.navigate("complete_data")
                }
            } else {
                println("Password dan Repeat Password tidak cocok!")
            }
            println("Password: $password, Repeat Password: $repeatPassword")
        }
    )
}

@Composable
fun PasswordContent(
    password: String,
    onPasswordChange: (String) -> Unit,
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
    onNext: () -> Unit = {},
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
                    text = "\uD83D\uDD12\uD83D\uDEE1 Bikin Password yang Aman, Yuk!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF013B6D)
                )
                Spacer(modifier= Modifier.height(8.dp))
                Text(
                    text = "Buat password untuk menjaga keamanan akunmu. Pastikan mudah diingat, tapi sulit ditebak ya! ✨ 😊",
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
                    Text(
                        text = "Password Baru",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        label = {
                            Text("Password Baru")
                        },
                        placeholder = {
                            Text("Masukkan password baru")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Email")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier= Modifier.height(16.dp))

                    Text(
                        text = "Ulangi Password",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = repeatPassword,
                        onValueChange = onRepeatPasswordChange,
                        label = {
                            Text("Ulangi password")
                        },
                        placeholder = {
                            Text("Masukkan password baru")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Nama")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier= Modifier.height(34.dp))

//                    when (registerState) {
//                        is RegisterState.Loading -> {
//                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//                        }
//
//                        is RegisterState.Success -> {
//                            Text(
//                                text = (registerState as RegisterState.Success).message,
//                                color = Color.Green,
//                                modifier = Modifier.align(Alignment.CenterHorizontally)
//                            )
//                        }
//
//                        is RegisterState.Error -> {
//                            Text(
//                                text = (registerState as RegisterState.Error).error,
//                                color = Color.Red,
//                                modifier = Modifier.align(Alignment.CenterHorizontally)
//                            )
//                        }
//
//                        else -> {
//
//                        }
//                    }

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
                        Text("Simpan", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordPreview() {
    PasswordContent(
        password = "",
        onPasswordChange = {},
        repeatPassword = "",
        onRepeatPasswordChange = {},
        onNext = {}
    )
}
