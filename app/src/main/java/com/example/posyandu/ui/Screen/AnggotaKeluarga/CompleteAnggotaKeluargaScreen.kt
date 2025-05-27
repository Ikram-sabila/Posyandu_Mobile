package com.example.posyandu.ui.Screen.AnggotaKeluarga

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posyandu.Data.Model.Request.JenisKelamin
import com.example.posyandu.Data.Model.Request.PosisiKeluarga
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CompleteAnggotaKeluargaScreen(
    navController: NavController,
    viewModel: AnggotaKeluargaViewModel,
    onSuccessAnggotaKeluargaState: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var nik by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var anak_ke by remember { mutableStateOf(0) }
    var jenisKelamin by remember { mutableStateOf<JenisKelamin?>(null) }
    var tanggalLahir by remember { mutableStateOf(LocalDate.now()) }

    val posisi = viewModel.posisi_keluarga

    LaunchedEffect(posisi) {
        if (posisi == PosisiKeluarga.ISTRI) {
            anak_ke = 0
        }
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(tanggalLahir)
        }
    }

    val anggotaKeluargaState by viewModel.anggotaKeluargaState.collectAsState()

    var lastAction by remember { mutableStateOf("") }

    fun saveAndGoLogin() {
        coroutineScope.launch {
            if (jenisKelamin != null && nik.isNotBlank()) {
                viewModel.saveAnggotaKeluarga(nama, nik, tanggalLahir, jenisKelamin!!, anak_ke)
                viewModel.save()
                lastAction = "login"
            }
        }
    }

    fun saveAndBackToList() {
        coroutineScope.launch {
            if (jenisKelamin != null && nik.isNotBlank()) {
                viewModel.saveAnggotaKeluarga(nama, nik, tanggalLahir, jenisKelamin!!, anak_ke)
                viewModel.save()
                lastAction = "back"
            }
        }
    }

    val no_kk = viewModel.no_kk

    LaunchedEffect(anggotaKeluargaState) {
        if (anggotaKeluargaState is AnggotaKeluargaState.Success) {
            when (lastAction) {
                "login" -> navController.navigate("Login")
                "back" -> navController.navigate("anggota/$no_kk")
            }
        }
    }

    CompleteAnggotaKeluargaContent(
        nik = nik,
        onNikChange = { nik = it },
        nama = nama,
        onNama = { nama = it },
        anak_ke = anak_ke,
        onAnak_ke = { anak_ke = it },
        jenisKelamin = jenisKelamin,
        onJenisKelamin = { jenisKelamin = it },
        tanggalLahir = tanggalLahir,
        onTanggalLahir = { tanggalLahir = it },
        posisi = posisi,
        anggotaKeluargaState = anggotaKeluargaState,
        onSaveAndGoLogin = { saveAndGoLogin() },
        onSaveAndBackToList = { saveAndBackToList() },
        onSuccessAnggotaKeluargaState = onSuccessAnggotaKeluargaState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteAnggotaKeluargaContent(
    nik: String,
    onNikChange: (String) -> Unit,
    nama: String,
    onNama: (String) -> Unit,
    anak_ke: Int,
    onAnak_ke: (Int) -> Unit,
    jenisKelamin: JenisKelamin?,
    onJenisKelamin: (JenisKelamin) -> Unit,
    tanggalLahir: LocalDate,
    onTanggalLahir: (LocalDate) -> Unit,
    posisi: PosisiKeluarga,
    anggotaKeluargaState: AnggotaKeluargaState,
    onSaveAndGoLogin: () -> Unit,
    onSaveAndBackToList: () -> Unit,
    onSuccessAnggotaKeluargaState: () -> Unit
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
                .padding(top = 100.dp)
                .verticalScroll(rememberScrollState()),
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
                        text = "Nama",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp) // Padding untuk teks
                    )

                    OutlinedTextField(
                        value = nama,
                        onValueChange = onNama,
                        label = {
                            Text("Nama")
                        },
                        placeholder = {
                            Text("Nama lengkap sesuai KTP")
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
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )

                    var expanded by remember { mutableStateOf(false) }
                    val options = listOf("Laki-laki", "Perempuan")
                    var selectedOptionText by remember {
                        mutableStateOf(jenisKelamin?.let {
                            if (it == JenisKelamin.LAKI) "Laki-laki" else "Perempuan"
                        } ?: "")
                    }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Jenis Kelamin") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.White
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                        val jenisKelaminEnum = when (selectionOption) {
                                            "Laki-laki" -> JenisKelamin.LAKI
                                            "Perempuan" -> JenisKelamin.PEREMPUAN
                                            else -> null
                                        }
                                        jenisKelaminEnum?.let { onJenisKelamin(it) }
                                    }
                                )
                            }
                        }
                    }

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
                    Spacer(modifier= Modifier.height(16.dp))

                    if (posisi != PosisiKeluarga.ISTRI) {
                        Text(
                            text = "Anak ke",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                        )

                        OutlinedTextField(
                            value = anak_ke.toString(),
                            onValueChange = { newValue ->
                                onAnak_ke(newValue.toIntOrNull() ?: 0)
                            },
                            label = { Text("Anak ke") },
                            placeholder = { Text("Contoh: 1") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Anak Ke") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Spacer(modifier= Modifier.height(34.dp))

                    when (anggotaKeluargaState) {
                        is AnggotaKeluargaState.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        is AnggotaKeluargaState.Success -> {
                            LaunchedEffect(Unit) {
                                Toast.makeText(context, (anggotaKeluargaState as AnggotaKeluargaState.Success).message, Toast.LENGTH_LONG).show()
                                onSuccessAnggotaKeluargaState()
                            }
                        }

                        is AnggotaKeluargaState.Error -> {
                            LaunchedEffect(Unit) {
                                Toast.makeText(context, (anggotaKeluargaState as AnggotaKeluargaState.Error).error, Toast.LENGTH_LONG).show()
                            }
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
                                if (nik.isBlank() || jenisKelamin == null || nama.isBlank()) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Semua field harus diisi!",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    onSaveAndBackToList()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tambah", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier= Modifier.height(16.dp))

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
                                if (nik.isBlank() || jenisKelamin == null || nama.isBlank()) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Semua field harus diisi!",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    onSaveAndGoLogin()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Daftar", color = Color.White, fontWeight = FontWeight.Bold)
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
fun CompleteAnggotaKeluargaPreview() {
    CompleteAnggotaKeluargaContent(
        nik = "",
        onNikChange = {},
        nama = "",
        onNama = {},
        anak_ke = 0,
        onAnak_ke = {},
        jenisKelamin = null,
        onJenisKelamin = {},
        tanggalLahir = LocalDate.now(),
        onTanggalLahir = {},
        posisi = PosisiKeluarga.ANAK,
        anggotaKeluargaState = AnggotaKeluargaState.Idle,
        onSaveAndBackToList = {},
        onSaveAndGoLogin = {},
        onSuccessAnggotaKeluargaState = {}
    )
}
