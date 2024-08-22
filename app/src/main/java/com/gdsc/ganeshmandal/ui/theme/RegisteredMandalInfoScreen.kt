package com.gdsc.ganeshmandal.ui.theme

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdsc.ganeshmandal.AdminLoginPage
import com.gdsc.ganeshmandal.Global
import com.gdsc.ganeshmandal.Mandal
import com.gdsc.ganeshmandal.MandalFinalAuditForm
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisteredMandalInfoScreen(mandal: Mandal, firstAuditStatus: Boolean, secondAuditStatus: Boolean){
    var status = remember {
        mutableStateOf<Boolean>(true)
    }
    LaunchedEffect(Unit){
        val db = FirebaseFirestore.getInstance()
        checkAuditStatus(db, mandal.mandalId, status)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        Text(
            text = "Technical Evaluation Form",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = mandal.nameOfMandal,
            onValueChange = {
            },
            placeholder = {
                Text(text = "Name of Mandal")
            },
            label = {
                Text(text = "Name of Mandal")
            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.addOfMandal,
            onValueChange = {
//                addOfMandal = it
            },
            placeholder = {
                Text(text = "Address of Mandal")
            },
            enabled = false,
            label = {
                Text(text = "Address of Mandal")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.contactPerson,
            onValueChange = {
//                contactPerson = it
            },
            placeholder = {
                Text(text = "Contact Person")
            },
            enabled = false,
            label = {
                Text(text = "Contact Person")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.email,
            onValueChange = {
//                contactPerson = it
            },
            placeholder = {
                Text(text = "Mandal Representative Email")
            },
            enabled = false,
            label = {
                Text(text = "Mandal Representative Email")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.mobileNo,
            onValueChange = {
//                if (it.text.length <= 10) mobileNo = it
            },
            placeholder = {
                Text(text = "Mobile No.")
            },
            label = {
                Text(text = "Mobile No.")
            },
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = mandal.totalAreaCovered,
                onValueChange = {
//                    totalAreaCovered = it
                },
                enabled = false,
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
        ) {
            OutlinedTextField(
                value = mandal.totalAreaOpen,
                onValueChange = {
//                    totalAreaOpen = it
                },
                placeholder = {
                    Text(text = "Total Area(Open)")
                },
                enabled = false,
                label = {
                    Text(text = "Total Area(Open)")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            Text("  Sq. ft.")
        }

        Column {
            Text(text = "Date of Technical evaluation:")
            Text(text = mandal.dateOfTechnicalEvaluation)
        }

        Text("A. Electrical Safety", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = mandal.powerConsumedByMandal,
            onValueChange = {
//                powerConsumedByMandal = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Power consumed by Mandal")
            },
            label = {
                Text(text = "Power consumed by Mandal")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.sizeOfCableInstalled,
            onValueChange = {
//                sizeOfCableInstalled = it
            },
            placeholder = {
                Text(text = "Size of Cable Installed")
            },
            enabled = false,
            label = {
                Text(text = "Size of Cable Installed")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.typeOfCable,
            onValueChange = {
//                typeOfCable = it
            },
            placeholder = {
                Text(text = "Type of Cable ISI / FRLS")
            },
            label = {
                Text(text = "Type of Cable ISI / FRLSd")
            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.mcb_mccbInstalled,
            onValueChange = {
//                mcb_mccbInstalled = it
            },
            placeholder = {
                Text(text = "MCB / MCCB installed")
            },
            label = {
                Text(text = "MCB / MCCB installed")
            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "24x7 Electrician Yes/No",
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {
//                    expanded = !expanded
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = mandal._24ElectricianYesOrNo,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier.menuAnchor()
                )
            }
        }
        Text(
            text = "Proper Termination of Cables Yes/No",
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {
//                    expanded1 = !expanded1
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = mandal.properTerminationOfCables,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier.menuAnchor()
                )
            }
        }
        Text(
            text = "B. Fire Extinguisher",
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.fireExtin1,
                onValueChange = {
//                    fireExtin1 = it
                },
                placeholder = {
                    Text(text = "Fire Extinguisher")
                },
                label = {
                    Text(text = "Fire Extinguisher")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.fireExt1Quant,
                onValueChange = {
//                    fireExt1Quant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.fireExtin2,
                onValueChange = {
//                    fireExtin2 = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Fire Extinguisher")
                },
                label = {
                    Text(text = "Fire Extinguisher")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.fireExt2Quant,
                onValueChange = {
//                    fireExt2Quant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        // C. Fire Detectors
        Text(
            text = "C. Fire Detector",
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.fireDetect1,
                onValueChange = {
//                    mandal.fireDetect1 = it
                },
                placeholder = {
                    Text(text = "Fire Detector")
                },
                label = {
                    Text(text = "Fire Detector")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.fireDetect1Quant,
                onValueChange = {
//                    fireDetect1Quant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.fireDetect2,
                onValueChange = {
//                    fireDetect2 = it
                },
                placeholder = {
                    Text(text = "Fire Detector")
                },
                enabled = false,
                label = {
                    Text(text = "Fire Detector")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.fireDetect2Quant,
                onValueChange = {
//                    fireDetect2Quant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }

        //D. CCTV System
        Text(
            text = "D. CCTV System",
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfCamera1,
                onValueChange = {
//                    typeOfCamera1 = it
                },
                placeholder = {
                    Text(text = "Type of Camera")
                },
                label = {
                    Text(text = "Type of Camera")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfCamera1Qty,
                onValueChange = {
//                    typeOfCamera1Qty = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfCamera2,
                onValueChange = {
//                    typeOfCamera2 = it
                },
                placeholder = {
                    Text(text = "Type Of Camera")
                },
                label = {
                    Text(text = "Type of Camera")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfCamera2Qty,
                onValueChange = {
//                    typeOfCamera2Qty = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                enabled = false,
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            OutlinedTextField(
                value = mandal.dvr,
                onValueChange = {
//                    dvr = it
                },
                placeholder = {
                    Text(text = "DVR")
                },
                enabled = false,
                label = {
                    Text(text = "DVR")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.channels,
                onValueChange = {
//                    channels = it
                },
                placeholder = {
                    Text(text = "Channels")
                },
                label = {
                    Text(text = "Channels")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1.5f)
            )
            OutlinedTextField(
                value = mandal.dvrQuantity,
                onValueChange = {
//                    dvrQuantity = it
                },
                placeholder = {
                    Text(text = "Qunt")
                },
                label = {
                    Text(text = "Qunt")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        OutlinedTextField(
            value = mandal.storageDetails,
            onValueChange = {
//                storageDetails = it
            },
            placeholder = {
                Text(text = "Storage Details")
            },
            label = {
                Text(text = "Storage Details")
            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.monitoringMethodology,
            onValueChange = {
//                monitoringMethodology = it
            },
            placeholder = {
                Text(text = "Monitoring Methodology")
            },
            label = {
                Text(text = "Monitoring Methodology")
            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
        )

        // E. Public Address system
        Text(
            text = "E. Public Address System",
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfSpeaker1,
                onValueChange = {
//                    typeOfSpeaker1 = it
                },
                placeholder = {
                    Text(text = "Type of Speaker")
                },
                label = {
                    Text(text = "Type of Speaker")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfSpeaker1Quant,
                onValueChange = {
//                    typeOfSpeaker1Quant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfSpeaker2,
                onValueChange = {
//                    typeOfSpeaker2 = it
                },
                placeholder = {
                    Text(text = "Type of Speaker")
                },
                label = {
                    Text(text = "Type of Speaker")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfSpeaker2Quant,
                onValueChange = {
//                    typeOfSpeaker2Quant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.announcingTeam,
                onValueChange = {
//                    announcingTeam = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Announcing Team")
                },
                label = {
                    Text(text = "Announcing Team")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.announcingTeamQuant,
                onValueChange = {
//                    announcingTeamQuant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        //F. Metal and Bomb Detector
        Text(
            text = "F. Metal and Bomb Detector",
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfDetector1,
                onValueChange = {
//                    typeOfDetector1 = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Type of Detector")
                },
                label = {
                    Text(text = "Type of Detector")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfDetector1Quant,
                onValueChange = {
//                    typeOfDetector1Quant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfDetector2,
                onValueChange = {
//                    typeOfDetector2 = it
                },
                placeholder = {
                    Text(text = "Type of Detector")
                },
                label = {
                    Text(text = "Type of Detector")
                },
                enabled = false,
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfDetector2Quant,
                onValueChange = {
//                    typeOfDetector2Quant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        //G. Banners and First Aid Kit
        Text(
            text = "G. Banners and FirstAid Kit",
            fontWeight= FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfSign1,
                onValueChange = {
//                    typeOfSign1 = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Type of Sign Boards")
                },
                label = {
                    Text(text = "Type of Sign Boards")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfSign1Quant,
                onValueChange = {
//                    typeOfSign1Quant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.typeOfSign2,
                onValueChange = {
//                    typeOfSign2= it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Type of Sign Boards")
                },
                label = {
                    Text(text = "Type of Sign Boards")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.typeOfSign2Quant,
                onValueChange = {
//                    typeOfSign2Quant = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            OutlinedTextField(
                value = mandal.firstAidKit,
                onValueChange = {
//                    firstAidKit = it
                },
                enabled = false,
                placeholder = {
                    Text(text = "FirstAid Kit")
                },
                label = {
                    Text(text = "FirstAid Kit")
                },
                modifier = Modifier
                    .weight(2f)
            )
            OutlinedTextField(
                value = mandal.firstAidKitQuant,
                onValueChange = {
//                    firstAidKitQuant = it
                },
                placeholder = {
                    Text(text = "Quantity")
                },
                label = {
                    Text(text = "Quantity")
                },
                enabled = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
        //H. Other Measures Undertaken
        Text(
            text = "H. Other Measures Undertaken",
            fontWeight= FontWeight.Bold
        )
        OutlinedTextField(
            value = mandal.emergencyEvacuationPlanDetails,
            onValueChange = {
//                emergencyEvacuationPlanDetails = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Emergency evacuation plan details")
            },
            label = {
                Text(text = "Emergency evacuation plan details")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.securityTeamDetails,
            onValueChange = {
//                securityTeamDetails = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Security Team Details")
            },
            label = {
                Text(text = "Security Team Details")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.trainingSecurityTeam,
            onValueChange = {
//                trainingSecurityTeam = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Training to Security Team")
            },
            label = {
                Text(text = "Training to Security Team")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        // I. Remarks
        Text(
            text = "I. Remarks",
            fontWeight= FontWeight.Bold
        )
        OutlinedTextField(
            value = mandal.remarks ,
            onValueChange = {
//                remarks = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Remarks")
            },
            label = {
                Text(text = "Remarks")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.nameOfFSAIRepresentative ,
            onValueChange = {
//                mandal.nameOfFSAIRepresentative = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Name Of FSAI Representative")
            },
            label = {
                Text(text = "Name Of FSAI Representative")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = mandal.nameOfMandalRepresentative ,
            onValueChange = {
//                nameOfMandalRepresentative = it
            },
            enabled = false,
            placeholder = {
                Text(text = "Name Of Mandal Representative")
            },
            label = {
                Text(text = "Name Of Mandal Representative")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        val context = LocalContext.current
        AsyncImage(
            model = mandal.imageOfMandal,
            contentDescription = "Image of Mandal",
            modifier = Modifier
                .size(220.dp)
        )
        Button(onClick = {
            val intentUri = Uri.parse("google.navigation:q=${mandal.latitude},${mandal.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, intentUri)
            intent.`package` = "com.google.android.apps.maps"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }) {
            Column {
                Text(text = "Latitude:"+mandal.latitude)
                Text(text = "Longitude"+mandal.latitude)
            }
        }
        AnimatedVisibility(visible = firstAuditStatus) {
            Button(onClick ={
                if(mandal.firstAuditStatus == "true"){

                }
                else{
                    val intent = Intent(context, AdminLoginPage::class.java)
                    context.startActivity(intent)
                }
            }) {
                Text(
                    text = "First Audit: "+mandal.firstAuditStatus,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        AnimatedVisibility(visible = mandal.totalScore.isNotEmpty() || mandal.totalScore.isNotBlank() || mandal.firstAuditStatus == "true") {
            Text(
                text = "First Audit Score: "+mandal.totalScore,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        AnimatedVisibility(visible = secondAuditStatus) {
            Button(onClick ={
                if(status.value){
                    Toast.makeText(context, "You have already submitted the audit!", Toast.LENGTH_SHORT).show()
                }
                else{
                    val intent = Intent(context, MandalFinalAuditForm::class.java)
                    context.startActivity(intent)
                }
            }) {
                Text(
                    text = "Second Audit Status: ${status.value}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

suspend fun checkAuditStatus(db: FirebaseFirestore, mandalId: String, status: MutableState<Boolean>){
    status.value = false
    db.collection("mandalsSelectedForNext")
        .document(mandalId)
        .collection("audits")
        .document(Global.adminId.toString())
        .get()
        .addOnSuccessListener {
            if(it.exists()){
                status.value = true
                println("Found:")
            }
        }
        .await()
}