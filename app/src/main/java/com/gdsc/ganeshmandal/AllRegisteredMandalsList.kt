package com.gdsc.ganeshmandal

import android.app.Activity
import android.content.Intent
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.gdsc.ganeshmandal.ui.theme.MandalSingleRow
import com.github.drjacky.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore

class AllRegisteredMandalsList : ComponentActivity() {
    private val uriImageState = mutableStateOf<Uri?>(null)
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                uriImageState.value = uri
                Toast.makeText(this@AllRegisteredMandalsList, uriImageState.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var listOfMandals by remember {
                        mutableStateOf(ArrayList<Mandal>())
                    }
                    val db = FirebaseFirestore.getInstance()
                    val listOfMandal = ArrayList<Mandal>()
//                    listOfMandals = listOfMandal
                    var textFieldValue by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    db.collection("mandals").get()
                        .addOnSuccessListener {docs->
                            for(doc in docs){
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
                                        typeOfSpeaker1Quant= doc["typeOfSpeaker1Quant"].toString(),
                                        typeOfSpeaker2= doc["typeOfSpeaker2"].toString(),
                                        typeOfSpeaker2Quant= doc["typeOfSpeaker2Quant"].toString(),
                                        announcingTeam= doc["announcingTeam"].toString(),
                                        announcingTeamQuant= doc["announcingTeamQuant"].toString(),
                                        typeOfDetector1= doc["typeOfDetector1"].toString(),
                                        typeOfDetector1Quant= doc["typeOfDetector1Quant"].toString(),
                                        typeOfDetector2= doc["typeOfDetector2"].toString(),
                                        typeOfDetector2Quant= doc["typeOfDetector2Quant"].toString(),
                                        typeOfSign1= doc["typeOfSign1"].toString(),
                                        typeOfSign1Quant= doc["typeOfSign1Quant"].toString(),
                                        typeOfSign2= doc["typeOfSign2"].toString(),
                                        typeOfSign2Quant= doc["typeOfSign2Quant"].toString(),
                                        firstAidKit= doc["firstAidKit"].toString(),
                                        firstAidKitQuant= doc["firstAidKitQuant"].toString(),
                                        emergencyEvacuationPlanDetails= doc["emergencyEvacuationPlanDetails"].toString(),
                                        securityTeamDetails= doc["securityTeamDetails"].toString(),
                                        trainingSecurityTeam= doc["trainingSecurityTeam"].toString(),
                                        remarks= doc["remarks"].toString(),
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
                                        nameOf1stAuditor = doc["nameOf1stAuditor"].toString()
                                    )
                                )
                            }
                            println("List"+listOfMandal)
                            if(TextUtils.isEmpty(textFieldValue.text)){
                                    listOfMandals = listOfMandal
                            }

                        }
                    Column(){
                        Text(text = uriImageState.value.toString())
//                        AsyncImage(model = if(imageUri!=null){imageUri}else{"data:image/jpeg;base64,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"}, contentDescription = "")
                        Button(onClick = {
                            launcher.launch(
                                ImagePicker.with(this@AllRegisteredMandalsList)
                                    .cameraOnly()
                                    .createIntent()
                            )
                        }) {
                            Text(text = "Upload Image")
                        }
                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = {
                                textFieldValue = it
                                listOfMandals = if(!TextUtils.isEmpty(textFieldValue.text)){
                                    val list = search(it, listOfMandal)
                                    list
                                } else{
                                    listOfMandal
                                }
                            },
                            label = {
                                    Text(text = "Serach Mandal")
                            },
                            trailingIcon = {
                                Image(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "Search button",
                                    modifier = Modifier
                                        .clickable {
                                            listOfMandals = search(textFieldValue, listOfMandal)
                                        },
                                    colorFilter = ColorFilter.tint(Color.White),
                                )
                            },
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )
                        AnimatedVisibility(visible = listOfMandals.isNotEmpty()) {
                            LazyColumn(){
                                for(mandal in listOfMandals){
                                    item {
                                        MandalSingleRow(mandal = mandal, show1stAuditStatus = false, show2ndAuditStatus = false)
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
    if(!TextUtils.isEmpty(textFieldValue.text)){
        for(mandal in listOfMandals){
            if(mandal.nameOfMandal.toString().contains(textFieldValue.text, true)){
                list.add(mandal)
            }
        }
    }
    else{
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