package com.example.posyandu.ui.Screen.Register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.customView
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.util.Calendar
import java.time.format.DateTimeFormatter

@Composable
fun CompleteDataScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    onSuccessRegister: () -> Unit
) {
    var nik by remember { mutableStateOf("") }
    var no_kk by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf(LocalDate.now()) }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(tanggalLahir)
        }
    }

    val password = viewModel.password

    val registerState by viewModel.registerState.collectAsState()

    CompleteDataContent(
        nik = nik,
        onNikChange = { nik = it },
        no_kk = no_kk,
        onNo_kk = { no_kk = it },
        jenisKelamin = jenisKelamin,
        onJenisKelamin = { jenisKelamin = it },
        tanggalLahir = tanggalLahir,
        onTanggalLahir = { tanggalLahir = it },
        onNext = {
            Log.d("TAG", "Simpan klik, data: nik=$nik, no_kk=$no_kk, jenisKelamin=$jenisKelamin, tanggalLahir=$tanggalLahir")
            viewModel.saveCompleteData(nik, no_kk, jenisKelamin, tanggalLahir)

            Log.d("Tag", password)
            if (password.isNotEmpty()) {
                Log.d("TAG", "Password ditemukan, proses register dimulai")
                viewModel.register(password)
            } else {
                Log.d("TAG", "Password kosong, tidak bisa register")
            }
//            navController.navigate("password")
        },
        registerState = registerState,
        onSuccessRegister = onSuccessRegister
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteDataContent(
    nik: String,
    onNikChange: (String) -> Unit,
    no_kk: String,
    onNo_kk: (String) -> Unit,
    jenisKelamin: String,
    onJenisKelamin: (String) -> Unit,
    tanggalLahir: LocalDate,
    onTanggalLahir: (LocalDate) -> Unit,
    registerState: RegisterState,
    onNext: () -> Unit = {},
    onSuccessRegister: () -> Unit
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
                )
            ) {
                Column (
                    modifier = Modifier.padding(24.dp)
                ){
                    Text(
                        text = "NIK",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = nik,
                        onValueChange = onNikChange,
                        label = {
                            Text("NIK")
                        },
                        placeholder = {
                            Text("Masukkan NIK sesuai KTP")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = "Email")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier= Modifier.height(16.dp))

                    Text(
                        text = "No. Kartu Kelurga",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = no_kk,
                        onValueChange = onNo_kk,
                        label = {
                            Text("NO. KK")
                        },
                        placeholder = {
                            Text("Masukkan No. KK sesuai KK")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = "Email")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier= Modifier.height(16.dp))

                    Text(
                        text = "Jenis Kelamin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = jenisKelamin,
                        onValueChange = onJenisKelamin,
                        label = {
                            Text("Jenis Kelamin")
                        },
                        placeholder = {
                            Text("Contoh: Perempuan")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Nama")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                    )
                    Spacer(modifier= Modifier.height(16.dp))

                    Text(
                        text = "Tanggal Lahir",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    val context = LocalContext.current

                    val dateDialogeState = rememberMaterialDialogState()

                    Card(
                        onClick = { dateDialogeState.show() },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.DateRange, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = tanggalLahir.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier= Modifier.height(34.dp))

                    when (registerState) {
                        is RegisterState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        is RegisterState.Success -> {
                            Text(
                                text = (registerState as RegisterState.Success).message,
                                color = Color.Green,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            onSuccessRegister()
                        }

                        is RegisterState.Error -> {
                            Text(
                                text = (registerState as RegisterState.Error).error,
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        else -> {

                        }
                    }

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
                            .clickable {
                                Log.d("TAG", "Tombol Simpan diklik")
                                if (nik.isBlank() || jenisKelamin.isBlank()) {
                                    Log.d("NIK", nik)
                                    Toast.makeText(context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Log.d("NIK", nik + "Berhasil")
                                    onNext()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Simpan", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    MaterialDialog (
                        dialogState = dateDialogeState,
                        buttons = {
                            positiveButton(text = "Ok")
                            negativeButton(text = "Cancel")
                        }
                    ) {
                        datepicker(
                            initialDate = tanggalLahir,
                            title = "Pilih Tanggal"
                        ) { date ->
                            onTanggalLahir(date)
                            Toast.makeText(context, "Tanggal dipilih: ${date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompleteDataPreview() {
    CompleteDataContent(
        nik = "",
        onNikChange = {},
        no_kk = "",
        onNo_kk = {},
        jenisKelamin = "",
        onJenisKelamin = {},
        tanggalLahir = LocalDate.now(),
        onTanggalLahir = {},
        onNext = {},
        registerState = RegisterState.Idle,
        onSuccessRegister = {}
    )
}
