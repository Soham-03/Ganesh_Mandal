package com.gdsc.ganeshmandal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.drjacky.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalAuditForm(
    launcher: ActivityResultLauncher<Intent>,
    storage: FirebaseStorage,
    edit: Boolean
){
    var progressState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ){
            var imgElectricalCabling = remember{
                mutableStateOf<Uri?>(null)
            }
            val context = LocalContext.current
            val yesOrNo = arrayOf("None","Average","Good","Excellent")
            val db = FirebaseFirestore.getInstance()
            var act = LocalContext.current as Activity
            Text(text = "A. Electrical safety audit", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "1. Electrical Cabling", fontSize = 18.sp)
            var expanded1 by remember {
                mutableStateOf(false)
            }
            var electricalCabling by remember{
                mutableStateOf("")
            }
            var pointsElectricalCabling by remember{
                mutableStateOf("")
            }
            when (electricalCabling) {
                "None" -> {
                    pointsElectricalCabling = "0"
                }
                "Average" -> {
                    pointsElectricalCabling = "1"
                }
                "Good" -> {
                    pointsElectricalCabling = "2"
                }
                "Excellent" -> {
                    pointsElectricalCabling = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded1,
                        onExpandedChange = {
                            expanded1 = !expanded1
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = electricalCabling,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded1,
                            onDismissRequest = { expanded1 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        electricalCabling = item
                                        expanded1 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsElectricalCabling,
                    onValueChange = {
                        pointsElectricalCabling = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[0]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 1
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "2. Electrical Terminations", fontSize = 18.sp)
            var electricalTerminations by remember {
                mutableStateOf("")
            }
            var pointsElectricalTerminations by remember {
                mutableStateOf("")
            }
            var expanded2 by remember {
                mutableStateOf(false)
            }
            when (electricalTerminations) {
                "None" -> {
                    pointsElectricalTerminations = "0"
                }
                "Average" -> {
                    pointsElectricalTerminations = "1"
                }
                "Good" -> {
                    pointsElectricalTerminations = "2"
                }
                "Excellent" -> {
                    pointsElectricalTerminations = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded2,
                        onExpandedChange = {
                            expanded2 = !expanded2
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = electricalTerminations,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        electricalTerminations= item
                                        expanded2 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsElectricalTerminations,
                    onValueChange = {
                        pointsElectricalTerminations = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[1]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 2
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "3. ELCB/ MCCB/ Switch Board Installation", fontSize = 18.sp)
            var switchBoardInstallation by remember {
                mutableStateOf("")
            }
            var pointsSwitchBoardInstallation by remember {
                mutableStateOf("")
            }
            var expanded3 by remember {
                mutableStateOf(false)
            }
            when (switchBoardInstallation) {
                "None" -> {
                    pointsSwitchBoardInstallation = "0"
                }
                "Average" -> {
                    pointsSwitchBoardInstallation = "1"
                }
                "Good" -> {
                    pointsSwitchBoardInstallation = "2"
                }
                "Excellent" -> {
                    pointsSwitchBoardInstallation = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded3,
                        onExpandedChange = {
                            expanded3 = !expanded3
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = switchBoardInstallation,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded3) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded3,
                            onDismissRequest = { expanded3 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        switchBoardInstallation= item
                                        expanded3 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsSwitchBoardInstallation,
                    onValueChange = {
                        pointsSwitchBoardInstallation = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[2]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 3
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "B. Fire Extinguishers and Fire Safety", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "4. Location of Fire Extinguishers", fontSize = 18.sp)
            var locationOfFireExtinguishers by remember {
                mutableStateOf("")
            }
            var pointsLocationOfFireExtinguishers by remember {
                mutableStateOf("")
            }
            var expanded4 by remember {
                mutableStateOf(false)
            }
            when (locationOfFireExtinguishers) {
                "None" -> {
                    pointsLocationOfFireExtinguishers = "0"
                }
                "Average" -> {
                    pointsLocationOfFireExtinguishers = "1"
                }
                "Good" -> {
                    pointsLocationOfFireExtinguishers = "2"
                }
                "Excellent" -> {
                    pointsLocationOfFireExtinguishers = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded4,
                        onExpandedChange = {
                            expanded4 = !expanded4
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = locationOfFireExtinguishers,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded4) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded4,
                            onDismissRequest = { expanded4 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        locationOfFireExtinguishers = item
                                        expanded4 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsLocationOfFireExtinguishers,
                    onValueChange = {
                        pointsLocationOfFireExtinguishers = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[3]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 4
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "5. validity of Fire Extinguishers", fontSize = 18.sp)
            var validityOfFireExtinguishers by remember {
                mutableStateOf("")
            }
            var pointsValidityOfFireExtinguishers by remember {
                mutableStateOf("")
            }
            var expanded5 by remember {
                mutableStateOf(false)
            }
            when (validityOfFireExtinguishers) {
                "None" -> {
                    pointsValidityOfFireExtinguishers = "0"
                }
                "Average" -> {
                    pointsValidityOfFireExtinguishers = "1"
                }
                "Good" -> {
                    pointsValidityOfFireExtinguishers = "2"
                }
                "Excellent" -> {
                    pointsValidityOfFireExtinguishers = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded5,
                        onExpandedChange = {
                            expanded5 = !expanded5
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = validityOfFireExtinguishers,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded5) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded5,
                            onDismissRequest = { expanded5 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        validityOfFireExtinguishers = item
                                        expanded5 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsValidityOfFireExtinguishers,
                    onValueChange = {
                        pointsValidityOfFireExtinguishers = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[4]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 5
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "6. Other Fire Safety arrangements", fontSize = 18.sp)
            var otherFireSafetyArrangements by remember {
                mutableStateOf("")
            }
            var pointsOtherFireSafetyArrangements by remember {
                mutableStateOf("")
            }
            var expanded6 by remember {
                mutableStateOf(false)
            }
            when (otherFireSafetyArrangements) {
                "None" -> {
                    pointsOtherFireSafetyArrangements = "0"
                }
                "Average" -> {
                    pointsOtherFireSafetyArrangements = "1"
                }
                "Good" -> {
                    pointsOtherFireSafetyArrangements = "2"
                }
                "Excellent" -> {
                    pointsOtherFireSafetyArrangements = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded6,
                        onExpandedChange = {
                            expanded6 = !expanded6
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = otherFireSafetyArrangements,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded6) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded6,
                            onDismissRequest = { expanded6 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        otherFireSafetyArrangements = item
                                        expanded6 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsOtherFireSafetyArrangements,
                    onValueChange = {
                        pointsOtherFireSafetyArrangements = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[5]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 6
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "C. Bomb and Metal Detectors", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "7. Operation of Metal Detectors", fontSize = 18.sp)
            var operationOfMetalDetectors by remember {
                mutableStateOf("")
            }
            var pointsOperationOfMetalDetectors by remember {
                mutableStateOf("")
            }
            var expanded7 by remember {
                mutableStateOf(false)
            }
            when (operationOfMetalDetectors) {
                "None" -> {
                    pointsOperationOfMetalDetectors = "0"
                }
                "Average" -> {
                    pointsOperationOfMetalDetectors = "1"
                }
                "Good" -> {
                    pointsOperationOfMetalDetectors = "2"
                }
                "Excellent" -> {
                    pointsOperationOfMetalDetectors = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded7,
                        onExpandedChange = {
                            expanded7 = !expanded7
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = operationOfMetalDetectors,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded7) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded7,
                            onDismissRequest = { expanded7 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        operationOfMetalDetectors = item
                                        expanded7 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsOperationOfMetalDetectors,
                    onValueChange = {
                        pointsOperationOfMetalDetectors = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[6]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 7
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "8. Operation of Bomb Detectors", fontSize = 18.sp)
            var operationOfBombDetectors by remember {
                mutableStateOf("")
            }
            var pointsOperationOfBombDetectors by remember {
                mutableStateOf("")
            }
            var expanded8 by remember {
                mutableStateOf(false)
            }
            when (operationOfBombDetectors) {
                "None" -> {
                    pointsOperationOfBombDetectors = "0"
                }
                "Average" -> {
                    pointsOperationOfBombDetectors = "1"
                }
                "Good" -> {
                    pointsOperationOfBombDetectors = "2"
                }
                "Excellent" -> {
                    pointsOperationOfBombDetectors = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded8,
                        onExpandedChange = {
                            expanded8 = !expanded8
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = operationOfBombDetectors,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded8) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded8,
                            onDismissRequest = { expanded8 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        operationOfBombDetectors = item
                                        expanded8 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsOperationOfBombDetectors,
                    onValueChange = {
                        pointsOperationOfBombDetectors = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[7]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 8
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "D. CCTV System", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "9. Surveillance Coverage", fontSize = 18.sp)
            var surveillanceCoverage by remember {
                mutableStateOf("")
            }
            var pointsSurveillanceCoverage by remember {
                mutableStateOf("")
            }
            var expanded9 by remember {
                mutableStateOf(false)
            }
            when (surveillanceCoverage) {
                "None" -> {
                    pointsSurveillanceCoverage = "0"
                }
                "Average" -> {
                    pointsSurveillanceCoverage = "1"
                }
                "Good" -> {
                    pointsSurveillanceCoverage = "2"
                }
                "Excellent" -> {
                    pointsSurveillanceCoverage = "3"
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded9,
                        onExpandedChange = {
                            expanded9 = !expanded9
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = surveillanceCoverage,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded9) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded9,
                            onDismissRequest = { expanded9 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        surveillanceCoverage = item
                                        expanded9 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsSurveillanceCoverage,
                    onValueChange = {
                        pointsSurveillanceCoverage = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[8]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 9
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "10. Surveillance Monitoring", fontSize = 18.sp)
            var surveillanceMonitoring by remember {
                mutableStateOf("")
            }
            var pointsSurveillanceMonitoring by remember {
                mutableStateOf("")
            }
            var expanded10 by remember {
                mutableStateOf(false)
            }
            when (surveillanceMonitoring) {
                "None" -> {
                    pointsSurveillanceMonitoring = "0"
                }
                "Average" -> {
                    pointsSurveillanceMonitoring = "1"
                }
                "Good" -> {
                    pointsSurveillanceMonitoring = "2"
                }
                "Excellent" -> {
                    pointsSurveillanceMonitoring = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded10,
                        onExpandedChange = {
                            expanded10 = !expanded10
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = surveillanceMonitoring,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded10) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded10,
                            onDismissRequest = { expanded10 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        surveillanceMonitoring = item
                                        expanded10 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsSurveillanceMonitoring,
                    onValueChange = {
                        pointsSurveillanceMonitoring = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[9]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 10
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "E. Public Address System", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "11. Public Address Coverage", fontSize = 18.sp)
            var publicAddressCoverage by remember {
                mutableStateOf("")
            }
            var pointsPublicAddressCoverage by remember {
                mutableStateOf("")
            }
            var expanded11 by remember {
                mutableStateOf(false)
            }
            when (publicAddressCoverage) {
                "None" -> {
                    pointsPublicAddressCoverage = "0"
                }
                "Average" -> {
                    pointsPublicAddressCoverage = "1"
                }
                "Good" -> {
                    pointsPublicAddressCoverage = "2"
                }
                "Excellent" -> {
                    pointsPublicAddressCoverage = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded11,
                        onExpandedChange = {
                            expanded11 = !expanded11
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = publicAddressCoverage,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded11) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded11,
                            onDismissRequest = { expanded11 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        publicAddressCoverage= item
                                        expanded11 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsPublicAddressCoverage,
                    onValueChange = {
                        pointsPublicAddressCoverage = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[10]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 11
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "12. Announcing Team", fontSize = 18.sp)
            var announcingTeam by remember {
                mutableStateOf("")
            }
            var pointsAnnouncingTeam by remember {
                mutableStateOf("")
            }
            var expanded12 by remember {
                mutableStateOf(false)
            }
            when (announcingTeam) {
                "None" -> {
                    pointsAnnouncingTeam = "0"
                }
                "Average" -> {
                    pointsAnnouncingTeam = "1"
                }
                "Good" -> {
                    pointsAnnouncingTeam = "2"
                }
                "Excellent" -> {
                    pointsAnnouncingTeam = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded12,
                        onExpandedChange = {
                            expanded12 = !expanded12
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = announcingTeam,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded12) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded12,
                            onDismissRequest = { expanded12 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        announcingTeam = item
                                        expanded12 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsAnnouncingTeam,
                    onValueChange = {
                        pointsAnnouncingTeam = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[11]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 12
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "F. Banners and FirstAid", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "13. Location of Banners", fontSize = 18.sp)
            var locationOfBanners by remember {
                mutableStateOf("")
            }
            var pointsLocationOfBanners by remember {
                mutableStateOf("")
            }
            var expanded13 by remember {
                mutableStateOf(false)
            }
            when (locationOfBanners) {
                "None" -> {
                    pointsLocationOfBanners = "0"
                }
                "Average" -> {
                    pointsLocationOfBanners = "1"
                }
                "Good" -> {
                    pointsLocationOfBanners = "2"
                }
                "Excellent" -> {
                    pointsLocationOfBanners = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded13,
                        onExpandedChange = {
                            expanded13 = !expanded13
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = locationOfBanners,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded13) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded13,
                            onDismissRequest = { expanded13 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        locationOfBanners = item
                                        expanded13 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsLocationOfBanners,
                    onValueChange = {
                        pointsLocationOfBanners = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[12]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 13
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "14. Medical Emergency Mitigation", fontSize = 18.sp)
            var medicalEmergencyMitigation by remember {
                mutableStateOf("")
            }
            var pointsMedicalEmergencyMitigation by remember {
                mutableStateOf("")
            }
            var expanded14 by remember {
                mutableStateOf(false)
            }
            when (medicalEmergencyMitigation) {
                "None" -> {
                    pointsMedicalEmergencyMitigation = "0"
                }
                "Average" -> {
                    pointsMedicalEmergencyMitigation = "1"
                }
                "Good" -> {
                    pointsMedicalEmergencyMitigation = "2"
                }
                "Excellent" -> {
                    pointsMedicalEmergencyMitigation = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded14,
                        onExpandedChange = {
                            expanded14 = !expanded14
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = medicalEmergencyMitigation,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded14) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded14,
                            onDismissRequest = { expanded14 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        medicalEmergencyMitigation = item
                                        expanded14 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsMedicalEmergencyMitigation,
                    onValueChange = {
                        pointsMedicalEmergencyMitigation = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[13]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 14
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "G. Security and Volunteers", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "15. Security and Volunteer Team", fontSize = 18.sp)
            var securityAndVolunteerTeam by remember {
                mutableStateOf("")
            }
            var pointsSecurityAndVolunteerTeam by remember {
                mutableStateOf("")
            }
            var expanded15 by remember {
                mutableStateOf(false)
            }
            when (securityAndVolunteerTeam) {
                "None" -> {
                    pointsSecurityAndVolunteerTeam = "0"
                }
                "Average" -> {
                    pointsSecurityAndVolunteerTeam = "1"
                }
                "Good" -> {
                    pointsSecurityAndVolunteerTeam = "2"
                }
                "Excellent" -> {
                    pointsSecurityAndVolunteerTeam = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded15,
                        onExpandedChange = {
                            expanded15 = !expanded15
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = securityAndVolunteerTeam,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded15) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded15,
                            onDismissRequest = { expanded15 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        securityAndVolunteerTeam = item
                                        expanded15 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsSecurityAndVolunteerTeam,
                    onValueChange = {
                        pointsSecurityAndVolunteerTeam = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[14]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 15
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "16. Competency of team on Fire Safety and Security", fontSize = 18.sp)
            var competencyOfTeam by remember {
                mutableStateOf("")
            }
            var pointsCompetencyOfTeam by remember {
                mutableStateOf("")
            }
            var expanded16 by remember {
                mutableStateOf(false)
            }
            when (competencyOfTeam) {
                "None" -> {
                    pointsCompetencyOfTeam = "0"
                }
                "Average" -> {
                    pointsCompetencyOfTeam = "1"
                }
                "Good" -> {
                    pointsCompetencyOfTeam = "2"
                }
                "Excellent" -> {
                    pointsCompetencyOfTeam = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded16,
                        onExpandedChange = {
                            expanded16 = !expanded16
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = competencyOfTeam,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded16) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded16,
                            onDismissRequest = { expanded16 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        competencyOfTeam = item
                                        expanded16 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsCompetencyOfTeam,
                    onValueChange = {
                        pointsCompetencyOfTeam = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[15]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 16
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "H. Other Arrangements", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "17. Emergency Evacuation Process", fontSize = 18.sp)
            var emergencyEvacuationProcess by remember {
                mutableStateOf("")
            }
            var pointsEmergencyEvacuationProcess by remember {
                mutableStateOf("")
            }
            var expanded17 by remember {
                mutableStateOf(false)
            }
            when (emergencyEvacuationProcess) {
                "None" -> {
                    pointsEmergencyEvacuationProcess = "0"
                }
                "Average" -> {
                    pointsEmergencyEvacuationProcess = "1"
                }
                "Good" -> {
                    pointsEmergencyEvacuationProcess = "2"
                }
                "Excellent" -> {
                    pointsEmergencyEvacuationProcess = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded17,
                        onExpandedChange = {
                            expanded17 = !expanded17
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = emergencyEvacuationProcess,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded17) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded17,
                            onDismissRequest = { expanded17 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        emergencyEvacuationProcess = item
                                        expanded17 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsEmergencyEvacuationProcess,
                    onValueChange = {
                        pointsEmergencyEvacuationProcess = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[16]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 17
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "18. Overall Proactiveness on Fire safety and Security", fontSize = 18.sp)
            var proactivenessOfFireSafety by remember {
                mutableStateOf("")
            }
            var pointsProactivenessOfFireSafety by remember {
                mutableStateOf("")
            }
            var expanded18 by remember {
                mutableStateOf(false)
            }
            when (proactivenessOfFireSafety) {
                "None" -> {
                    pointsProactivenessOfFireSafety = "0"
                }
                "Average" -> {
                    pointsProactivenessOfFireSafety = "1"
                }
                "Good" -> {
                    pointsProactivenessOfFireSafety = "2"
                }
                "Excellent" -> {
                    pointsProactivenessOfFireSafety = "3"
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded18,
                        onExpandedChange = {
                            expanded18 = !expanded18
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = proactivenessOfFireSafety,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded18) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded18,
                            onDismissRequest = { expanded18 = false }
                        ) {
                            yesOrNo.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        proactivenessOfFireSafety = item
                                        expanded18 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsProactivenessOfFireSafety,
                    onValueChange = {
                        pointsProactivenessOfFireSafety = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[17]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 18
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            Text(text = "19. Is the Mandal Insured", fontSize = 18.sp)
            var isMandalInsured by remember {
                mutableStateOf("")
            }
            var pointsIsMandalInsured by remember {
                mutableStateOf("")
            }
            var expanded19 by remember {
                mutableStateOf(false)
            }
            when (isMandalInsured) {
                "No" -> {
                    pointsIsMandalInsured = "0"
                }
                "Yes" -> {
                    pointsIsMandalInsured = "3"
                }
            }
            var yesOrNo1 = arrayOf("Yes","No")
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded19,
                        onExpandedChange = {
                            expanded19 = !expanded19
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = isMandalInsured,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded19) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded19,
                            onDismissRequest = { expanded19 = false }
                        ) {
                            yesOrNo1.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        isMandalInsured = item
                                        expanded19 = false
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    value = pointsIsMandalInsured,
                    onValueChange = {
                        pointsIsMandalInsured = it
                    },
                    placeholder = {
                        Text(text = "points")
                    },
                    label = {
                        Text(text = "points")
                    },
                    enabled = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
            }
            AsyncImage(
                model = if(Global.currentCapturedImage.isNotEmpty()){Global.currentCapturedImage[18]} else {null},
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            Button(onClick = {
                Global.clickedOnButton = 19
                launcher.launch(
                    ImagePicker.with(act)
                        .cameraOnly()
                        .createIntent()
                )
            }) {
                Text(text = "Upload Image")
            }
            var totalScoreText by remember{
                mutableStateOf("0")
            }
            Text(
                "Total Points out of 57: $totalScoreText",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            var auditorsName by remember {
                mutableStateOf(TextFieldValue(""))
            }
            OutlinedTextField(
                value = Global.adminId.toString(),
                onValueChange = {
//                auditorsName = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Name of Auditor")
                },
                label = {
                    Text(text = "Name of Auditor")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if(
                        !TextUtils.isEmpty(pointsElectricalCabling) &&
                        !TextUtils.isEmpty(pointsElectricalTerminations)&&
                        !TextUtils.isEmpty(pointsSwitchBoardInstallation)&&
                        !TextUtils.isEmpty(pointsLocationOfFireExtinguishers)&&
                        !TextUtils.isEmpty(pointsValidityOfFireExtinguishers)&&
                        !TextUtils.isEmpty(pointsOtherFireSafetyArrangements)&&
                        !TextUtils.isEmpty(pointsOperationOfMetalDetectors)&&
                        !TextUtils.isEmpty(pointsOperationOfBombDetectors)&&
                        !TextUtils.isEmpty(pointsSurveillanceCoverage)&&
                        !TextUtils.isEmpty(pointsSurveillanceMonitoring)&&
                        !TextUtils.isEmpty(pointsPublicAddressCoverage)&&
                        !TextUtils.isEmpty(pointsAnnouncingTeam)&&
                        !TextUtils.isEmpty(pointsLocationOfBanners)&&
                        !TextUtils.isEmpty(pointsMedicalEmergencyMitigation)&&
                        !TextUtils.isEmpty(pointsSecurityAndVolunteerTeam)&&
                        !TextUtils.isEmpty(pointsCompetencyOfTeam)&&
                        !TextUtils.isEmpty(pointsEmergencyEvacuationProcess)&&
                        !TextUtils.isEmpty(pointsProactivenessOfFireSafety)&&
                        !TextUtils.isEmpty(pointsIsMandalInsured)
                    ){
                        val totalScore = pointsElectricalCabling.toInt()+
                                pointsElectricalTerminations.toInt()+
                                pointsSwitchBoardInstallation.toInt()+
                                pointsLocationOfFireExtinguishers.toInt()+
                                pointsValidityOfFireExtinguishers.toInt()+
                                pointsOtherFireSafetyArrangements.toInt()+
                                pointsOperationOfMetalDetectors.toInt()+
                                pointsOperationOfBombDetectors.toInt()+
                                pointsSurveillanceCoverage.toInt()+
                                pointsSurveillanceMonitoring.toInt()+
                                pointsPublicAddressCoverage.toInt()+
                                pointsAnnouncingTeam.toInt()+
                                pointsLocationOfBanners.toInt()+
                                pointsMedicalEmergencyMitigation.toInt()+
                                pointsSecurityAndVolunteerTeam.toInt()+
                                pointsCompetencyOfTeam.toInt()+
                                pointsEmergencyEvacuationProcess.toInt()+
                                pointsProactivenessOfFireSafety.toInt()+
                                pointsIsMandalInsured.toInt()
                        totalScoreText = totalScore.toString()
                    }
                    else{
                        Toast.makeText(context, "Some Field is Missing", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Calculate Score")
            }
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    if (
                        !TextUtils.isEmpty(pointsElectricalCabling) &&
                        !TextUtils.isEmpty(pointsElectricalTerminations) &&
                        !TextUtils.isEmpty(pointsSwitchBoardInstallation) &&
                        !TextUtils.isEmpty(pointsLocationOfFireExtinguishers) &&
                        !TextUtils.isEmpty(pointsValidityOfFireExtinguishers) &&
                        !TextUtils.isEmpty(pointsOtherFireSafetyArrangements) &&
                        !TextUtils.isEmpty(pointsOperationOfMetalDetectors) &&
                        !TextUtils.isEmpty(pointsOperationOfBombDetectors) &&
                        !TextUtils.isEmpty(pointsSurveillanceCoverage) &&
                        !TextUtils.isEmpty(pointsSurveillanceMonitoring) &&
                        !TextUtils.isEmpty(pointsPublicAddressCoverage) &&
                        !TextUtils.isEmpty(pointsAnnouncingTeam) &&
                        !TextUtils.isEmpty(pointsLocationOfBanners) &&
                        !TextUtils.isEmpty(pointsMedicalEmergencyMitigation) &&
                        !TextUtils.isEmpty(pointsSecurityAndVolunteerTeam) &&
                        !TextUtils.isEmpty(pointsCompetencyOfTeam) &&
                        !TextUtils.isEmpty(pointsEmergencyEvacuationProcess) &&
                        !TextUtils.isEmpty(pointsProactivenessOfFireSafety) &&
                        !TextUtils.isEmpty(pointsIsMandalInsured)
                    ) {

                        val totalScore = pointsElectricalCabling.toInt() +
                                pointsElectricalTerminations.toInt() +
                                pointsSwitchBoardInstallation.toInt() +
                                pointsLocationOfFireExtinguishers.toInt() +
                                pointsValidityOfFireExtinguishers.toInt() +
                                pointsOtherFireSafetyArrangements.toInt() +
                                pointsOperationOfMetalDetectors.toInt() +
                                pointsOperationOfBombDetectors.toInt() +
                                pointsSurveillanceCoverage.toInt() +
                                pointsSurveillanceMonitoring.toInt() +
                                pointsPublicAddressCoverage.toInt() +
                                pointsAnnouncingTeam.toInt() +
                                pointsLocationOfBanners.toInt() +
                                pointsMedicalEmergencyMitigation.toInt() +
                                pointsSecurityAndVolunteerTeam.toInt() +
                                pointsCompetencyOfTeam.toInt() +
                                pointsEmergencyEvacuationProcess.toInt() +
                                pointsProactivenessOfFireSafety.toInt() +
                                pointsIsMandalInsured.toInt()
                        for(image in Global.currentCapturedImage){
                            println("Image:"+image.toString())
                        }
                        for(i in 0..Global.currentCapturedImage.lastIndex){
                            println("At Index: "+(i+1))
                        }
                        println("Size: "+Global.currentCapturedImage.size)
                        coroutineScope.launch {
                            progressState = true
                            val hashNewAudit = uploadImagesAndSetHashmapFinal(storage, coroutineScope)
                            hashNewAudit["pointsElectricalCabling"] = pointsElectricalCabling
                            hashNewAudit["pointsElectricalTerminations"] = pointsElectricalTerminations
                            hashNewAudit["pointsSwitchBoardInstallation"] = pointsSwitchBoardInstallation
                            hashNewAudit["pointsLocationOfFireExtinguishers"] = pointsLocationOfFireExtinguishers
                            hashNewAudit["pointsValidityOfFireExtinguishers"] = pointsValidityOfFireExtinguishers
                            hashNewAudit["pointsOtherFireSafetyArrangements"] = pointsOtherFireSafetyArrangements
                            hashNewAudit["pointsOperationOfMetalDetectors"] = pointsOperationOfMetalDetectors
                            hashNewAudit["pointsOperationOfBombDetectors"] = pointsOperationOfBombDetectors
                            hashNewAudit["pointsSurveillanceCoverage"] =  pointsSurveillanceCoverage
                            hashNewAudit["pointsSurveillanceMonitoring"] = pointsSurveillanceMonitoring
                            hashNewAudit["pointsPublicAddressCoverage"] = pointsPublicAddressCoverage
                            hashNewAudit["pointsAnnouncingTeam"] = pointsAnnouncingTeam
                            hashNewAudit["pointsLocationOfBanners"] = pointsLocationOfBanners
                            hashNewAudit["pointsMedicalEmergencyMitigation"] = pointsMedicalEmergencyMitigation
                            hashNewAudit["pointsSecurityAndVolunteerTeam"] = pointsSecurityAndVolunteerTeam
                            hashNewAudit["pointsCompetencyOfTeam"] = pointsCompetencyOfTeam
                            hashNewAudit["pointsEmergencyEvacuationProcess"] = pointsEmergencyEvacuationProcess
                            hashNewAudit["pointsProactivenessOfFireSafety"] = pointsProactivenessOfFireSafety
                            hashNewAudit["pointsIsMandalInsured"] = pointsIsMandalInsured
                            hashNewAudit["totalScoreOfAudit"] = totalScoreText.toString()
                            println("Full Hash: "+hashNewAudit)
                            ////
                            db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId).collection("audits").document(Global.adminId!!)
                                .set(hashNewAudit)
                                .addOnSuccessListener {
                                    val hash = HashMap<String, Any>()
                                    hash["secondAuditStatus"] = true
                                    db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId)
                                        .collection("audits").get()
                                        .addOnSuccessListener {audits->
                                            if(audits.isEmpty){
                                                hash["finalAuditScore"] = totalScoreText
                                            }
                                            else{
                                                var total = 0
                                                for(audit in audits){
                                                    total += audit["totalScoreOfAudit"].toString().toInt()
                                                }
                                                hash["finalAuditScore"] = total.toString()
                                            }
                                            db.collection("mandals").document(Global.selectedMandal!!.mandalId)
                                                .update(hash)
                                                .addOnSuccessListener {
                                                    db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId)
                                                        .update(hash)
                                                        .addOnSuccessListener {
                                                            Toast.makeText(context, "2nd Audit Submitted Successfully", Toast.LENGTH_SHORT).show()
                                                            progressState = false
                                                            val intent = Intent(context,Top20MandalsAfter1stAudit::class.java)
                                                            Global.currentCapturedImage = SnapshotStateList()
                                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                                            context.startActivity(intent)
                                                        }
                                                }
                                        }
                                }
                        }
//                    for(i in 0 ..Global.currentCapturedImage.size){
//                        reference.putFile(localUri)
//                            .addOnSuccessListener {
//                                reference.downloadUrl.addOnSuccessListener {
//                                    val downloadUri = it
//                                    //1
//                                    if(i==0 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgElectricalCabling"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgElectricalCabling"] = "null"
//                                    }
//                                    //2
//                                    if(i==1 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgElectricalTerminations"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgElectricalTerminations"] = "null"
//                                    }
//                                    //3
//                                    if(i==2 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgSwitchBoardInstallation"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgSwitchBoardInstallation"] = "null"
//                                    }
//                                    //4
//                                    if(i==3 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgLocationOfFireExtinguishers"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgLocationOfFireExtinguishers"] = "null"
//                                    }
//                                    //5
//                                    if(i==4 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgValidityOfFireExtinguishers"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgValidityOfFireExtinguishers"] = "null"
//                                    }
//                                    //6
//                                    if(i==5 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgOtherFireSafetyArrangements"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgOtherFireSafetyArrangements"] = "null"
//                                    }
//                                    //7
//                                    if(i==6 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgOperationOfMetalDetectors"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgOperationOfMetalDetectors"] = "null"
//                                    }
//                                    //8
//                                    if(i==7 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgOperationOfBombDetectors"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgOperationOfBombDetectors"] = "null"
//                                    }
//                                    //9
//                                    if(i==8 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgSurveillanceCoverage"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgSurveillanceCoverage"] = "null"
//                                    }
//                                    //10
//                                    if(i==9 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgSurveillanceMonitoring"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgSurveillanceMonitoring"] = "null"
//                                    }
//                                    //11
//                                    if(i==10 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgPublicAddressCoverage"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgPublicAddressCoverage"] = "null"
//                                    }
//                                    //12
//                                    if(i==11 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgAnnouncingTeam"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgAnnouncingTeam"] = "null"
//                                    }
//                                    //13
//                                    if(i==12 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgLocationOfBanners"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgLocationOfBanners"] = "null"
//                                    }
//                                    //14
//                                    if(i==13 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgMedicalEmergencyMitigation"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgMedicalEmergencyMitigation"] = "null"
//                                    }
//                                    //15
//                                    if(i==14 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgSecurityAndVolunteerTeam"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgSecurityAndVolunteerTeam"] = "null"
//                                    }
//                                    //16
//                                    if(i==15 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgCompetencyOfTeam"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgCompetencyOfTeam"] = "null"
//                                    }
//                                    //17
//                                    if(i==16 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgEmergencyEvacuationProcess"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgEmergencyEvacuationProcess"] = "null"
//                                    }
//                                    //18
//                                    if(i==17 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgProactivenessOfFireSafety"] = downloadUri.toString()
//                                    }
//                                    else{
//                                        hashNewAudit["imgProactivenessOfFireSafety"] = "null"
//                                    }
//                                    //19
//                                    if(i==18 && Global.currentCapturedImage[i]!=null){
//                                        hashNewAudit["imgIsMandalInsured"] = downloadUri.toString()
//                                        println("ListOfImages: "+hashNewAudit)
//                                    }
//                                    else{
//                                        hashNewAudit["imgIsMandalInsured"] = "null"
//                                        println("ListOfImages: "+hashNewAudit)
//                                    }
//                                    //
//                                    //
//                                }
//                                    .addOnFailureListener {e->
//                                        e.printStackTrace()
//                                    }
//                            }
//                    }
                        totalScoreText = totalScore.toString()
//                    if(Global.selectedMandal != null){
//                        db.collection("mandalsSelectedForNext").document(Global.adminId!!)
//                            .set(hashNewAudit)
//                            .addOnSuccessListener {
//                                db.collection("mandals").document(Global.selectedMandal!!.mandalId)
//                                    .update(hash)
//                                    .addOnSuccessListener {
//                                        db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId)
//                                            .update(hash)
//                                            .addOnSuccessListener {
//                                                Toast.makeText(context, "2nd Audit Submitted Successfully", Toast.LENGTH_SHORT).show()
////                                            val intent = Intent(context, MandalsSelectedFor1stAuditList::class.java)
////                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
////                                            context.startActivity(intent)
//                                            }
//                                    }
//                            }
//                    }
//                    else{
//                        Toast.makeText(context, "Null Mandal", Toast.LENGTH_SHORT).show()
//                    }
                        //
//
//                    hashNewAudit["imgElectricalTerminations"] = listOfUri[1].toString()
//                    hashNewAudit["imgSwitchBoardInstallation"] = listOfUri[2].toString()
//                    hashNewAudit["imgLocationOfFireExtinguishers"] = listOfUri[3].toString()
//                    hashNewAudit["imgValidityOfFireExtinguishers"] = listOfUri[4].toString()
//                    hashNewAudit["imgOtherFireSafetyArrangements"] = listOfUri[5].toString()
//                    hashNewAudit["imgOperationOfMetalDetectors"] = listOfUri[6].toString()
//                    hashNewAudit["imgOperationOfBombDetectors"] = listOfUri[7].toString()
//                    hashNewAudit["imgSurveillanceCoverage"] =  listOfUri[8].toString()
//                    hashNewAudit["imgSurveillanceMonitoring"] = listOfUri[9].toString()
//                    hashNewAudit["imgPublicAddressCoverage"] = listOfUri[10].toString()
//                    hashNewAudit["imgAnnouncingTeam"] = listOfUri[11].toString()
//                    hashNewAudit["imgLocationOfBanners"] = listOfUri[12].toString()
//                    hashNewAudit["imgMedicalEmergencyMitigation"] = listOfUri[13].toString()
//                    hashNewAudit["imgSecurityAndVolunteerTeam"] = listOfUri[14].toString()
//                    hashNewAudit["imgCompetencyOfTeam"] = listOfUri[15].toString()
//                    hashNewAudit["imgEmergencyEvacuationProcess"] = listOfUri[16].toString()
//                    hashNewAudit["imgProactivenessOfFireSafety"] = listOfUri[17].toString()
//                    hashNewAudit["imgIsMandalInsured"] = listOfUri[18].toString()
                    } else {
                        Toast.makeText(context, "Some Fields are Missing", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Submit Audit")
            }
        }
        AnimatedVisibility(visible = progressState ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center))
            }
        }
    }

}

suspend fun uploadImagesAndSetHashmapFinal(storage: FirebaseStorage, coroutineScope: CoroutineScope): HashMap<String,String>{
    val hashNewAudit = HashMap<String, String>()
    for(i in 0 until Global.currentCapturedImage.size){
        if(Global.currentCapturedImage[i]!=null){
            val reference = storage.reference.child("images/${Global.currentCapturedImage[i]}")
                reference.putFile(Global.currentCapturedImage[i]!!)
                    .addOnSuccessListener {
                        coroutineScope.launch {
                        reference.downloadUrl.addOnSuccessListener {
                            val downloadUri = it
                            if(i==0)
                            {
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgElectricalCabling"] = downloadUri.toString()
                                }
                            }
                            //2
                            if(i==1){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgElectricalTerminations"] = downloadUri.toString()
                                }
                            }
                            //3
                            if(i==2){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgSwitchBoardInstallation"] = downloadUri.toString()
                                }
                            }
                            //4
                            if(i==3){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgLocationOfFireExtinguishers"] = downloadUri.toString()
                                }
                            }
                            //5
                            if(i==4){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgValidityOfFireExtinguishers"] = downloadUri.toString()
                                }
                            }
                            //6
                            if(i==5){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgOtherFireSafetyArrangements"] = downloadUri.toString()
                                }
                            }
                            //7
                            if(i==6){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgOperationOfMetalDetectors"] = downloadUri.toString()
                                }
                            }
                            //8
                            if(i==7){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgOperationOfBombDetectors"] = downloadUri.toString()
                                }
                            }
                            //9
                            if(i==8){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgSurveillanceCoverage"] = downloadUri.toString()
                                }
                            }
                            //10
                            if(i==9){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgSurveillanceMonitoring"] = downloadUri.toString()
                                }
                            }
                            //11
                            if(i==10){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgPublicAddressCoverage"] = downloadUri.toString()
                                }
                            }
                            //continue from here
                            //12
                            if(i==11){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgAnnouncingTeam"] = downloadUri.toString()
                                }
                            }
                            //13
                            if(i==12){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgLocationOfBanners"] = downloadUri.toString()
                                }
                            }
                            //14
                            if(i==13){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgMedicalEmergencyMitigation"] = downloadUri.toString()
                                }
                            }
                            //15
                            if(i==14){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgSecurityAndVolunteerTeam"] = downloadUri.toString()
                                }
                            }
                            //16
                            if(i==15){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgCompetencyOfTeam"] = downloadUri.toString()
                                }
                            }
                            //17
                            if(i==16){
                                if(Global.currentCapturedImage[i]!=null){
                                    hashNewAudit["imgEmergencyEvacuationProcess"] = downloadUri.toString()
                                }
                            }
                            //18
                            if(i==17) {
                                if (Global.currentCapturedImage[i] != null) {
                                    hashNewAudit["imgProactivenessOfFireSafety"] = downloadUri.toString()
                                }
                            }
                            //19
                            if(i==18){
                                println("Inside the last index and the image is uploaded")
                                hashNewAudit["imgIsMandalInsured"] = downloadUri.toString()
                            }
                            if(i==19){
                                println("Inside the last index and the image is uploaded")
                            }
                        }.await()
                    }
            }.await()
        }
        else{
            if(i==0)
            {
                hashNewAudit["imgElectricalCabling"] = "null"
            }
            //2
            if(i==1){
                hashNewAudit["imgElectricalTerminations"] = "null"
            }
            //3
            if(i==2){
                hashNewAudit["imgSwitchBoardInstallation"] = "null"
            }
            //4
            if(i==3){

                hashNewAudit["imgLocationOfFireExtinguishers"] = "null"
            }
            //5
            if(i==4){


                hashNewAudit["imgValidityOfFireExtinguishers"] = "null"
            }
            //6
            if(i==5){

                hashNewAudit["imgOtherFireSafetyArrangements"] = "null"
            }
            //7
            if(i==6){

                hashNewAudit["imgOperationOfMetalDetectors"] = "null"
            }
            //8
            if(i==7){

                hashNewAudit["imgOperationOfBombDetectors"] = "null"
            }
            //9
            if(i==8){
                hashNewAudit["imgSurveillanceCoverage"] = "null"
            }
            //10
            if(i==9){
                hashNewAudit["imgSurveillanceMonitoring"] = "null"
            }
            //11
            if(i==10){

                hashNewAudit["imgPublicAddressCoverage"] = "null"
            }
            //continue from here
            //12
            if(i==11){

                hashNewAudit["imgAnnouncingTeam"] = "null"
            }
            //13
            if(i==12){

                hashNewAudit["imgLocationOfBanners"] = "null"
            }
            //14
            if(i==13){

                hashNewAudit["imgMedicalEmergencyMitigation"] = "null"
            }
            //15
            if(i==14){

                hashNewAudit["imgSecurityAndVolunteerTeam"] = "null"
            }
            //16
            if(i==15){

                hashNewAudit["imgCompetencyOfTeam"] = "null"
            }
            //17
            if(i==16){

                hashNewAudit["imgEmergencyEvacuationProcess"] = "null"
            }
            //18
            if(i==17) {
                hashNewAudit["imgProactivenessOfFireSafety"] = "null"
            }
            //19
            if(i==18){
                hashNewAudit["imgIsMandalInsured"] = "null"
            }
        }

    }
    return hashNewAudit
}

@Preview
@Composable
fun FinalFormPreview(){
//    FinalAuditForm()
}