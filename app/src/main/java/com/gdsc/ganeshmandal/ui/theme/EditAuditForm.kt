package com.gdsc.ganeshmandal

import android.app.Activity
import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.gdsc.ganeshmandal.model.MandalFinalAudit
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAuditForm(
    launcher: ActivityResultLauncher<Intent>,
    storage: FirebaseStorage,
    audit: MandalFinalAudit?
){
    var progressState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ){
            var currentAudit by remember {
                mutableStateOf<MandalFinalAudit?>(null)
            }
            currentAudit = audit
            for(i in 0..Global.currentCapturedImage.size){
                if(i==0)
                {
                    if(currentAudit!!.imgElectricalCabling!="null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgElectricalCabling.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //2
                if(i==1){
                    if(currentAudit!!.imgElectricalTerminations!="null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgElectricalTerminations.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //3
                if(i==2){
                    if(currentAudit!!.imgSwitchBoardInstallation != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgSwitchBoardInstallation.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //4
                if(i==3){
                    if(currentAudit!!.imgLocationOfFireExtinguishers != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgLocationOfFireExtinguishers.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //5
                if(i==4){
                    if(currentAudit!!.imgValidityOfFireExtinguishers != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgValidityOfFireExtinguishers.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //6
                if(i==5){
                    if(currentAudit!!.imgOtherFireSafetyArrangements != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgOtherFireSafetyArrangements.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //7
                if(i==6){
                    if(currentAudit!!.imgOperationOfMetalDetectors != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgOperationOfMetalDetectors.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //8
                if(i==7){
                    if(currentAudit!!.imgOperationOfBombDetectors != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgOperationOfBombDetectors.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //9
                if(i==8){
                    if(currentAudit!!.imgSurveillanceCoverage != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgSurveillanceCoverage.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //10
                if(i==9){
                    if(currentAudit!!.imgSurveillanceMonitoring != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgSurveillanceMonitoring.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //11
                if(i==10){
                    if(currentAudit!!.imgPublicAddressCoverage != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgPublicAddressCoverage.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //continue from here
                //12
                if(i==11){
                    if(currentAudit!!.imgAnnouncingTeam != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgAnnouncingTeam.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //13
                if(i==12){
                    if(currentAudit!!.imgLocationOfBanners != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgLocationOfBanners.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //14
                if(i==13){
                    if(currentAudit!!.imgMedicalEmergencyMitigation != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgMedicalEmergencyMitigation.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //15
                if(i==14){
                    if(currentAudit!!.imgSecurityAndVolunteerTeam != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgSecurityAndVolunteerTeam.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //16
                if(i==15){
                    if(currentAudit!!.imgCompetencyOfTeam != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgCompetencyOfTeam.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //17
                if(i==16){
                    if(currentAudit!!.imgEmergencyEvacuationProcess != "null"){
                        Global.currentCapturedImage[i] = currentAudit!!.imgEmergencyEvacuationProcess.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //18
                if(i==17) {
                    if (currentAudit!!.imgProactivenessOfFireSafety != "null") {
                        Global.currentCapturedImage[i] = currentAudit!!.imgProactivenessOfFireSafety.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                //19
                if(i==18){
                    println("Inside the last index and the image is uploaded")
                    if (currentAudit!!.imgIsMandalInsured != "null") {
                        Global.currentCapturedImage[i] = currentAudit!!.imgIsMandalInsured.toUri()
                    }
                    else{
                        Global.currentCapturedImage[i] = null
                    }
                }
                if(i==19){
                    println("Inside the last index and the image is uploaded")
                }
            }

            currentAudit = audit
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
                    currentAudit!!.pointsElectricalCabling = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsElectricalCabling = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsElectricalCabling = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsElectricalCabling = "3"
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
                    value = currentAudit!!.pointsElectricalCabling,
                    onValueChange = {
                        currentAudit!!.pointsElectricalCabling = it
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
                model = currentAudit!!.imgElectricalCabling,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsElectricalTerminations = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsElectricalTerminations = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsElectricalTerminations = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsElectricalTerminations = "3"
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
                    value = currentAudit!!.pointsElectricalTerminations,
                    onValueChange = {
                        currentAudit!!.pointsElectricalTerminations = it
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
                model = currentAudit!!.imgElectricalTerminations,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsSwitchBoardInstallation = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsSwitchBoardInstallation = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsSwitchBoardInstallation = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsSwitchBoardInstallation = "3"
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
                    value = currentAudit!!.pointsSwitchBoardInstallation,
                    onValueChange = {
                        currentAudit!!.pointsSwitchBoardInstallation = it
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
                model = currentAudit!!.imgSwitchBoardInstallation,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsLocationOfFireExtinguishers = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsLocationOfFireExtinguishers = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsLocationOfFireExtinguishers = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsLocationOfFireExtinguishers = "3"
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
                    value =  currentAudit!!.pointsLocationOfFireExtinguishers,
                    onValueChange = {
                        currentAudit!!.pointsLocationOfFireExtinguishers = it
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
                model =  currentAudit!!.imgLocationOfFireExtinguishers,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsValidityOfFireExtinguishers = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsValidityOfFireExtinguishers = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsValidityOfFireExtinguishers = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsValidityOfFireExtinguishers = "3"
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
                    value = currentAudit!!.pointsValidityOfFireExtinguishers,
                    onValueChange = {
                        currentAudit!!.pointsValidityOfFireExtinguishers = it
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
                model = currentAudit!!.imgValidityOfFireExtinguishers,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsOtherFireSafetyArrangements = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsOtherFireSafetyArrangements = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsOtherFireSafetyArrangements = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsOtherFireSafetyArrangements = "3"
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
                    value = currentAudit!!.pointsOtherFireSafetyArrangements,
                    onValueChange = {
                        currentAudit!!.pointsOtherFireSafetyArrangements = it
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
                model = currentAudit!!.imgOtherFireSafetyArrangements,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsOperationOfMetalDetectors = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsOperationOfMetalDetectors = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsOperationOfMetalDetectors = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsOperationOfMetalDetectors = "3"
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
                    value = currentAudit!!.pointsOperationOfMetalDetectors,
                    onValueChange = {
                        currentAudit!!.pointsOperationOfMetalDetectors = it
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
                model = currentAudit!!.imgOperationOfMetalDetectors,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsOperationOfBombDetectors = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsOperationOfBombDetectors = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsOperationOfBombDetectors = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsOperationOfBombDetectors = "3"
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
                    value = currentAudit!!.pointsOperationOfBombDetectors,
                    onValueChange = {
                        currentAudit!!.pointsOperationOfBombDetectors = it
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
                model = currentAudit!!.imgOperationOfBombDetectors,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsSurveillanceCoverage = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsSurveillanceCoverage = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsSurveillanceCoverage = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsSurveillanceCoverage = "3"
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
                    value = currentAudit!!.pointsSurveillanceCoverage,
                    onValueChange = {
                        currentAudit!!.pointsSurveillanceCoverage = it
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
                model = currentAudit!!.imgSurveillanceCoverage,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsSurveillanceMonitoring = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsSurveillanceMonitoring = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsSurveillanceMonitoring = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsSurveillanceMonitoring = "3"
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
                    value = currentAudit!!.pointsSurveillanceMonitoring,
                    onValueChange = {
                        currentAudit!!.pointsSurveillanceMonitoring = it
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
                model = currentAudit!!.imgSurveillanceMonitoring,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsPublicAddressCoverage = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsPublicAddressCoverage = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsPublicAddressCoverage = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsPublicAddressCoverage = "3"
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
                    value = currentAudit!!.pointsPublicAddressCoverage,
                    onValueChange = {
                        currentAudit!!.pointsPublicAddressCoverage = it
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
                model = currentAudit!!.imgPublicAddressCoverage,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsAnnouncingTeam = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsAnnouncingTeam = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsAnnouncingTeam = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsAnnouncingTeam = "3"
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
                    value = currentAudit!!.pointsAnnouncingTeam,
                    onValueChange = {
                        currentAudit!!.pointsAnnouncingTeam = it
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
                model = currentAudit!!.imgAnnouncingTeam,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsLocationOfBanners = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsLocationOfBanners = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsLocationOfBanners = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsLocationOfBanners = "3"
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
                    value = currentAudit!!.pointsLocationOfBanners,
                    onValueChange = {
                        currentAudit!!.pointsLocationOfBanners = it
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
                model = currentAudit!!.imgLocationOfBanners,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsMedicalEmergencyMitigation = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsMedicalEmergencyMitigation = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsMedicalEmergencyMitigation = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsMedicalEmergencyMitigation = "3"
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
                    value = currentAudit!!.pointsMedicalEmergencyMitigation,
                    onValueChange = {
                        currentAudit!!.pointsMedicalEmergencyMitigation = it
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
                model = currentAudit!!.imgMedicalEmergencyMitigation,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )

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
                    currentAudit!!.pointsSecurityAndVolunteerTeam = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsSecurityAndVolunteerTeam = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsSecurityAndVolunteerTeam = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsSecurityAndVolunteerTeam = "3"
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
                    value = currentAudit!!.pointsSecurityAndVolunteerTeam,
                    onValueChange = {
                        currentAudit!!.pointsSecurityAndVolunteerTeam = it
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
                model = currentAudit!!.imgSecurityAndVolunteerTeam,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsCompetencyOfTeam = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsCompetencyOfTeam = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsCompetencyOfTeam = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsCompetencyOfTeam = "3"
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
                    value = currentAudit!!.pointsCompetencyOfTeam,
                    onValueChange = {
                        currentAudit!!.pointsCompetencyOfTeam = it
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
                model = currentAudit!!.imgCompetencyOfTeam,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsEmergencyEvacuationProcess = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsEmergencyEvacuationProcess = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsEmergencyEvacuationProcess = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsEmergencyEvacuationProcess = "3"
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
                    value = currentAudit!!.pointsEmergencyEvacuationProcess,
                    onValueChange = {
                        currentAudit!!.pointsEmergencyEvacuationProcess = it
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
                model = currentAudit!!.imgEmergencyEvacuationProcess,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsProactivenessOfFireSafety = "0"
                }
                "Average" -> {
                    currentAudit!!.pointsProactivenessOfFireSafety = "1"
                }
                "Good" -> {
                    currentAudit!!.pointsProactivenessOfFireSafety = "2"
                }
                "Excellent" -> {
                    currentAudit!!.pointsProactivenessOfFireSafety = "3"
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
                    value = currentAudit!!.pointsProactivenessOfFireSafety,
                    onValueChange = {
                        currentAudit!!.pointsProactivenessOfFireSafety = it
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
                model = currentAudit!!.imgProactivenessOfFireSafety,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
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
                    currentAudit!!.pointsIsMandalInsured = "0"
                }
                "Yes" -> {
                    currentAudit!!.pointsIsMandalInsured = "3"
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
                    value = currentAudit!!.pointsIsMandalInsured,
                    onValueChange = {
                        currentAudit!!.pointsIsMandalInsured = it
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
                model = currentAudit!!.imgIsMandalInsured,
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(220.dp)
            )
            var totalScoreText by remember{
                mutableStateOf(currentAudit!!.totalScoreText)
            }
            Text(
                "Total Points out of 57: ${totalScoreText}",
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
                    if(true
//                    !TextUtils.isEmpty(pointsElectricalCabling) &&
//                    !TextUtils.isEmpty(pointsElectricalTerminations)&&
//                    !TextUtils.isEmpty(pointsSwitchBoardInstallation)&&
//                    !TextUtils.isEmpty(pointsLocationOfFireExtinguishers)&&
//                    !TextUtils.isEmpty(pointsValidityOfFireExtinguishers)&&
//                    !TextUtils.isEmpty(pointsOtherFireSafetyArrangements)&&
//                    !TextUtils.isEmpty(pointsOperationOfMetalDetectors)&&
//                    !TextUtils.isEmpty(pointsOperationOfBombDetectors)&&
//                    !TextUtils.isEmpty(pointsSurveillanceCoverage)&&
//                    !TextUtils.isEmpty(pointsSurveillanceMonitoring)&&
//                    !TextUtils.isEmpty(pointsPublicAddressCoverage)&&
//                    !TextUtils.isEmpty(pointsAnnouncingTeam)&&
//                    !TextUtils.isEmpty(pointsLocationOfBanners)&&
//                    !TextUtils.isEmpty(pointsMedicalEmergencyMitigation)&&
//                    !TextUtils.isEmpty(pointsSecurityAndVolunteerTeam)&&
//                    !TextUtils.isEmpty(pointsCompetencyOfTeam)&&
//                    !TextUtils.isEmpty(pointsEmergencyEvacuationProcess)&&
//                    !TextUtils.isEmpty(pointsProactivenessOfFireSafety)&&
//                    !TextUtils.isEmpty(pointsIsMandalInsured)
                    ){
                        val totalScore = currentAudit!!.pointsElectricalCabling.toInt()+
                                currentAudit!!.pointsElectricalTerminations.toInt()+
                                currentAudit!!.pointsSwitchBoardInstallation.toInt()+
                                currentAudit!!.pointsLocationOfFireExtinguishers.toInt()+
                                currentAudit!!.pointsValidityOfFireExtinguishers.toInt()+
                                currentAudit!!.pointsOtherFireSafetyArrangements.toInt()+
                                currentAudit!!.pointsOperationOfMetalDetectors.toInt()+
                                currentAudit!!.pointsOperationOfBombDetectors.toInt()+
                                currentAudit!!.pointsSurveillanceCoverage.toInt()+
                                currentAudit!!.pointsSurveillanceMonitoring.toInt()+
                                currentAudit!!.pointsPublicAddressCoverage.toInt()+
                                currentAudit!!.pointsAnnouncingTeam.toInt()+
                                currentAudit!!.pointsLocationOfBanners.toInt()+
                                currentAudit!!.pointsMedicalEmergencyMitigation.toInt()+
                                currentAudit!!.pointsSecurityAndVolunteerTeam.toInt()+
                                currentAudit!!.pointsCompetencyOfTeam.toInt()+
                                currentAudit!!.pointsEmergencyEvacuationProcess.toInt()+
                                currentAudit!!.pointsProactivenessOfFireSafety.toInt()+
                                currentAudit!!.pointsIsMandalInsured.toInt()
                        totalScoreText = totalScore.toString()
                        currentAudit!!.totalScoreText = totalScoreText
                    }
                    else{
                        Toast.makeText(context, "Some Fields are Missing", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Calculate Score")
            }
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    if (true
//                    !TextUtils.isEmpty(pointsElectricalCabling) &&
//                    !TextUtils.isEmpty(pointsElectricalTerminations) &&
//                    !TextUtils.isEmpty(pointsSwitchBoardInstallation) &&
//                    !TextUtils.isEmpty(pointsLocationOfFireExtinguishers) &&
//                    !TextUtils.isEmpty(pointsValidityOfFireExtinguishers) &&
//                    !TextUtils.isEmpty(pointsOtherFireSafetyArrangements) &&
//                    !TextUtils.isEmpty(pointsOperationOfMetalDetectors) &&
//                    !TextUtils.isEmpty(pointsOperationOfBombDetectors) &&
//                    !TextUtils.isEmpty(pointsSurveillanceCoverage) &&
//                    !TextUtils.isEmpty(pointsSurveillanceMonitoring) &&
//                    !TextUtils.isEmpty(pointsPublicAddressCoverage) &&
//                    !TextUtils.isEmpty(pointsAnnouncingTeam) &&
//                    !TextUtils.isEmpty(pointsLocationOfBanners) &&
//                    !TextUtils.isEmpty(pointsMedicalEmergencyMitigation) &&
//                    !TextUtils.isEmpty(pointsSecurityAndVolunteerTeam) &&
//                    !TextUtils.isEmpty(pointsCompetencyOfTeam) &&
//                    !TextUtils.isEmpty(pointsEmergencyEvacuationProcess) &&
//                    !TextUtils.isEmpty(pointsProactivenessOfFireSafety) &&
//                    !TextUtils.isEmpty(pointsIsMandalInsured)
                    ) {

                        val totalScore = currentAudit!!.pointsElectricalCabling.toInt()+
                                currentAudit!!.pointsElectricalTerminations.toInt()+
                                currentAudit!!.pointsSwitchBoardInstallation.toInt()+
                                currentAudit!!.pointsLocationOfFireExtinguishers.toInt()+
                                currentAudit!!.pointsValidityOfFireExtinguishers.toInt()+
                                currentAudit!!.pointsOtherFireSafetyArrangements.toInt()+
                                currentAudit!!.pointsOperationOfMetalDetectors.toInt()+
                                currentAudit!!.pointsOperationOfBombDetectors.toInt()+
                                currentAudit!!.pointsSurveillanceCoverage.toInt()+
                                currentAudit!!.pointsSurveillanceMonitoring.toInt()+
                                currentAudit!!.pointsPublicAddressCoverage.toInt()+
                                currentAudit!!.pointsAnnouncingTeam.toInt()+
                                currentAudit!!.pointsLocationOfBanners.toInt()+
                                currentAudit!!.pointsMedicalEmergencyMitigation.toInt()+
                                currentAudit!!.pointsSecurityAndVolunteerTeam.toInt()+
                                currentAudit!!.pointsCompetencyOfTeam.toInt()+
                                currentAudit!!.pointsEmergencyEvacuationProcess.toInt()+
                                currentAudit!!.pointsProactivenessOfFireSafety.toInt()+
                                currentAudit!!.pointsIsMandalInsured.toInt()
                        totalScoreText = totalScore.toString()
                        for(image in Global.currentCapturedImage){
                            println("Image:"+image.toString())
                        }
                        for(i in 0..Global.currentCapturedImage.lastIndex){
                            println("At Index: "+(i+1))
                        }
                        println("Size: "+Global.currentCapturedImage.size)
                        coroutineScope.launch {
                            progressState = true
                            val hashNewAudit = uploadImagesAndSetHashmap(storage, coroutineScope, currentAudit!!)
                            hashNewAudit["pointsElectricalCabling"] = currentAudit!!.pointsElectricalCabling
                            hashNewAudit["pointsElectricalTerminations"] = currentAudit!!.pointsElectricalTerminations
                            hashNewAudit["pointsSwitchBoardInstallation"] = currentAudit!!.pointsSwitchBoardInstallation
                            hashNewAudit["pointsLocationOfFireExtinguishers"] = currentAudit!!.pointsLocationOfFireExtinguishers
                            hashNewAudit["pointsValidityOfFireExtinguishers"] = currentAudit!!.pointsValidityOfFireExtinguishers
                            hashNewAudit["pointsOtherFireSafetyArrangements"] = currentAudit!!.pointsOtherFireSafetyArrangements
                            hashNewAudit["pointsOperationOfMetalDetectors"] = currentAudit!!.pointsOperationOfMetalDetectors
                            hashNewAudit["pointsOperationOfBombDetectors"] = currentAudit!!.pointsOperationOfBombDetectors
                            hashNewAudit["pointsSurveillanceCoverage"] =  currentAudit!!.pointsSurveillanceCoverage
                            hashNewAudit["pointsSurveillanceMonitoring"] = currentAudit!!.pointsSurveillanceMonitoring
                            hashNewAudit["pointsPublicAddressCoverage"] = currentAudit!!.pointsPublicAddressCoverage
                            hashNewAudit["pointsAnnouncingTeam"] = currentAudit!!.pointsAnnouncingTeam
                            hashNewAudit["pointsLocationOfBanners"] = currentAudit!!.pointsLocationOfBanners
                            hashNewAudit["pointsMedicalEmergencyMitigation"] = currentAudit!!.pointsMedicalEmergencyMitigation
                            hashNewAudit["pointsSecurityAndVolunteerTeam"] = currentAudit!!.pointsSecurityAndVolunteerTeam
                            hashNewAudit["pointsCompetencyOfTeam"] = currentAudit!!.pointsCompetencyOfTeam
                            hashNewAudit["pointsEmergencyEvacuationProcess"] = currentAudit!!.pointsEmergencyEvacuationProcess
                            hashNewAudit["pointsProactivenessOfFireSafety"] = currentAudit!!.pointsProactivenessOfFireSafety
                            hashNewAudit["pointsIsMandalInsured"] = currentAudit!!.pointsIsMandalInsured
                            hashNewAudit["totalScoreOfAudit"] = currentAudit!!.totalScoreText.toString()
                            println("Full Hash: "+hashNewAudit)
                            ////
                            db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId).collection("audits").document(Global.adminId!!)
                                .set(hashNewAudit)
                                .addOnSuccessListener {
                                    val hash = HashMap<String, Any>()
                                    hash["secondAuditStatus"] = true
                                    db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId)
                                        .collection("audits").get()
                                        .addOnSuccessListener { audits ->
                                            if (audits.isEmpty) {
                                                hash["finalAuditScore"] = totalScoreText
                                            } else {
                                                var total = totalScore
                                                for (au in audits) {
                                                    if(au.id == Global.adminId){
                                                        continue
                                                    }
                                                    total += au["totalScoreOfAudit"].toString()
                                                        .toInt()
                                                }
                                                hash["finalAuditScore"] = total.toString()
                                            }

                                            db.collection("mandals").document(Global.selectedMandal!!.mandalId)
                                                .update(hash)
                                                .addOnSuccessListener {
                                                    db.collection("mandalsSelectedForNext").document(Global.selectedMandal!!.mandalId)
                                                        .update(hash)
                                                        .addOnSuccessListener {
                                                            Toast.makeText(context, "Audit Updated Successfully", Toast.LENGTH_SHORT).show()
                                                            progressState = false
                                                            val intent = Intent(context, AdminOptions::class.java)
                                                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                                            context.startActivity(intent)
//                                                val intent = Intent(context,MandalFinalListAfter2ndAudit::class.java)
////                                                Global.currentCapturedImage = SnapshotStateList()
//                                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                                                context.startActivity(intent)
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
                        Toast.makeText(context, "Some Field is Missing", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Update Audit Points", textAlign = TextAlign.Center)
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

suspend private fun uploadImagesAndSetHashmap(
    storage: FirebaseStorage,
    coroutineScope: CoroutineScope,
    currentAudit: MandalFinalAudit
): HashMap<String,String> {
    val hashNewAudit = HashMap<String, String>()
    for (i in 0 until Global.currentCapturedImage.size) {
        if(!Global.currentCapturedImage[i].toString().contains("http")){
            if (Global.currentCapturedImage[i] != null && Global.currentCapturedImage[i].toString() != "null") {
                val reference = storage.reference.child("images/${Global.currentCapturedImage[i]}")
                reference.putFile(Global.currentCapturedImage[i]!!)
                    .addOnSuccessListener {
                        coroutineScope.launch {
                            reference.downloadUrl.addOnSuccessListener {
                                val downloadUri = it
                                if (i == 0) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgElectricalCabling"] = downloadUri.toString()
                                    }
                                }
                                //2
                                if (i == 1) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgElectricalTerminations"] =
                                            downloadUri.toString()
                                    }
                                }
                                //3
                                if (i == 2) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgSwitchBoardInstallation"] =
                                            downloadUri.toString()
                                    }
                                }
                                //4
                                if (i == 3) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgLocationOfFireExtinguishers"] =
                                            downloadUri.toString()
                                    }
                                }
                                //5
                                if (i == 4) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgValidityOfFireExtinguishers"] =
                                            downloadUri.toString()
                                    }
                                }
                                //6
                                if (i == 5) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgOtherFireSafetyArrangements"] =
                                            downloadUri.toString()
                                    }
                                }
                                //7
                                if (i == 6) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgOperationOfMetalDetectors"] =
                                            downloadUri.toString()
                                    }
                                }
                                //8
                                if (i == 7) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgOperationOfBombDetectors"] =
                                            downloadUri.toString()
                                    }
                                }
                                //9
                                if (i == 8) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgSurveillanceCoverage"] = downloadUri.toString()
                                    }
                                }
                                //10
                                if (i == 9) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgSurveillanceMonitoring"] =
                                            downloadUri.toString()
                                    }
                                }
                                //11
                                if (i == 10) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgPublicAddressCoverage"] =
                                            downloadUri.toString()
                                    }
                                }
                                //continue from here
                                //12
                                if (i == 11) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgAnnouncingTeam"] = downloadUri.toString()
                                    }
                                }
                                //13
                                if (i == 12) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgLocationOfBanners"] = downloadUri.toString()
                                    }
                                }
                                //14
                                if (i == 13) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgMedicalEmergencyMitigation"] =
                                            downloadUri.toString()
                                    }
                                }
                                //15
                                if (i == 14) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgSecurityAndVolunteerTeam"] =
                                            downloadUri.toString()
                                    }
                                }
                                //16
                                if (i == 15) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgCompetencyOfTeam"] = downloadUri.toString()
                                    }
                                }
                                //17
                                if (i == 16) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgEmergencyEvacuationProcess"] =
                                            downloadUri.toString()
                                    }
                                }
                                //18
                                if (i == 17) {
                                    if (Global.currentCapturedImage[i] != null) {
                                        hashNewAudit["imgProactivenessOfFireSafety"] =
                                            downloadUri.toString()
                                    }
                                }
                                //19
                                if (i == 18) {
                                    println("Inside the last index and the image is uploaded")
                                    hashNewAudit["imgIsMandalInsured"] = downloadUri.toString()
                                }
                                if (i == 19) {
                                    println("Inside the last index and the image is uploaded")
                                }
                            }.await()
                        }
                    }.await()
            } else if(Global.currentCapturedImage[i] == null || Global.currentCapturedImage[i].toString() == "null"){
                if (i == 0) {
                    hashNewAudit["imgElectricalCabling"] = "null"
                }
                //2
                if (i == 1) {
                    hashNewAudit["imgElectricalTerminations"] = "null"
                }
                //3
                if (i == 2) {
                    hashNewAudit["imgSwitchBoardInstallation"] = "null"
                }
                //4
                if (i == 3) {

                    hashNewAudit["imgLocationOfFireExtinguishers"] = "null"
                }
                //5
                if (i == 4) {


                    hashNewAudit["imgValidityOfFireExtinguishers"] = "null"
                }
                //6
                if (i == 5) {

                    hashNewAudit["imgOtherFireSafetyArrangements"] = "null"
                }
                //7
                if (i == 6) {

                    hashNewAudit["imgOperationOfMetalDetectors"] = "null"
                }
                //8
                if (i == 7) {

                    hashNewAudit["imgOperationOfBombDetectors"] = "null"
                }
                //9
                if (i == 8) {
                    hashNewAudit["imgSurveillanceCoverage"] = "null"
                }
                //10
                if (i == 9) {
                    hashNewAudit["imgSurveillanceMonitoring"] = "null"
                }
                //11
                if (i == 10) {

                    hashNewAudit["imgPublicAddressCoverage"] = "null"
                }
                //continue from here
                //12
                if (i == 11) {

                    hashNewAudit["imgAnnouncingTeam"] = "null"
                }
                //13
                if (i == 12) {

                    hashNewAudit["imgLocationOfBanners"] = "null"
                }
                //14
                if (i == 13) {

                    hashNewAudit["imgMedicalEmergencyMitigation"] = "null"
                }
                //15
                if (i == 14) {

                    hashNewAudit["imgSecurityAndVolunteerTeam"] = "null"
                }
                //16
                if (i == 15) {

                    hashNewAudit["imgCompetencyOfTeam"] = "null"
                }
                //17
                if (i == 16) {

                    hashNewAudit["imgEmergencyEvacuationProcess"] = "null"
                }
                //18
                if (i == 17) {
                    hashNewAudit["imgProactivenessOfFireSafety"] = "null"
                }
                //19
                if (i == 18) {
                    hashNewAudit["imgIsMandalInsured"] = "null"
                }
            }
        }
        if(Global.currentCapturedImage[i].toString().contains("http") || Global.currentCapturedImage[i].toString() != "null"
            ){
            if (i == 0) {
                hashNewAudit["imgElectricalCabling"] = Global.currentCapturedImage[i].toString()
            }
            //2
            if (i == 1) {
                hashNewAudit["imgElectricalTerminations"] = Global.currentCapturedImage[i].toString()
            }
            //3
            if (i == 2) {
                hashNewAudit["imgSwitchBoardInstallation"] = Global.currentCapturedImage[i].toString()
            }
            //4
            if (i == 3) {

                hashNewAudit["imgLocationOfFireExtinguishers"] = Global.currentCapturedImage[i].toString()
            }
            //5
            if (i == 4) {
                hashNewAudit["imgValidityOfFireExtinguishers"] = Global.currentCapturedImage[i].toString()
            }
            //6
            if (i == 5) {

                hashNewAudit["imgOtherFireSafetyArrangements"] = Global.currentCapturedImage[i].toString()
            }
            //7
            if (i == 6) {

                hashNewAudit["imgOperationOfMetalDetectors"] = Global.currentCapturedImage[i].toString()
            }
            //8
            if (i == 7) {

                hashNewAudit["imgOperationOfBombDetectors"] = Global.currentCapturedImage[i].toString()
            }
            //9
            if (i == 8) {
                hashNewAudit["imgSurveillanceCoverage"] = Global.currentCapturedImage[i].toString()
            }
            //10
            if (i == 9) {
                hashNewAudit["imgSurveillanceMonitoring"] = Global.currentCapturedImage[i].toString()
            }
            //11
            if (i == 10) {

                hashNewAudit["imgPublicAddressCoverage"] = Global.currentCapturedImage[i].toString()
            }
            //continue from here
            //12
            if (i == 11) {

                hashNewAudit["imgAnnouncingTeam"] = Global.currentCapturedImage[i].toString()
            }
            //13
            if (i == 12) {

                hashNewAudit["imgLocationOfBanners"] = Global.currentCapturedImage[i].toString()
            }
            //14
            if (i == 13) {

                hashNewAudit["imgMedicalEmergencyMitigation"] = Global.currentCapturedImage[i].toString()
            }
            //15
            if (i == 14) {

                hashNewAudit["imgSecurityAndVolunteerTeam"] = Global.currentCapturedImage[i].toString()
            }
            //16
            if (i == 15) {

                hashNewAudit["imgCompetencyOfTeam"] = Global.currentCapturedImage[i].toString()
            }
            //17
            if (i == 16) {

                hashNewAudit["imgEmergencyEvacuationProcess"] = Global.currentCapturedImage[i].toString()
            }
            //18
            if (i == 17) {
                hashNewAudit["imgProactivenessOfFireSafety"] = Global.currentCapturedImage[i].toString()
            }
            //19
            if (i == 18) {
                hashNewAudit["imgIsMandalInsured"] = Global.currentCapturedImage[i].toString()
            }
        }
    }
    return hashNewAudit
}