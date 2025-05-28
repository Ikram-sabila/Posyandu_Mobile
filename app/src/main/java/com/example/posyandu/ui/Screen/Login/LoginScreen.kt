package com.example.posyandu.ui.Screen.Login

import android.content.Context
import com.example.posyandu.ui.Screen.Login.LoginViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
) {
    val email by viewModel.email
    val password by viewModel.password

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            navController.navigate("profil") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LoginContent(
        email = email,
        onEmailChange = { viewModel.email.value = it },
        password = password,
        onPasswordChange = { viewModel.password.value = it },
        onNext = {
            viewModel.login(context)
        }
    )

    when (loginState) {
        is LoginState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is LoginState.Error -> {
            val errorMessage = (loginState as LoginState.Error).message
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(8.dp))
        }
        else -> {}
    }
}

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
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
                    text = "PosyanduCare",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF013B6D)
                )
                Spacer(modifier= Modifier.height(8.dp))
                Text(
                    text = "Selamat Datang Kembali!\n" +
                            "Silahkan lengkapi form berikut untuk LogIn",
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
                        text = "Email",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        label = {
                            Text("Email")
                        },
                        placeholder = {
                            Text("Contoh: kamu@gmail.com")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = "Email")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier= Modifier.height(16.dp))

                    Text(
                        text = "Password",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )

                    var passwordVisible by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        label = { Text("Password") },
                        placeholder = { Text("Kata sandi") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Password")
                        },
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff

                            val description = if (passwordVisible) "Sembunyikan password" else "Tampilkan password"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
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
                        Text("Masuk", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginContent(
        email = "",
        onEmailChange = {},
        password = "",
        onPasswordChange = {}
    )
}
