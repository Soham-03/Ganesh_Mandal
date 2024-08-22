package com.gdsc.ganeshmandal

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object Global {
    var selectedMandal: Mandal? = null
    var selectedMandaAudit: MandalFinalAudit? = null
    var adminId: String? = null
    var listOfAdminIdAndPassword = HashMap<String,String>()
    var currentCapturedImage = mutableStateListOf<Uri?>(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
    var clickedOnButton: Int = 0
}