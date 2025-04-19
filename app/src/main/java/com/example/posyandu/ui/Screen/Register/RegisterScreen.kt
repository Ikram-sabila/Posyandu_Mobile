package com.example.posyandu.ui.Screen.Register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onSuccessRegister: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.registerState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome Back!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier= Modifier.height(4.dp))
        Text(text = "Login to your account")

        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = {
            Text(text = "Username")
        })

        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = {
            Text(text = "Email address")
        })

        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = {
            Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier= Modifier.height(16.dp))
        Button(onClick = { viewModel.register(name, email, password) }) {
            Text(text = "Daftar")
        }

        Spacer(modifier= Modifier.height(16.dp))
        Text(text = "Forget Password", modifier = Modifier.clickable {

        })

        Spacer(modifier= Modifier.height(16.dp))
        Text(text = "Or sign in with")


//        TextField(value = name, onValueChange = { name = it }, label = { Text("Nama") })
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation()
//        )
//        Spacer(modifier = Modifier.height(16.dp))

//        Button(onClick = { viewModel.register(name, email, password) }) {
//            Text("Daftar")
//        }

//        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is RegisterState.Loading -> CircularProgressIndicator()
            is RegisterState.Success -> {
                Text((state as RegisterState.Success).message, color = MaterialTheme.colorScheme.primary)
                LaunchedEffect(Unit) {
                    onSuccessRegister()
                }
            }
            is RegisterState.Error -> {
                Text((state as RegisterState.Error).error, color = MaterialTheme.colorScheme.error)
            }
            else -> {}
        }
    }
}