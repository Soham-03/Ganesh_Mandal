package com.gdsc.ganeshmandal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.model.MandalFinalAudit
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.gdsc.ganeshmandal.ui.theme.MandalIndividualAuditSingleRow
import com.google.firebase.firestore.FirebaseFirestore

class ListOfAuditsForSelectedMandal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = FirebaseFirestore.getInstance()
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var listOfAudits by remember {
                        mutableStateOf(ArrayList<MandalFinalAudit>())
                    }
                    val listOfMandalAudit = ArrayList<MandalFinalAudit>()
                    db.collection("mandalsSelectedForNext")
                        .document(Global.selectedMandal!!.mandalId)
                        .collection("audits")
                        .get()
                        .addOnSuccessListener {docs->
                            println("Audit Docs Size: "+docs.size())
                            for(doc in docs){
                                listOfMandalAudit.add(
                                    MandalFinalAudit(
                                        auditId = doc.id,
                                                pointsElectricalCabling = doc["pointsElectricalCabling"].toString(),
                                                pointsElectricalTerminations =  doc["pointsElectricalTerminations"].toString(),
                                                pointsSwitchBoardInstallation =  doc["pointsSwitchBoardInstallation"].toString(),
                                                pointsLocationOfFireExtinguishers =  doc["pointsLocationOfFireExtinguishers"].toString(),
                                                pointsValidityOfFireExtinguishers =  doc["pointsValidityOfFireExtinguishers"].toString(),
                                                pointsOtherFireSafetyArrangements =  doc["pointsOtherFireSafetyArrangements"].toString(),
                                                pointsOperationOfMetalDetectors =  doc["pointsOperationOfMetalDetectors"].toString(),
                                                pointsOperationOfBombDetectors =  doc["pointsOperationOfBombDetectors"].toString(),
                                                pointsSurveillanceCoverage =  doc["pointsSurveillanceCoverage"].toString(),
                                                pointsSurveillanceMonitoring=  doc["pointsSurveillanceMonitoring"].toString(),
                                                pointsPublicAddressCoverage=  doc["pointsPublicAddressCoverage"].toString(),
                                                pointsAnnouncingTeam=  doc["pointsAnnouncingTeam"].toString(),
                                                pointsLocationOfBanners=  doc["pointsLocationOfBanners"].toString(),
                                                pointsMedicalEmergencyMitigation=  doc["pointsMedicalEmergencyMitigation"].toString(),
                                                pointsSecurityAndVolunteerTeam=  doc["pointsSecurityAndVolunteerTeam"].toString(),
                                                pointsCompetencyOfTeam=  doc["pointsCompetencyOfTeam"].toString(),
                                                pointsEmergencyEvacuationProcess=  doc["pointsEmergencyEvacuationProcess"].toString(),
                                                pointsProactivenessOfFireSafety=  doc["pointsProactivenessOfFireSafety"].toString(),
                                                pointsIsMandalInsured=  doc["pointsIsMandalInsured"].toString(),
                                                totalScoreText=  doc["totalScoreOfAudit"].toString(),
                                                imgElectricalCabling=  doc["imgElectricalCabling"].toString(),
                                                imgElectricalTerminations=  doc["imgElectricalTerminations"].toString(),
                                                imgSwitchBoardInstallation=  doc["imgSwitchBoardInstallation"].toString(),
                                                imgLocationOfFireExtinguishers=  doc["imgLocationOfFireExtinguishers"].toString(),
                                                imgValidityOfFireExtinguishers=  doc["imgValidityOfFireExtinguishers"].toString(),
                                                imgOtherFireSafetyArrangements=  doc["imgOtherFireSafetyArrangements"].toString(),
                                                imgOperationOfMetalDetectors=  doc["imgOperationOfMetalDetectors"].toString(),
                                                imgOperationOfBombDetectors=  doc["imgOperationOfBombDetectors"].toString(),
                                                imgSurveillanceCoverage=  doc["imgSurveillanceCoverage"].toString(),
                                                imgSurveillanceMonitoring=  doc["imgSurveillanceMonitoring"].toString(),
                                                imgPublicAddressCoverage=  doc["imgPublicAddressCoverage"].toString(),
                                                imgAnnouncingTeam=  doc["imgAnnouncingTeam"].toString(),
                                                imgLocationOfBanners=  doc["imgLocationOfBanners"].toString(),
                                                imgMedicalEmergencyMitigation=  doc["imgMedicalEmergencyMitigation"].toString(),
                                                imgSecurityAndVolunteerTeam=  doc["imgSecurityAndVolunteerTeam"].toString(),
                                                imgCompetencyOfTeam=  doc["imgCompetencyOfTeam"].toString(),
                                                imgEmergencyEvacuationProcess=  doc["imgEmergencyEvacuationProcess"].toString(),
                                                imgProactivenessOfFireSafety=  doc["imgProactivenessOfFireSafety"].toString(),
                                                imgIsMandalInsured=  doc["imgIsMandalInsured"].toString(),
                                    )
                                )
                            }
                            listOfAudits = listOfMandalAudit
                        }
                    var totalScoreOfAllAudits by remember {
                        mutableStateOf(0)
                    }
                    Column {
                        Text(text = "Total Score of all Audits: $totalScoreOfAllAudits", fontSize = 24.sp, textAlign = TextAlign.Center)
                        LazyColumn(){
                            for(audit in listOfAudits){
                                totalScoreOfAllAudits+=audit.totalScoreText.toInt()
                                item {
                                    MandalIndividualAuditSingleRow(audit = audit)
                                }
                            }
                        }
                    }
                    println("List of audits: "+listOfAudits)

                }
            }
        }
    }
}