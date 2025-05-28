package com.example.posyandu.ui.Screen.Profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.tooling.preview.Preview

class EditFamilyMemberActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditFamilyMemberScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFamilyMemberScreen() {
    var name by remember { mutableStateOf("") }
    var nik by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var posyandu by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val genderOptions = listOf("Laki-laki", "Perempuan")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Informasi Anggota Keluarga") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali"
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FieldWithIcon(
                value = name,
                onValueChange = { name = it },
                label = "Nama",
                placeholder = "Nama lengkap sesuai KTP",
                icon = Icons.Default.Person
            )

            FieldWithIcon(
                value = nik,
                onValueChange = { nik = it },
                label = "NIK",
                placeholder = "Masukkan NIK Anda",
                icon = Icons.Default.DoneOutline
            )

            FieldWithIcon(
                value = status,
                onValueChange = { status = it },
                label = "Status",
                placeholder = "Pilih status anggota",
                icon = Icons.Default.Dataset
            )

            FieldWithIcon(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = "Tanggal Lahir",
                placeholder = "Pilih tanggal lahir Anda",
                icon = Icons.Default.CalendarToday
            )

            Text(text = "Jenis Kelamin", fontWeight = FontWeight.SemiBold)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = gender,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Pilih jenis kelamin Anda") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE5E5EA),
                        unfocusedContainerColor = Color(0xFFE5E5EA)

                    ),

                            modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genderOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                gender = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            FieldWithIcon(
                value = posyandu,
                onValueChange = { posyandu = it },
                label = "Posyandu",
                placeholder = "Posyandu aktif",
                icon = Icons.Default.Home
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Simpan data */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00597A),
                    contentColor = Color.White
                )
            ) {
                Text("Simpan", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FieldWithIcon(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector
) {
    Column {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            trailingIcon = { Icon(icon, contentDescription = null) },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE5E5EA),
                unfocusedContainerColor = Color(0xFFE5E5EA),
                focusedIndicatorColor = Color.Transparent,     // hilangkan garis saat fokus
                unfocusedIndicatorColor = Color.Transparent,   // hilangkan garis saat tidak fokus
                disabledIndicatorColor = Color.Transparent     // hilangkan garis saat disabled
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditFamilyMemberScreen() {
    EditFamilyMemberScreen()
}
