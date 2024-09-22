package com.example.fuelcalculatorlab1task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fuelcalculatorlab1task2.ui.theme.FuelCalculatorLab1Task2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelCalculatorLab1Task2Theme {
                FuelCalculatorScreen()
            }
        }
    }
}

@Composable
fun FuelCalculatorScreen() {
    //Збереження отриманих значень
    var cg by remember { mutableStateOf("85.50") }
    var hg by remember { mutableStateOf("11.20") }
    var og by remember { mutableStateOf("0.80") }
    var sg by remember { mutableStateOf("2.50") }
    var qidaf by remember { mutableStateOf("40.40") }
    var wg by remember { mutableStateOf("2.00") }
    var ag by remember { mutableStateOf("0.15") }
    var vg by remember { mutableStateOf("333.3") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Поля введення
        OutlinedTextField(
            value = cg,
            onValueChange = { cg = it },
            label = { Text("CГ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = hg,
            onValueChange = { hg = it },
            label = { Text("HГ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = og,
            onValueChange = { og = it },
            label = { Text("OГ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = sg,
            onValueChange = { sg = it },
            label = { Text("SГ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = qidaf,
            onValueChange = { qidaf = it },
            label = { Text("Qidaf (МДж/кг)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = wg,
            onValueChange = { wg = it },
            label = { Text("WГ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ag,
            onValueChange = { ag = it },
            label = { Text("AГ (%") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = vg,
            onValueChange = { vg = it },
            label = { Text("VГ (мг/кг)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Кнопка для обчислення
        Button(
            onClick = {
                val cgVal = cg.toDoubleOrNull() ?: 0.0
                val hgVal = hg.toDoubleOrNull() ?: 0.0
                val ogVal = og.toDoubleOrNull() ?: 0.0
                val sgVal = sg.toDoubleOrNull() ?: 0.0
                val qidafVal = qidaf.toDoubleOrNull() ?: 0.0
                val wgVal = wg.toDoubleOrNull() ?: 0.0
                val agVal = ag.toDoubleOrNull() ?: 0.0
                val vgVal = vg.toDoubleOrNull() ?: 0.0

                // Розрахунок складу робочої маси та нижчої теплоти згоряння на робочу масу
                val cr = cgVal * (100 - wgVal - agVal) / 100
                val hr = hgVal * (100 - wgVal - agVal) / 100
                val or = ogVal * (100 - wgVal - agVal) / 100
                val sr = sgVal * (100 - wgVal - agVal) / 100
                val vr = vgVal * (100 - wgVal) / 100
                val qir = qidafVal * (100 - wgVal - agVal) / 100 - 0.025 * wgVal

                //Результат
                result = """
                    Склад робочої маси:
                    CР = ${roundup(cr)}%
                    HР = ${roundup(hr)}%
                    OР = ${roundup(or)}%
                    SР = ${roundup(sr)}%
                    VР = ${roundup(vr)} мг/кг
                    AР = $agVal%
                    Нижча теплота згоряння (Qir) = ${roundup(qir)} МДж/кг
                """.trimIndent()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Text(text = result)
    }
}

//Округлення
fun roundup(value: Double): String {
    return String.format("%.2f", value)
}