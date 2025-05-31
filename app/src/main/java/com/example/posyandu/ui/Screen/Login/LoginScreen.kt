package com.example.posyandu.ui.Screen.Login

//import androidx.compose.material3.*
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.posyandu.R
import com.example.posyandu.ui.Screen.Register.FormInput

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
) {
    val email by viewModel.email
    val password by viewModel.password

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    // Background Image dipisah dari Modifier dan berada sebagai children pertama di Box
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            navController.navigate("dashboard") {
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
        },
        onNextMore = {
            navController.navigate("onboarding")
        }
    )

    when (loginState) {
        is LoginState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is LoginState.Error -> {}
        else -> {}
    }

    val showErrorToast by viewModel.showErrorToast.collectAsState()

    LaunchedEffect(showErrorToast) {
        if (showErrorToast) {
            Toast.makeText(
                context,
                "Email atau Password salah!",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetErrorToast()
        }
    }
}

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onNext: () -> Unit = {},
    onNextMore: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo dan Judul
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo PosyanduCare",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "PosyanduCare",
            color = Color(0xFF005F6B),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Selamat Datang Kembali!",
            color = Color(0xFF005F6B),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Silakan lengkapi formulir berikut untuk masuk",
            color = Color(0xFF7D7F81),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
            //  modifier = Modifier.align(Alignment.CenterHorizontally)  // optional
        )

        Spacer(modifier = Modifier.height(28.dp))

        CustomFormField(
            label = "Email",
            value = email,
            onValueChange = onEmailChange,
            placeholder = "Masukkan Email Anda",
            trailingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        var passwordVisible by remember { mutableStateOf(false) }

        // Password
        FormInput(
            label = "Password",
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Masukkan password Anda",
            keyboardType = KeyboardType.Password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                val description = if (passwordVisible) "Sembunyikan kata sandi" else "Tampilkan kata sandi"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )


//        TextField(
//            value = password,
//            onValueChange = onPasswordChange,
//            label = { Text("Password") },
//            placeholder = { Text("Kata sandi") },
//            leadingIcon = {
//                Icon(Icons.Default.Lock, contentDescription = "Password")
//            },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            colors = TextFieldDefaults.colors()
//        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF005F6B)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Masuk", color = Color.White)
        }

        Spacer(modifier = Modifier.height(180.dp))


        // Text button sejajar
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Belum memiliki Akun?")
            TextButton(onClick = onNextMore) {
                Text("Daftar", color = Color(0xFFFF9800))

            }
        }
    }
}

//form field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            trailingIcon = trailingIcon,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8E9ED), shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color(0xFFF1F1F1),
                unfocusedContainerColor = Color(0xFFF1F1F1),
                disabledContainerColor = Color(0xFFF1F1F1)
            )
        )
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


