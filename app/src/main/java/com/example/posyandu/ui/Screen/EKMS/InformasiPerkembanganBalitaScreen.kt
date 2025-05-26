package com.example.posyandu.ui.Screen.EKMS

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InformasiPerkembanganBalitaScreen() {
    val options = listOf("Informasi Berat Badan", "Informasi Tinggi Badan")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }

    val perkembanganBB = listOf(
        Triple("1 Bulan", "3.1 Kg", "Normal"),
        Triple("2 Bulan", "3.8 Kg", "Normal"),
        Triple("3 Bulan", "4.2 Kg", "Normal"),
        Triple("4 Bulan", "5.1 Kg", "Normal"),
        Triple("5 Bulan", "5.5 Kg", "Normal"),
        Triple("6 Bulan", "5.9 Kg", "Normal")
    )

    val perkembanganTB = listOf(
        Triple("1 Bulan", "50.0 cm", "Normal"),
        Triple("2 Bulan", "53.5 cm", "Normal"),
        Triple("3 Bulan", "56.0 cm", "Normal"),
        Triple("4 Bulan", "59.0 cm", "Normal"),
        Triple("5 Bulan", "61.5 cm", "Normal"),
        Triple("6 Bulan", "63.0 cm", "Normal")
    )

    val dataToShow = if (selectedOption == "Informasi Tinggi Badan") perkembanganTB else perkembanganBB

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Dropdown
            Box {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Pilih Informasi") },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "dropdown")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                selectedOption = label
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = selectedOption,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFFF9F9F9))
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TableCellPerkembanganBalita("Umur", true, Modifier.weight(1f))
                        TableCellPerkembanganBalita(
                            if (selectedOption == "Informasi Tinggi Badan") "Tinggi Badan" else "Berat Badan",
                            true,
                            Modifier.weight(1f)
                        )
                        TableCellPerkembanganBalita("Status Gizi", true, Modifier.weight(1f))
                    }
                    Divider()

                    dataToShow.forEach { (umur, nilai, status) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TableCellPerkembanganBalita(umur, false, Modifier.weight(1f))
                            TableCellPerkembanganBalita(nilai, false, Modifier.weight(1f))
                            TableCellPerkembanganBalita(status, false, Modifier.weight(1f))
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun TableCellPerkembanganBalita(text: String, isHeader: Boolean, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(horizontal = 8.dp),
        style = if (isHeader) MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        else MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Start
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewInformasiPerkembanganBalitaScreen() {
    InformasiPerkembanganBalitaScreen()
}
