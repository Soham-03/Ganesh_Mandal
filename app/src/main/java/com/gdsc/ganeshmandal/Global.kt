package com.gdsc.ganeshmandal

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.gdsc.ganeshmandal.model.Mandal
import com.gdsc.ganeshmandal.model.MandalFinalAudit

object Global {
    var selectedMandal: Mandal? = null
    var selectedMandaAudit: MandalFinalAudit? = null
    var adminId: String? = null
    var listOfAdminIdAndPassword = HashMap<String,String>()
    var currentCapturedImage = mutableStateListOf<Uri?>(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
    var clickedOnButton: Int = 0
}