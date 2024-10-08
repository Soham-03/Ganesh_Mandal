package com.gdsc.ganeshmandal

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.model.Mandal
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.gdsc.ganeshmandal.ui.theme.MandalSingleRow
import com.google.firebase.firestore.FirebaseFirestore

class AllRegisteredMandalsList : ComponentActivity() {
    private val uriImageState = mutableStateOf<Uri?>(null)
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                uriImageState.value = uri
                Toast.makeText(
                    this@AllRegisteredMandalsList, uriImageState.toString(), Toast.LENGTH_SHORT
                ).show()
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var listOfMandals by remember {
                        mutableStateOf(ArrayList<Mandal>())
                    }
                    val db = FirebaseFirestore.getInstance()
                    val listOfMandal = ArrayList<Mandal>()

//                    listOfMandals = listOfMandal
                    //counting the number of mandal

                    var numberofMandal by remember {
                        mutableStateOf(0)
                    }

                    var textFieldValue by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    db.collection("mandals").get().addOnSuccessListener { docs ->
                            numberofMandal = docs.size()
                            for (doc in docs) {

                                listOfMandal.add(
                                    Mandal(
                                        mandalId = doc.id.toString(),
                                        nameOfMandal = doc["nameOfMandal"].toString(),
                                        addOfMandal = doc["addOfMandal"].toString(),
                                        contactPerson = doc["contactPerson"].toString(),
                                        mobileNo = doc["mobileNo"].toString(),
                                        totalAreaCovered = doc["totalAreaCovered"].toString(),
                                        totalAreaOpen = doc["totalAreaOpen"].toString(),
                                        dateOfTechnicalEvaluation = doc["dateOfTechnicalEvaluation"].toString(),
                                        powerConsumedByMandal = doc["powerConsumedByMandal"].toString(),
                                        sizeOfCableInstalled = doc["sizeOfCableInstalled"].toString(),
                                        typeOfCable = doc["typeOfCable"].toString(),
                                        mcb_mccbInstalled = doc["mcb_mccbInstalled"].toString(),
                                        _24ElectricianYesOrNo = doc["_24ElectricianYesOrNo"].toString(),
                                        earthingWireQuant = doc["earthingWireQuant"].toString(),
                                        backupPowerSupplyYesOrNo = doc["backupPowerSupplyYesOrNo"].toString(),
                                        sourceOfBackupPowerSupply = doc["sourceOfBackupPowerSupply"].toString(),
                                        properTerminationOfCables = doc["properTerminationOfCables"].toString(),
                                        fireExtin1 = doc["fireExtin1"].toString(),
                                        fireExt1Quant = doc["fireExt1Quant"].toString(),
                                        fireExtin2 = doc["fireExtin2"].toString(),
                                        fireExt2Quant = doc["fireExt2Quant"].toString(),
                                        fireDetect1 = doc["fireDetect1"].toString(),
                                        fireDetect1Quant = doc["fireDetect1Quant"].toString(),
                                        fireDetect2 = doc["fireDetect2"].toString(),
                                        fireDetect2Quant = doc["fireDetect2Quant"].toString(),
                                        typeOfCamera1 = doc["typeOfCamera1"].toString(),
                                        typeOfCamera1Qty = doc["typeOfCamera1Qty"].toString(),
                                        typeOfCamera2 = doc["typeOfCamera2"].toString(),
                                        typeOfCamera2Qty = doc["typeOfCamera2Qty"].toString(),
                                        dvr = doc["dvr"].toString(),
                                        channels = doc["channels"].toString(),
                                        dvrQuantity = doc["dvrQuantity"].toString(),
                                        storageDetails = doc["storageDetails"].toString(),
                                        monitoringMethodology = doc["monitoringMethodology"].toString(),
                                        typeOfSpeaker1 = doc["typeOfSpeaker1"].toString(),
                                        typeOfSpeaker1Quant = doc["typeOfSpeaker1Quant"].toString(),
                                        typeOfSpeaker2 = doc["typeOfSpeaker2"].toString(),
                                        typeOfSpeaker2Quant = doc["typeOfSpeaker2Quant"].toString(),
                                        announcingTeam = doc["announcingTeam"].toString(),
                                        announcingTeamQuant = doc["announcingTeamQuant"].toString(),
                                        typeOfDetector1 = doc["typeOfDetector1"].toString(),
                                        typeOfDetector1Quant = doc["typeOfDetector1Quant"].toString(),
                                        typeOfSign1 = doc["typeOfSign1"].toString(),
                                        typeOfSign1Quant = doc["typeOfSign1Quant"].toString(),
                                        typeOfSign2 = doc["typeOfSign2"].toString(),
                                        typeOfSign2Quant = doc["typeOfSign2Quant"].toString(),
                                        firstAidKit = doc["firstAidKit"].toString(),
                                        firstAidKitQuant = doc["firstAidKitQuant"].toString(),
                                        emergencyEvacuationPlanDetails = doc["emergencyEvacuationPlanDetails"].toString(),
                                        securityTeamDetails = doc["securityTeamDetails"].toString(),
                                        trainingSecurityTeam = doc["trainingSecurityTeam"].toString(),
                                        remarks = doc["remarks"].toString(),
                                        nameOfFSAIRepresentative = doc["nameOfFSAIRepresentative"].toString(),
                                        nameOfMandalRepresentative = doc["nameOfMandalRepresentative"].toString(),
                                        firstAuditStatus = doc["firstAuditStatus"].toString(),
                                        secondAuditStatus = doc["secondAuditStatus"].toString(),
                                        MCBInstalledYesorNo = doc["MCBInstalledYesorNo:"].toString(),
                                        FireExtinguishersYesorNo = doc["FireExtinguishersYesorNo"].toString(),
                                        FireDetectorsYesorNo = doc["FireDetectorsYesorNo"].toString(),
                                        CCTVCameraYesorNo = doc["CCTVCameraYesorNo"].toString(),
                                        PublicAddressYesorNo = doc["PublicAddressYesorNo"].toString(),
                                        MetalAndBombYesorNo = doc["MetalAndBombYesorNo"].toString(),
                                        FirstAidYesorNo = doc["FirstAidYesorNo"].toString(),
                                        EmergencyTeamYesorNo = doc["EmergencyTeamYesorNo"].toString(),
                                        totalScore = doc["totalScore"].toString(),
                                        nameOf1stAuditor = doc["nameOf1stAuditor"].toString(),
                                        latitude = doc["latitude"].toString(),
                                        longitude = doc["longitude"].toString(),
                                        imageOfMandal = doc["imagMandal"].toString(),
                                        finalAuditScore = doc["finalAuditScore"].toString(),
                                        email = doc["mandalRepresentativeEmail"].toString()
                                    )
                                )
                            }
                            println("List" + listOfMandal)
                            if (TextUtils.isEmpty(textFieldValue.text)) {
                                listOfMandals = listOfMandal
                            }

                        }
                    Column() {
                        OutlinedTextField(value = textFieldValue,
                            onValueChange = {
                                textFieldValue = it
                                listOfMandals = if (!TextUtils.isEmpty(textFieldValue.text)) {
                                    val list = search(it, listOfMandal)
                                    list
                                } else {
                                    listOfMandal
                                }
                            },
                            label = {
                                Text(text = "Serach Mandal")
                            },
                            trailingIcon = {
                                Image(imageVector = Icons.Outlined.Search,
                                    contentDescription = "Search button",
                                    modifier = Modifier.clickable {
                                            listOfMandals = search(textFieldValue, listOfMandal)
                                        })
                            },
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total no.of Mandal Registered:",
                                fontWeight = FontWeight.W400,
                                fontSize = 20.sp
                            )
                            Text(
                                text = numberofMandal.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }

                        AnimatedVisibility(visible = listOfMandals.isNotEmpty()) {
                            LazyColumn() {
                                for (mandal in listOfMandals) {
                                    item {
                                        MandalSingleRow(
                                            mandal = mandal,
                                            show1stAuditStatus = false,
                                            show2ndAuditStatus = false,
                                            listOfAuditId = null,
                                            inRegistered = true
                                        )
                                    }
                                }
                            }
                        }
                        AnimatedVisibility(visible = listOfMandals.isEmpty()) {
                            Text(
                                text = "No Registered Mandals Available",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

fun search(textFieldValue: TextFieldValue, listOfMandals: ArrayList<Mandal>): ArrayList<Mandal> {
    var list = ArrayList<Mandal>()
    if (!TextUtils.isEmpty(textFieldValue.text)) {
        for (mandal in listOfMandals) {
            if (mandal.nameOfMandal.toString().contains(textFieldValue.text, true)) {
                list.add(mandal)
            }
        }
    } else {
        list = listOfMandals
    }
    return list
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    GaneshMandalTheme {

    }
}

//nameOfMandal
//addOfMandal
//contactPerson
//mobileNo
//totalAreaCovered
//totalAreaOpen
//dateOfTechnicalEvaluation
//powerConsumedByMandal
//sizeOfCableInstalled
//typeOfCable
//mcb_mccbInstalled
//_24ElectricianYesOrNo
//properTerminationOfCables
//fireExtin1
//fireExt1Quant
//fireExtin2
//fireExt2Quant
//fireDetect1
//fireDetect1Quant
//fireDetect2
//fireDetect2Quant
//typeOfCamera1
//typeOfCamera1Qty
//typeOfCamera2
//typeOfCamera2Qty
//dvr
//channels
//dvrQuantity
//storageDetails
//monitoringMethodology
//typeOfSpeaker1
//typeOfSpeaker1Quant
//typeOfSpeaker2
//typeOfSpeaker2Quant
//announcingTeam
//announcingTeamQuant
//typeOfDetector1
//typeOfDetector1Quant
//typeOfDetector2
//typeOfDetector2Quant
//typeOfSign1
//typeOfSign1Quant
//typeOfSign2
//typeOfSign2Quant
//firstAidKit
//firstAidKitQuant
//emergencyEvacuationPlanDetails
//securityTeamDetails
//trainingSecurityTeam
//remarks