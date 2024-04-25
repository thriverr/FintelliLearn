package com.example.myscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

@Composable
fun CompoundInterestCalculator() {

    var startingBalance by remember { mutableStateOf("") }
    var annualInterestRate by remember { mutableStateOf("") }
    var compoundInterval by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var durationType by remember { mutableStateOf(DurationType.YEARS) }
    var result by remember { mutableStateOf(0.0) }
    var profit by remember { mutableStateOf(0.0) }
    // Change mutableDoubleStateOf to mutableStateOf

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Compound Interest Calculator", fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = startingBalance,
            onValueChange = { startingBalance = it },
            label = { Text("Starting Balance") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = annualInterestRate,
            onValueChange = { annualInterestRate = it },
            label = { Text("Annual Interest Rate (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = compoundInterval,
            onValueChange = { compoundInterval = it },
            label = { Text("Compound Interval(12 for monthly)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Duration Type: ", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            RadioButtonGroup(
                options = DurationType.values(),
                selectedOption = durationType,
                onSelectedOptionChange = { durationType = it }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                result = calculateCompoundInterest(
                    startingBalance.toDouble(),
                    annualInterestRate.toDouble(),
                    compoundInterval.toInt(),
                    duration.toInt(),
                    durationType


                )
                // Update the UI with the new result

                profit = result - startingBalance.toDouble()
            }
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Future value: Rs${"%.2f".format(result)}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Total Profit: Rs${"%.2f".format(profit)}",fontWeight = FontWeight.Bold)


    }
}


@Composable
fun <T> RadioButtonGroup(
    options: Array<T>,
    selectedOption: T,
    onSelectedOptionChange: (T) -> Unit
) {
    options.forEach { option ->
        Row(
            Modifier
                .padding(horizontal = 8.dp)
                .height(48.dp)
        ) {
            RadioButton(
                selected = option == selectedOption,
                onClick = { onSelectedOptionChange(option) },

                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = option.toString(),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

enum class DurationType {
    YEARS,
    MONTHS
}

fun calculateCompoundInterest(
    startingBalance: Double,
    annualInterestRate: Double,
    compoundInterval: Int,
    duration: Int,
    durationType: DurationType
): Double {
    val n: Double = when (durationType) {
        DurationType.YEARS -> duration.toDouble()
        DurationType.MONTHS -> duration / 12.0
    }
    val r = annualInterestRate / 100
    return startingBalance * (1 + r / compoundInterval).pow(compoundInterval * n)
}