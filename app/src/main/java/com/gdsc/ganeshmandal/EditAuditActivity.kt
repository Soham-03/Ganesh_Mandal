package com.gdsc.ganeshmandal

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class EditAuditActivity : ComponentActivity() {
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                Global.currentCapturedImage.set(index = (Global.clickedOnButton-1), element = uri)
                Global.currentCapturedImage[19] = uri
                Toast.makeText(this@EditAuditActivity, uri.toString(), Toast.LENGTH_SHORT).show()
            }
            else{
//                Global.currentCapturedImage.add(Global.clickedOnButton-1,null)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val storage = FirebaseStorage.getInstance()
                    var auditInfo = remember {
                        mutableStateOf<MandalFinalAudit?>(null)
                    }
                    var auditInfoLocal: MandalFinalAudit? = null
                    val db = FirebaseFirestore.getInstance()
                    db.collection("mandalsSelectedForNext")
                        .document(Global.selectedMandal!!.mandalId)
                        .collection("audits")
                        .document(Global.adminId.toString())
                        .get()
                        .addOnSuccessListener {doc->
                            auditInfo.value = MandalFinalAudit(
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
                                if(auditInfoLocal != null){
                                    auditInfo.value = auditInfoLocal
                                }
                        }
                    if(auditInfo.value != null){
                        EditAuditForm(launcher = launcher, storage = storage, audit = auditInfo.value)
                    }
                }
            }
        }
    }
}