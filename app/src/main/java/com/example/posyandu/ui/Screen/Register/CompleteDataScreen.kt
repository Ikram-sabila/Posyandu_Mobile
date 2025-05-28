package com.example.posyandu.ui.Screen.Register

//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.draw.clip
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posyandu.Data.Model.Request.JenisKelamin
import com.example.posyandu.ui.Screen.components.HeaderBackground
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter




@Composable
fun CompleteDataScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    onSuccessRegister: () -> Unit
) {
    var nik by remember { mutableStateOf("") }
    var no_kk by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf(LocalDate.now()) }
    var jenisKelamin by remember { mutableStateOf<JenisKelamin?>(null) }
    var poskoPosyandu by remember { mutableStateOf("") }

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
        poskoPosyandu = poskoPosyandu,
        onPoskoPosyanduChange = { poskoPosyandu = it },
        registerState = registerState,
        onNext = {
            Log.d("TAG", "Simpan klik, data: nik=$nik, no_kk=$no_kk, jenisKelamin=$jenisKelamin, tanggalLahir=$tanggalLahir")
            jenisKelamin?.let { viewModel.saveCompleteData(nik, no_kk, it, tanggalLahir) }

            Log.d("Tag", password)
            if (password.isNotEmpty()) {
                Log.d("TAG", "Password ditemukan, proses register dimulai")
                viewModel.register(password)
            } else {
                Log.d("TAG", "Password kosong, tidak bisa register")
            }
            navController.navigate("anggota/$no_kk")
        },
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
    jenisKelamin: JenisKelamin?,
    onJenisKelamin: (JenisKelamin) -> Unit,
    tanggalLahir: LocalDate,
    onTanggalLahir: (LocalDate) -> Unit,
    poskoPosyandu: String,
    onPoskoPosyanduChange: (String) -> Unit,
    registerState: RegisterState,
    onNext: () -> Unit = {},
    onSuccessRegister: () -> Unit = {}
) {
    CompleteDataHeader()

    // State dropdown
    var expandedJenisKelamin by remember { mutableStateOf(false) }
    var expandedPosko by remember { mutableStateOf(false) }

    // Format tanggal tampil di TextField
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val tanggalLahirText by remember(tanggalLahir) {
        mutableStateOf(tanggalLahir.format(formatter))
    }

    val poskoList = listOf("Posko A", "Posko B", "Posko C")

    // Date Picker Dialog
    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 170.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input NIK
                CustomFormField(
                    label = "NIK",
                    value = nik,
                    onValueChange = onNikChange,
                    placeholder = "Masukkan NIK"
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Jenis Kelamin Dropdown
                Text(
                    text = "Jenis Kelamin",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                TextField(
                    value = jenisKelamin?.name ?: "",
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Pilih Jenis Kelamin") },
                    trailingIcon = {
                        IconButton(onClick = { expandedJenisKelamin = !expandedJenisKelamin }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Toggle dropdown"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF1F1F1)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(0xFFF1F1F1),
                        unfocusedContainerColor = Color(0xFFF1F1F1),
                        disabledContainerColor = Color(0xFFF1F1F1)
                    )
                )
                DropdownMenu(
                    expanded = expandedJenisKelamin,
                    onDismissRequest = { expandedJenisKelamin = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    JenisKelamin.values().forEach { jenis ->
                        DropdownMenuItem(
                            text = { Text(jenis.name) },
                            onClick = {
                                onJenisKelamin(jenis)
                                expandedJenisKelamin = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tanggal Lahir Picker
                Text(
                    text = "Tanggal Lahir",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                TextField(
                    value = tanggalLahirText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Select date"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF1F1F1))
                        .clickable {
                            dateDialogState.show()
                        },
                    placeholder = { Text("Pilih Tanggal Lahir") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(0xFFF1F1F1),
                        unfocusedContainerColor = Color(0xFFF1F1F1),
                        disabledContainerColor = Color(0xFFF1F1F1)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dropdown Posko Posyandu
                Text(
                    text = "Posko Posyandu",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                TextField(
                    value = poskoPosyandu,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Pilih Posko Posyandu") },
                    trailingIcon = {
                        IconButton(onClick = { expandedPosko = !expandedPosko }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Toggle dropdown"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF1F1F1)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(0xFFF1F1F1),
                        unfocusedContainerColor = Color(0xFFF1F1F1),
                        disabledContainerColor = Color(0xFFF1F1F1)
                    )
                )
                DropdownMenu(
                    expanded = expandedPosko,
                    onDismissRequest = { expandedPosko = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    poskoList.forEach { posko ->
                        DropdownMenuItem(
                            text = { Text(posko) },
                            onClick = {
                                onPoskoPosyanduChange(posko)
                                expandedPosko = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Lanjut
                Button(
                    onClick = onNext, //ubah ini
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF005F6B)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Selanjutnya", color = Color.White)
                }
            }
        }
    }

    // Material Date Picker Dialog
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("Batal")
        }
    ) {
        datepicker(
            initialDate = tanggalLahir,
            title = "Pilih Tanggal Lahir"
        ) {
            onTanggalLahir(it)
        }
    }
}

@Composable
fun CompleteDataHeader() {
    HeaderBackground {
        Text(
            text = "Verifikasi Identitas Anda",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Masukkan data yang sesuai agar proses registrasi berjalan lancar.",
            fontSize = 14.sp,
            color = Color.White
        )
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
        jenisKelamin = null,
        onJenisKelamin = {},
        tanggalLahir = LocalDate.now(),
        onTanggalLahir = {},
        poskoPosyandu = "",
        onPoskoPosyanduChange = {},
        registerState = RegisterState.Idle
    )
}
