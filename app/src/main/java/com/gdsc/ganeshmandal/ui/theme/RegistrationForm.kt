package com.gdsc.ganeshmandal.ui.theme

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm(){
    var nameOfMandal by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var addOfMandal by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var contactPerson by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var mobileNo by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var totalAreaCovered by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var totalAreaOpen by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var dateOfTechnicalEvaluation by remember{
        mutableStateOf("")
    }
    // Electrical Safety
    var powerConsumedByMandal by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var sizeOfCableInstalled by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var typeOfCable by remember{
        mutableStateOf(TextFieldValue(""))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    )
    {
        Text(
            text = "Technical Evaluation Form",
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = nameOfMandal,
            onValueChange = {
                nameOfMandal = it
            },
            placeholder = {
                Text(text = "Name of Mandal")
            },
            label = {
                Text(text = "Name of Mandal")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = addOfMandal,
            onValueChange = {
                addOfMandal = it
            },
            placeholder = {
                Text(text = "Address of Mandal")
            },
            label = {
                Text(text = "Address of Mandal")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = contactPerson,
            onValueChange = {
                contactPerson = it
            },
            placeholder = {
                Text(text = "Contact Person")
            },
            label = {
                Text(text = "Contact Person")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mobileNo,
            onValueChange = {
                if (it.text.length <= 10) mobileNo = it
            },
            placeholder = {
                Text(text = "Mobile No.")
            },
            label = {
                Text(text = "Mobile No.")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mobileNo,
            onValueChange = {
                if (it.text.length <= 10) mobileNo = it
            },
            placeholder = {
                Text(text = "Mobile No.")
            },
            label = {
                Text(text = "Mobile No.")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            OutlinedTextField(
                value = totalAreaCovered,
                onValueChange = {
                    totalAreaCovered = it
                },
                placeholder = {
                    Text(text = "Total Area(Covered)")
                },
                label = {
                    Text(text = "Total Area(Covered)")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            Text("  Sq. ft.")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            OutlinedTextField(
                value = totalAreaOpen,
                onValueChange = {
                    totalAreaOpen = it
                },
                placeholder = {
                    Text(text = "Total Area(Open)")
                },
                label = {
                    Text(text = "Total Area(Open)")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            Text("  Sq. ft.")
        }
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val datePicker = DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                dateOfTechnicalEvaluation = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            }, year, month, dayOfMonth
        )
        datePicker.datePicker.minDate = calendar.timeInMillis

        Row(){
            Column {
                Text(text = "Date of Technical evaluation:")
                Text(text = dateOfTechnicalEvaluation)
            }
            Button(onClick = {
                datePicker.show()
            }) {
                Text(text = "Select date", fontSize = 12.sp)
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun RegistrationFormPreview(){
    RegistrationForm()
}