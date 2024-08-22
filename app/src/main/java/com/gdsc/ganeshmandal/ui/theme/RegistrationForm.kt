package com.gdsc.ganeshmandal.ui.theme

import android.app.DatePickerDialog
import android.app.LocalActivityManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm(launcher: ActivityResultLauncher<Intent>, uriImageState: MutableState<Uri?>) {

}


@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun RegistrationFormPreview(){
//    RegistrationForm(launcher, uriImageState)
}

//nameOfMandal.text//
//addOfMandal.text//
//contactPerson.text//
//mobileNo.text//
//totalAreaCovered.text//
//totalAreaOpen.text//
//dateOfTechnicalEvaluation//
//powerConsumedByMandal.text//
//sizeOfCableInstalled.text//
//typeOfCable.text//
//mcb_mccbInstalled.text//
//_24ElectricianYesOrNo//
//properTerminationOfCables
//fireExtin1.text
//fireExt1Quant.text
//fireExtin2.text
//fireExt2Quant.text
//fireDetect1.text
//fireDetect1Quant.text
//fireDetect2.text
//fireDetect2Quant.text
//typeOfCamera1.text
//typeOfCamera1Qty.text
//typeOfCamera2.text
//typeOfCamera2Qty.text//
//dvr.text
//channels.text
//dvrQuantity.text
//storageDetails.text
//monitoringMethodology.text
//typeOfSpeaker1.text
//typeOfSpeaker1Quant.text
//typeOfSpeaker2.text
//typeOfSpeaker2Quant.text
//announcingTeam.text
//announcingTeamQuant.text
//typeOfDetector1.text
//typeOfDetector1Quant.text
//typeOfDetector2.text
//typeOfDetector2Quant.text
//typeOfSign1.text
//typeOfSign1Quant.text
//typeOfSign2.text
//typeOfSign2Quant.text
//firstAidKit.text
//firstAidKitQuant.text
//emergencyEvacuationPlanDetails.text
//securityTeamDetails.text
//trainingSecurityTeam.text
//remarks.text//